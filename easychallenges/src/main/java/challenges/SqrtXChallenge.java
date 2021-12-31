package challenges;

public class SqrtXChallenge {
    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
    }

    public static int mySqrt(int x) {
        int start = 1;
        int end = x;
        int res = 0;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (mid * mid == x) {
                return mid;
            } else if (mid > x / mid) {
                end = mid - 1;
            } else {
                start = mid + 1;
                res = mid;
            }

        }
        return res;

    }
}
