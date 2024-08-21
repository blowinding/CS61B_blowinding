import org.junit.Test;
import static org.junit.Assert.*;
public class FlikTest {
    @Test
    public void testHorribleSteve() {
        int i = 0;
        for (int j = 0; i < 500; ++i, ++j) {
            if (!Flik.isSameNumber(i, j)) {
                break; // break exits the for loop!
            }
        }
        assertEquals(500, i);
    }

    @Test
    public void test128() {
        assertTrue(Flik.isSameNumber(128, 128));
    }
}
