package beans;

import database.JDBCManager;

import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@ManagedBean(name = "results")
@ApplicationScoped
public class Results {
    private final Connection connection;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "ivan";
    private static final String PASS = "ivan";
//    private static final String DB_URL = "jdbc:postgresql://pg:5432/studs";
//    private static final String USER = "";
//    private static final String PASS = "";
    private static final String TABLE_NAME = "results";
    private final String sessionID;
    private final Logger logger;

    {
        connection = new JDBCManager(DB_URL, USER, PASS, true).getConnection();
        FacesContext fCtx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false);
        sessionID = session.getId();
        logger = Logger.getLogger("logger");
        try {
            logger.addHandler(new FileHandler("log.txt"));
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        logger.info("2");
    }

    public int addResult() {
        logger.info("3");
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String xstr = requestParameterMap.get("form:xFromGraph");
        String ystr = requestParameterMap.get("form:y");
        String rstr = requestParameterMap.get("form:r");
        logger.info("X = " + xstr);
        logger.info("Y = " + ystr);
        logger.info("R = " + rstr);
        double x;
        double y;
        double r;
        try {
            x = Double.parseDouble(xstr.replace(',', '.'));
            y = Double.parseDouble(ystr.replace(',', '.'));
            r = Double.parseDouble(rstr.replace(',', '.'));
        } catch (Exception e) {
            return -1;
        }
        if (!MatchingManager.valid(x, y, r))
            return -1;
        boolean check = MatchingManager.match(x, y, r);

        logger.info("try");
        try {
            String sql = "INSERT INTO ? (id ,x, y, r , match) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, TABLE_NAME);
            preparedStatement.setString(2, sessionID);
            preparedStatement.setDouble(3, x);
            preparedStatement.setDouble(4, y);
            preparedStatement.setDouble(5, r);
            preparedStatement.setBoolean(6, check);

            int rows = preparedStatement.executeUpdate();
            return rows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("added");
        return -1;
    }

    public Vector<ResultRow> getAllResults() {
        logger.info("asd");
        Vector<ResultRow> resultRows = new Vector<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM " + TABLE_NAME +
                    " WHERE id = '" + sessionID + "';");
            while (resultSet.next()) {
                ResultRow resultRow = new ResultRow();
                resultRow.setX(resultSet.getString("x"));
                resultRow.setY(resultSet.getString("y"));
                resultRow.setR(resultSet.getString("r"));
                resultRow.setMatch(resultSet.getString("match").contains("t"));
                resultRows.add(resultRow);
            }
            resultSet.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return resultRows;
    }

    @PreDestroy
    private void clearResults() {
        try {
            connection.createStatement().executeUpdate("DELETE FROM " + TABLE_NAME +
                    " WHERE sessionID = '" + sessionID + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
