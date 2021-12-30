package challenges;

public class ReverseIntegerChallenge {
    /**
     * Challenge:
     *
     * Given a signed 32-bit integer x, return x with its digits reversed.
     * If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
     *
     * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
     *
     * Example 1:
     *
     * Input: x = 123
     * Output: 321
     * Example 2:
     *
     * Input: x = -123
     * Output: -321
     * Example 3:
     *
     * Input: x = 120
     * Output: 21
     * Example 4:
     *
     * Input: x = 0
     * Output: 0
     *
     *
     * Constraints:
     *
     * -231 <= x <= 231 - 1
     */

    public static void main(String[] args) {
        String s = "Hi by   hello";
        char[] split = s.toCharArray();
        int length = split.length;

        int x = 9001;
        System.out.println(reverse(x));
    }

    public static int reverse(int x) {

        if (x == 0) return x;

        boolean isNegative = (x < 0);

        if (isNegative) x = (x * -1);

        if (x > 0 && x < 9) return isNegative ? -1*x : x;

        long reversedNum = 0;
        while (x > 0) {
            reversedNum = (reversedNum * 10) + (x%10);
            x = x/10;
        }

        if(reversedNum > Integer.MAX_VALUE) return 0;

        return isNegative ? (int)(-1*reversedNum) : (int)reversedNum;

    }
}
