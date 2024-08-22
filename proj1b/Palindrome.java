import java.util.LinkedList;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if (word.isEmpty()) {
            return null;
        }
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            deque.addLast(ch);
        }
        return deque;
    }

    private boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.size() <= 1) {
            return true;
        }
        Character first = deque.removeFirst();
        Character last = deque.removeLast();
        if (first != last) {
            return false;
        }
        return isPalindromeHelper(deque);
    }

    public boolean isPalindrome(String word) {
        if (word.length() <= 1) {
            return true;
        }
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque);
    }

    private boolean isPalindromeHelper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() <= 1) {
            return true;
        }
        Character first = deque.removeFirst();
        Character last = deque.removeLast();
        if (!cc.equalChars(first, last)) {
            return false;
        }
        return isPalindromeHelper(deque, cc);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        }
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque, cc);
    }


}
