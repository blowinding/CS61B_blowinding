package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        Integer[] arr = {1, 2, 3, 4};
        for (int i = 0; i < 4; i++) {
            for (Integer integer : arr) {
                arb.enqueue(integer);
            }
            for (Integer integer : arr) {
                assertEquals(arb.dequeue(), integer);
            }
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
