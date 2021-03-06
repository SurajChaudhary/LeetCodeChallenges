package challenges;

import java.util.Stack;

public class MinimumDepthOfBinaryTreeChallenge {
    /**
     * Given a binary tree, find its minimum depth.
     * <p>
     * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
     * <p>
     * Note: A leaf is a node with no children.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: root = [3,9,20,null,null,15,7]
     * Output: 2
     * Example 2:
     * <p>
     * Input: root = [2,null,3,null,4,null,5,null,6]
     * Output: 5
     * <p>
     * <p>
     * Constraints:
     * <p>
     * The number of nodes in the tree is in the range [0, 105].
     * -1000 <= Node.val <= 1000
     */

    public static void main(String[] args) {
        Stack st = new Stack();
        st.peek();

    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        if (left == 0 || right == 0) {
            return 1 + Math.max(left, right);
        }

        return 1 + Math.min(left, right);
    }

    public int minDepth1(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null && root.right == null) return 1;
        if (root.left == null) return 1 + minDepth(root.right);
        if (root.right == null) return 1 + minDepth(root.left);

        return 1 + Math.min(minDepth(root.left), minDepth(root.right));

    }
}
