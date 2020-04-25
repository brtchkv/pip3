import main.java.beans.MatchingManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MatchTest {
    private double r = 2;

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Running some tests");
    }

    @Test
    public void firstSectionInTest() {
        assertTrue(MatchingManager.match(1, 0.5, r));
    }

    @Test
    public void firstSectionOutTest() {
        assertFalse(MatchingManager.match(2, 0.5, r));
    }

    @Test
    public void firstSectionOnCenterBrinkTest() {
        assertTrue(MatchingManager.match(1, 1, r));
    }

    @Test
    public void firstSectionNearCenterInTest() {
        assertTrue(MatchingManager.match(1, 0.9, r));
    }

    @Test
    public void firstSectionNearCenterOutTest() {
        assertFalse(MatchingManager.match(1, 1.1, r));
    }

    @Test
    public void firstSectionOnTopBrinkTest() {
        assertTrue(MatchingManager.match(0, 2, r));
    }

    @Test
    public void firstSectionNearTopInTest() {
        assertTrue(MatchingManager.match(0, 1.9, r));
    }

    @Test
    public void firstSectionNearTopOutTest() {
        assertFalse(MatchingManager.match(0, 2.1, r));
    }

    @Test
    public void firstSectionOnRightBrinkTest() {
        assertTrue(MatchingManager.match(2, 0, r));
    }

    @Test
    public void firstSectionNearRightInTest() {
        assertTrue(MatchingManager.match(1.9, 0, r));
    }

    @Test
    public void firstSectionNearRightOutTest() {
        assertFalse(MatchingManager.match(2.1, 0, r));
    }

    @Test
    public void secondSectionInTest() {
        assertTrue(MatchingManager.match(0, 1, r));
    }

    @Test
    public void secondSectionOutTest() {
        assertFalse(MatchingManager.match(-2, 1, r));
    }

    @Test
    public void secondSectionOnCenterBrinkTest() {
        assertTrue(MatchingManager.match(-1, 1, r));
    }

    @Test
    public void secondSectionNearCenterInTest() {
        assertTrue(MatchingManager.match(-0.9, 1, r));
    }

    @Test
    public void secondSectionNearCenterOutTest() {
        assertFalse(MatchingManager.match(-1.1, 1, r));
    }

    @Test
    public void secondSectionOnTopRightBrinkTest() {
        assertTrue(MatchingManager.match(0, 2, r));
    }

    @Test
    public void secondSectionNearTopRightInTest() {
        assertTrue(MatchingManager.match(0, 1.9, r));
    }

    @Test
    public void secondSectionNearTopRightOutTest() {
        assertFalse(MatchingManager.match(0, 2.1, r));
    }

    @Test
    public void secondSectionOnTopLeftBrinkTest() {
        assertTrue(MatchingManager.match(-1, 2, r));
    }

    @Test
    public void secondSectionNearTopLeftInTest() {
        assertTrue(MatchingManager.match(-0.9, 1.9, r));
    }

    @Test
    public void secondSectionNearTopLeftOutTest() {
        assertFalse(MatchingManager.match(-1.1, 2.1, r));
    }

    @Test
    public void secondSectionOnDownLeftBrinkTest() {
        assertTrue(MatchingManager.match(-1, 0, r));
    }

    @Test
    public void secondSectionNearDownLeftInTest() {
        assertTrue(MatchingManager.match(-0.9, 0, r));
    }

    @Test
    public void secondSectionNearDownLeftOutTest() {
        assertFalse(MatchingManager.match(-1.1, 0, r));
    }

    @Test
    public void ZeroPointTest() {
        assertTrue(MatchingManager.match(0, 0, r));
    }

    @Test
    public void nearZeroPointTest() {
        assertTrue(MatchingManager.match(-0.1, 0.1, r));
    }

    @Test
    public void near2ZeroPointTest() {
        assertFalse(MatchingManager.match(-0.1, -0.1, r));
    }

    @Test
    public void thirdSectionOutTest() {
        assertFalse(MatchingManager.match(-2, -2, r));
    }

    @Test
    public void fourthSectionInTest() {
        assertTrue(MatchingManager.match(1, 0, r));
    }

    @Test
    public void fourthSectionOutTest() {
        assertFalse(MatchingManager.match(-2, 2, r));
    }

    @Test
    public void fourthSectionOnDownBrinkTest() {
        assertTrue(MatchingManager.match(0, -1, r));
    }

    @Test
    public void fourthSectionNearDownInTest() {
        assertTrue(MatchingManager.match(0, -0.9, r));
    }

    @Test
    public void fourthSectionNearDownOutTest() {
        assertFalse(MatchingManager.match(0, -1.1, r));
    }

    @Test
    public void fourthSectionOnCenterBrinkTest() {
        assertTrue(
                MatchingManager.match(
                        Math.sqrt(Math.pow(3.5 / 2, 2) / 2),
                        -1 * Math.sqrt(Math.pow(3.5 / 2, 2) / 2),
                        3.5
                )
        );
    }

    @Test
    public void fourthSectionNearCenterInTest() {
        assertTrue(
                MatchingManager.match(
                        Math.sqrt(Math.pow(3.5 / 2, 2) / 2) - 0.001,
                        -1 * Math.sqrt(Math.pow(3.5 / 2, 2) / 2) + 0.001,
                        3.5
                )
        );
    }

    @Test
    public void fourthSectionNearCenterOutTest() {
        assertFalse(
                MatchingManager.match(
                        Math.sqrt(Math.pow(3.5 / 2, 2) / 2) + 0.001,
                        -1 * Math.sqrt(Math.pow(3.5 / 2, 2) / 2) - 0.001,
                        3.5
                )
        );
    }

    @Test
    public void fourthSectionOnRightBrinkTest() {
        assertTrue(MatchingManager.match(1, 0, r));
    }

    @Test
    public void fourthSectionNearRightInTest() {
        assertTrue(MatchingManager.match(0.9, 0, r));
    }

    @Test
    public void fourthSectionNearRightOutTest() {
        assertTrue(MatchingManager.match(1.1, 0, r));
    }

    @Test
    public void goodCoordinatesValidationTest() {
        assertTrue(MatchingManager.valid(1, 2, r));
    }

    @Test
    public void wrongCoordinatesValidationTest() {
        assertFalse(MatchingManager.valid(0.666, 14, 88));
    }

    @Test
    public void r15ValidationTest() {
        assertTrue(MatchingManager.valid(1, 2, 1.5));
    }

    @Test
    public void lessR15ValidationTest() {
        assertFalse(MatchingManager.valid(1, 2, 1.4));
    }

    @Test
    public void largerR15ValidationTest() {
        assertFalse(MatchingManager.valid(1, 2, 1.6));
    }

    @Test
    public void r2ValidationTest() {
        assertTrue(MatchingManager.valid(1, 2, 2));
    }

    @Test
    public void lessR2ValidationTest() {
        assertFalse(MatchingManager.valid(1, 2, 1.9));
    }

    @Test
    public void largerR2ValidationTest() {
        assertFalse(MatchingManager.valid(1, 2, 2.1));
    }

    @Test
    public void r1ValidationTest() {
        assertTrue(MatchingManager.valid(1, 2, 1));
    }

    @Test
    public void lessR1ValidationTest() {
        assertFalse(MatchingManager.valid(1, 2, 0.9));
    }

    @Test
    public void largerR1ValidationTest() {
        assertFalse(MatchingManager.valid(1, 2, 1.1));
    }

    @Test
    public void r25ValidationTest() {
        assertTrue(MatchingManager.valid(1, 2, 2.5));
    }

    @Test
    public void lessR25ValidationTest() {
        assertFalse(MatchingManager.valid(1, 2, 2.4));
    }

    @Test
    public void largerR25ValidationTest() {
        assertFalse(MatchingManager.valid(1, 2, 2.6));
    }

    @Test
    public void r3ValidationTest() {
        assertTrue(MatchingManager.valid(1, 2, 3));
    }

    @Test
    public void lessR3ValidationTest() {
        assertFalse(MatchingManager.valid(1, 2, 2.9));
    }

    @Test
    public void largerR3ValidationTest() {
        assertFalse(MatchingManager.valid(1, 2, 3.1));
    }

    @Test
    public void r35ValidationTest() {
        assertTrue(MatchingManager.valid(1, 2, 3.5));
    }

    @Test
    public void lessR35ValidationTest() {
        assertFalse(MatchingManager.valid(1, 2, 3.4));
    }

    @Test
    public void largerR35ValidationTest() {
        assertFalse(MatchingManager.valid(1, 2, 3.6));
    }

    @Test
    public void maxYValidationTest() {
        assertTrue(MatchingManager.valid(1, 5, r));
    }

    @Test
    public void lessMaxYValidationTest() {
        assertTrue(MatchingManager.valid(1, 4.9, r));
    }

    @Test
    public void largerMaxYValidationTest() {
        assertFalse(MatchingManager.valid(1, 5.1, r));
    }

    @Test
    public void minYValidationTest() {
        assertTrue(MatchingManager.valid(1, -5, r));
    }

    @Test
    public void lessMinYValidationTest() {
        assertFalse(MatchingManager.valid(1, -5.1, r));
    }

    @Test
    public void largerMinYValidationTest() {
        assertTrue(MatchingManager.valid(1, -4.9, 2));
    }

    @Test
    public void xMinus2ValidationTest() {
        assertTrue(MatchingManager.valid(-2, 2, r));
    }

    @Test
    public void lessXMinus2ValidationTest() {
        assertFalse(MatchingManager.valid(-2.1, 2, r));
    }

    @Test
    public void largerXMinus2ValidationTest() {
        assertFalse(MatchingManager.valid(-1.9, 2, r));
    }

    @Test
    public void xMinus1ValidationTest() {
        assertTrue(MatchingManager.valid(-1, 2, r));
    }

    @Test
    public void lessXMinus1ValidationTest() {
        assertFalse(MatchingManager.valid(-1.1, 2, r));
    }

    @Test
    public void largerXMinus1ValidationTest() {
        assertFalse(MatchingManager.valid(-0.9, 2, r));
    }

    @Test
    public void xZeroValidationTest() {
        assertTrue(MatchingManager.valid(0, 2, r));
    }

    @Test
    public void lessXZeroValidationTest() {
        assertFalse(MatchingManager.valid(-0.1, 2, r));
    }

    @Test
    public void largerXZeroValidationTest() {
        assertFalse(MatchingManager.valid(0.1, 2, r));
    }

    @Test
    public void x1ValidationTest() {
        assertTrue(MatchingManager.valid(1, 2, r));
    }

    @Test
    public void lessX1ValidationTest() {
        assertFalse(MatchingManager.valid(0.9, 2, r));
    }

    @Test
    public void largerX1ValidationTest() {
        assertFalse(MatchingManager.valid(1.1, 2, r));
    }

    @Test
    public void x2ValidationTest() {
        assertTrue(MatchingManager.valid(2, 2, r));
    }

    @Test
    public void lessX2ValidationTest() {
        assertFalse(MatchingManager.valid(1.9, 2, r));
    }

    @Test
    public void largerX2ValidationTest() {
        assertFalse(MatchingManager.valid(2.1, 2, r));
    }

    @AfterClass
    public static void globalTearDown() {
        System.out.println("Done running tests");
    }
}
