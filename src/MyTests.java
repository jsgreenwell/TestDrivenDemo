import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyTests {
    CheckAndClean cAndC = new CheckAndClean();

    @Test
    public void testcheckMatching() {
        assertFalse(cAndC.checkMatching("somebody knows", "bob"));
        assertTrue(cAndC.checkMatching("somebody knows", "some"));
        // above same as:
        assertEquals(cAndC.checkMatching("YES I want to exit", "yes"), true);

    }

    @Test
    public void testSanitize() {
        // Using these as test cases, so I don't have to write up new ones all the time
        final String[][] TESTCASES = {
                { "Bobby\\'\\;Drop tables students\\;\\-\\-",
                    "Bobby';Drop tables students;--"},
                { "May I do that\\.", "May I do that."},
                { "", "" },
                { "\\>\\; ls \\-al\\#", ">; ls -al#"}
        };

        // Check each testcase (if index 0 is Equal to index 1 sent to sanitize function
        for (String[] t : TESTCASES) {
            // print out the sanitized version (to check manually)
            System.out.println(cAndC.sanitize(t[1]));
            // check index 0 against index 1
            assertEquals(t[0], cAndC.sanitize(t[1]));
        }
    }

    @Test
    public void testCheckLengths() {
        // Check the wrapper (only needs to be above 1 digit) then
        // Check main function 8-16 & 10-19 & nothing
        // Switching on and off (so each is groups of 2 to 5 as needed)
        System.out.println("Group 1 checks: nothing part 1");
        assertFalse(cAndC.checkLength("",8));
        assertFalse(cAndC.checkLength("",8, 1));

        System.out.println("Group 2 checks: Invalid Arguments 1");
        // Cannot send negative max length or max length of 0 or min of 0
        assertFalse(cAndC.checkLength("abc",-8));
        assertFalse(cAndC.checkLength("abc",-8, 1));
        assertFalse(cAndC.checkLength("abc",0));
        assertFalse(cAndC.checkLength("abc",0, 1));

        System.out.println("Group 3 checks: Invalid Arguments 2");
        // This one should be false because cannot set 0 or less length
        assertFalse(cAndC.checkLength("abc",8, 0));
        assertFalse(cAndC.checkLength("abc",8, -1));

        System.out.println("Group 4 checks: Should be all correct");
        // Now for a few true then false just to check mechanics
        assertTrue(cAndC.checkLength("a123456", 8));
        assertTrue(cAndC.checkLength("a123456", 8, 4));
        assertTrue(cAndC.checkLength("12a123456", 15));
        assertTrue(cAndC.checkLength("a123456", 15, 6));

        System.out.println("Group 5 checks: Should report all are invalid");
        assertFalse(cAndC.checkLength("a123456", 3));
        assertFalse(cAndC.checkLength("a123456", 3, 1));
        assertFalse(cAndC.checkLength("a123456", 16, 8));

        System.out.println("Final check: A weird case");
        // Finally the weird case - should be false because min is greater than max
        assertFalse(cAndC.checkLength("a123456", 8, 16));
    }
}
