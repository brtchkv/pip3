package main.java.beans;

import java.io.Serializable;

/**
 * Method for testing javadoc (ResultRow)
 */
public class ResultRow implements Serializable {
    private String x, y, r;
    private Boolean match;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public Boolean getMatch() {
        return match;
    }

    public void setMatch(Boolean match) {
        this.match = match;
    }

}
