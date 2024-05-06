import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyTests {
    CheckAndClean cAndC = new CheckAndClean();

    @Test
    public void testcheckMatching() {
        assertFalse(cAndC.checkMatching("somebody knows", "bob"));
        assertTrue(cAndC.checkMatching("somebody knows", "some"));
        // above same as:
        assertEquals(cAndC.checkMatching("YES I want to exit", "yes"), true);

    }

}
