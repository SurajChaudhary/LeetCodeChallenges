package challenges;

import java.util.Arrays;

public class PascalTriangleIIChallenge {

    public static void main(String[] args) {

        int rowIndex = 3;

        Integer[] result = new Integer[rowIndex + 1];
        Arrays.fill(result, 0);
        result[0] = 1;

        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i; j > 0; j--) {
                result[j] = result[j] + result[j - 1];
            }
        }

        System.out.println(Arrays.toString(result));

    }
}
