package challenges;

import java.util.Arrays;

public class MergeSortedArrayChallenge {

    /**
     * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
     *
     * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
     *
     * The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
     *
     *
     *
     * Example 1:
     *
     * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     * Output: [1,2,2,3,5,6]
     * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
     * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
     * Example 2:
     *
     * Input: nums1 = [1], m = 1, nums2 = [], n = 0
     * Output: [1]
     * Explanation: The arrays we are merging are [1] and [].
     * The result of the merge is [1].
     * Example 3:
     *
     * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
     * Output: [1]
     * Explanation: The arrays we are merging are [] and [1].
     * The result of the merge is [1].
     * Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
     *
     *
     * Constraints:
     *
     * nums1.length == m + n
     * nums2.length == n
     * 0 <= m, n <= 200
     * 1 <= m + n <= 200
     * -109 <= nums1[i], nums2[j] <= 109
     *
     *
     * Follow up: Can you come up with an algorithm that runs in O(m + n) time?
     */

    public static void main(String[] args) {
        //[4,0,0,0,0,0]
        //1
        //[1,2,3,5,6]
        //5
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;

        merge(nums1, m, nums2, n);

    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        if((nums1 == null && nums2 == null) || (nums1.length == 0 && nums2.length == 0)) return;

        if(nums1.length < (m+n)) return;

        if(nums1.length == nums2.length) {
            for(int i = 0; i < nums1.length;i++) nums1[i] = nums2[i];
            System.out.println(Arrays.toString(nums1));
            return;
        }

        if(nums2 == null || nums2.length == 0) return;

        int idx1 = m-1;
        int idx2 = n-1;
        int end = nums1.length-1;

        while(idx1 >= 0 && idx2 >= 0) {
            if(nums1[idx1] >= nums2[idx2]) {
                nums1[end] = nums1[idx1];
                idx1--;
            } else if(nums1[idx1] < nums2[idx2]) {
                nums1[end] = nums2[idx2];
                idx2--;
            }
            end--;
        }
        if(idx2 >=0) {
            for(int i=0; i <=idx2;i++) {
                nums1[i] = nums2[i];
            }
        }

        System.out.println(Arrays.toString(nums1));

    }
}
