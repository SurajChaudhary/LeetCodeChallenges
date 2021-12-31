package challenges;

public class MaximumSubArrayChallenge {

    /**
     * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
     * <p>
     * A subarray is a contiguous part of an array.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
     * Output: 6
     * Explanation: [4,-1,2,1] has the largest sum = 6.
     * Example 2:
     * <p>
     * Input: nums = [1]
     * Output: 1
     * Example 3:
     * <p>
     * Input: nums = [5,4,-1,7,8]
     * Output: 23
     * <p>
     * <p>
     * Constraints:
     * <p>
     * 1 <= nums.length <= 3 * 104
     * -105 <= nums[i] <= 105
     * <p>
     * <p>
     * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
     */

    public static void main(String[] args) {
        int[] nums = {1};
        System.out.println(maxSubArrayByKadaneAlgorithm(nums));

    }

    public static int maxSubArrayByKadaneAlgorithm(int[] nums) {

        int maxSum = Integer.MIN_VALUE;

        int currSum = 0;

        for (int i = 0; i <= nums.length - 1; i++) {
            currSum = Math.max((currSum + nums[i]), nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;

    }
}
