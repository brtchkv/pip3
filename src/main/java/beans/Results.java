package main.java.beans;

import main.java.database.JDBCManager;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Logger;

/**
 * ManagedBean ApplicationScoped
 */
@ManagedBean(name = "results")
@ApplicationScoped
public class Results {
    private final Connection connection;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "ivan";
    private static final String PASS = "ivan";
    //    private static final String DB_URL = "jdbc:postgresql://pg:5432/studs";
//    private static final String USER = "s263916";
//    private static final String PASS = "";
    private static final String TABLE_NAME = "results";
    private final Logger logger;

    {
        connection = new JDBCManager(DB_URL, USER, PASS, true).getConnection();
        logger = Logger.getLogger("logger");
    }

    public int addResult() {
        Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String xstr = requestParameterMap.get("form:xGraph");
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
            logger.info("-1");
            return -1;
        }
        logger.info(String.valueOf(MatchingManager.valid(x, y, r)));
        boolean check = MatchingManager.match(x, y, r);

        logger.info("try");
        try {
            String sql = "INSERT INTO results (x, y, r , match) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, x);
            preparedStatement.setDouble(2, y);
            preparedStatement.setDouble(3, r);
            preparedStatement.setBoolean(4, check);

            int rows = preparedStatement.executeUpdate();
            return rows;
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("added");
        return -1;
    }

    public Vector<ResultRow> getAllResults() {
        Vector<ResultRow> resultRows = new Vector<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()) {
                ResultRow resultRow = new ResultRow();
                Double x = Double.parseDouble(resultSet.getString("x"));
                Double y = Double.parseDouble(resultSet.getString("y"));

                DecimalFormat df = new DecimalFormat("####0.00");

                resultRow.setX(df.format(x));
                resultRow.setY(df.format(y));
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
}
