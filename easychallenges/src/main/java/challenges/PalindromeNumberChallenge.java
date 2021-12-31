package challenges;

public class PalindromeNumberChallenge {
    /**
     * Given an integer x, return true if x is palindrome integer.
     * <p>
     * An integer is a palindrome when it reads the same backward as forward. For example, 121 is palindrome while 123 is not.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: x = 121
     * Output: true
     * Example 2:
     * <p>
     * Input: x = -121
     * Output: false
     * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
     * Example 3:
     * <p>
     * Input: x = 10
     * Output: false
     * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
     * Example 4:
     * <p>
     * Input: x = -101
     * Output: false
     * <p>
     * <p>
     * Constraints:
     * <p>
     * -231 <= x <= 231 - 1
     * <p>
     * <p>
     * Follow up: Could you solve it without converting the integer to a string?
     */
    public static void main(String[] args) {
        int x = 121;
        System.out.println(isPalindrome(x) ? "Number is palindrome." : "Number is not palindrome.");
        x = -121;
        System.out.println(isPalindrome(x) ? "Number is palindrome." : "Number is not palindrome.");
        x = 10;
        System.out.println(isPalindrome(x) ? "Number is palindrome." : "Number is not palindrome.");
        x = -101;
        System.out.println(isPalindrome(x) ? "Number is palindrome." : "Number is not palindrome.");
    }

    private static boolean isPalindrome(int x) {
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;

        long reversedNum = 0;
        while (x > reversedNum) {
            reversedNum = (reversedNum * 10) + (x % 10);
            x = x / 10;
        }
        return (reversedNum == x || x == reversedNum / 10);
    }
}
