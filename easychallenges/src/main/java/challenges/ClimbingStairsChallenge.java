package challenges;

public class ClimbingStairsChallenge {

    /**
     * You are climbing a staircase. It takes n steps to reach the top.
     * <p>
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: n = 2
     * Output: 2
     * Explanation: There are two ways to climb to the top.
     * 1. 1 step + 1 step
     * 2. 2 steps
     * Example 2:
     * <p>
     * Input: n = 3
     * Output: 3
     * Explanation: There are three ways to climb to the top.
     * 1. 1 step + 1 step + 1 step
     * 2. 1 step + 2 steps
     * 3. 2 steps + 1 step
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= n <= 45
     */
    public static void main(String[] args) {
        int n = 45;
        System.out.println(climbStairs1(n));
        System.out.println(climbStairs1(n));

    }

    public static int climbStairs1(int n) {
        // Base conditions
        if (n < 0) return -1;
        if (n == 0 || n == 1) return 1;
        if (n == 2) return 2;

        // Memory array
        int[] mem = new int[n + 1];
        mem[0] = 1;
        mem[1] = 1;
        mem[2] = 2;

        for (int i = 3; i <= n; i++) {
            mem[i] = mem[i - 1] + mem[i - 2];
        }
        return mem[n];
    }

    public static int climbStairs(int n) {
        int[] mem = new int[n + 1];
        return rec(n, mem);
    }

    private static int rec(int n, int[] mem) {
        if (n <= 1) return 1;
        if (n == 2) return 2;
        if (mem[n] > 0) return mem[n];
        int res = rec(n - 2, mem) + rec(n - 1, mem);
        mem[n] = res;
        return res;
    }
}
