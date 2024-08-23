import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    private static final int testNum = 5000;
    private static final String[] testMessage = {"addFirst", "addLast", "removeFirst", "removeLast"};

    private String generateMessage(int chooseFunctionNum, int value) {
        if (chooseFunctionNum < 2) {
            return testMessage[chooseFunctionNum] + "(" + value + ")" + "\n";
        } else {
            return testMessage[chooseFunctionNum] + "()" + "\n";
        }
    }

    @Test
    public void testArrayDeque() {
        ArrayDequeSolution<Integer> stdDeque = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> buggyDeque = new StudentArrayDeque<>();
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < testNum; i++) {
            // generate int from 0 to 3
            int chooseFunctionNum = StdRandom.uniform(0, 4);
            msg.append(generateMessage(chooseFunctionNum, i));
            switch (chooseFunctionNum) {
                case 0:
                    stdDeque.addFirst(i);
                    buggyDeque.addFirst(i);
                    break;
                case 1:
                    stdDeque.addLast(i);
                    buggyDeque.addLast(i);
                    break;
                case 2:
                    assertEquals(msg.toString(), stdDeque.removeFirst(), buggyDeque.removeFirst());
                    break;
                case 3:
                    assertEquals(msg.toString(), stdDeque.removeLast(), buggyDeque.removeLast());
                    break;
            }
        }

    }
}
