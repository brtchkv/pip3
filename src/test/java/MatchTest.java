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
    public void firstSectionOnBrinkTest() {
        assertTrue(MatchingManager.match(1, 1, r));
    }

    @Test
    public void firstSectionNearInTest() {
        assertTrue(MatchingManager.match(1, 0.9, r));
    }

    @Test
    public void firstSectionNearOutTest() {
        assertFalse(MatchingManager.match(1, 1.1, r));
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
    public void secondSectionOnBrinkTest() {
        assertTrue(MatchingManager.match(-1, 2, r));
    }

    @Test
    public void secondSectionNearInTest() {
        assertTrue(MatchingManager.match(-1, 1.9, r));
    }

    @Test
    public void secondSectionNearOutTest() {
        assertFalse(MatchingManager.match(-1, 2.1, r));
    }

    @Test
    public void thirdSectionInTest() {
        assertTrue(MatchingManager.match(0, 0, r));
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
    public void fourthSectionOnBrinkTest() {
        assertTrue(MatchingManager.match(0, -1, r));
    }

    @Test
    public void fourthSectionNearInTest() {
        assertTrue(MatchingManager.match(0, -0.9, r));
    }

    @Test
    public void fourthSectionNearOutTest() {
        assertFalse(MatchingManager.match(0, -1.1, r));
    }

    @Test
    public void goodCoordinatesValidationTest() {
        assertTrue(MatchingManager.valid(1, 2, r));
    }

    @Test
    public void wrongCoordinatesValidationTest() {
        assertFalse(MatchingManager.valid(0.666, 14, 88));
    }

    @AfterClass
    public static void globalTearDown() {
        System.out.println("Done running tests");
    }
}
