package challenges;

import java.util.Arrays;

public class PlusOneChallenge {
    public static void main(String[] args) {
        int[] digits = {9,9,9,9};
        System.out.println(Arrays.toString(plusOne(digits)));
    }

    public static int[] plusOne(int[] digits) {
        for(int i=digits.length-1; i >=0; i--) {
            if(digits[i] == 9) {
                digits[i] = 0;
            }else {
                digits[i] = digits[i] + 1;
                break;
            }
        }
        if(digits[0] == 0) {
            int[] nums = new int[digits.length+1];
            nums[0] = 1;
            return nums;
        }
        return digits;
    }
}
