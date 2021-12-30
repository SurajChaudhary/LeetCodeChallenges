package challenges;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumChallenge {

    /**
     * Challenge:
     *
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     * You can return the answer in any order.
     *
     *
     * Example 1:
     *
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
     * Example 2:
     *
     * Input: nums = [3,2,4], target = 6
     * Output: [1,2]
     * Example 3:
     *
     * Input: nums = [3,3], target = 6
     * Output: [0,1]
     *
     *
     * Constraints:
     *
     * 2 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * Only one valid answer exists.
     *
     *
     * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
     * @param args
     */
    public static void main(String[] args) {
        int [] nums = {2,7,11,15};
        int target = 9;

        int[] result1 = twoSumApproach1(nums, target);
        System.out.println(Arrays.toString(result1));

        int[] result2 = twoSumApproach2(nums, target);
        System.out.println(Arrays.toString(result2));
    }

    // Approach 1 with 2 loops
    public static int[] twoSumApproach1(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
            for(int j= i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i,j};
                }
            }
        }
        return nums;
    }

    // Approach 2 with Map
    public static int[] twoSumApproach2(int[] nums, int target) {
       int [] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target-nums[i])) {
                result[0] = map.get(target-nums[i]);
                result[1] = i;
                break;
            } else {
                map.put(nums[i], i);
            }
        }
       return result;
    }
}
