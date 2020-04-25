package main.java.beans;

/**
 * Method for testing javadoc
 */
public final class MatchingManager {
    private static final double[] possibleR = {1.5, 1, 2, 2.5, 3, 3.5};
    private static final double[] possibleX = {-2, -1, 0, 1, 2};
    private static final double minY = -5;
    private static final double maxY = 5;

    public static boolean valid(double x, double y, double r) {
        return matchX(x) &&
                y >= minY && y <= maxY &&
                matchR(r);
    }

    public static boolean match(double x, double y, double r) {
        Boolean correctCoordinate = false;
        if (x >= 0 && y >= 0 && y <= -x + r) {
            correctCoordinate = true;
        } else if (x >= 0 && y <= 0 && y * y <= r * r / 4 - x * x) {
            correctCoordinate = true;
        } else if (x <= 0 && y >= 0 && x >= -r / 2 && y <= r) {
            correctCoordinate = true;
        }

        return correctCoordinate;
    }

    private static boolean matchX(double x) {
        for (double aPossibleX : possibleX)
            if (x == aPossibleX)
                return true;
        return false;
    }

    private static boolean matchR(double r) {
        for (double aPossibleR : possibleR)
            if (r == aPossibleR)
                return true;
        return false;
    }

}
