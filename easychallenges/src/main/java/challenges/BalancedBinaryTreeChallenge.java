package challenges;

public class BalancedBinaryTreeChallenge {
    /**
     * Given a binary tree, determine if it is height-balanced.
     *
     * For this problem, a height-balanced binary tree is defined as:
     *
     * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [3,9,20,null,null,15,7]
     * Output: true
     * Example 2:
     *
     *
     * Input: root = [1,2,2,3,3,null,null,4,4]
     * Output: false
     * Example 3:
     *
     * Input: root = []
     * Output: true
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 5000].
     * -104 <= Node.val <= 104
     */
    public static void main(String[] args) {

    }
    public boolean isBalancedTopDown(TreeNode root) {
        if(root == null) return true;

         int left = height(root.left);
         int right = height(root.right);

         if(Math.abs(left-right) > 1) return false;
         return isBalancedTopDown(root.left) && isBalancedTopDown(root.right);
    }

    public boolean isBalancedBottomUp(TreeNode root) {
        if(root == null) return true;
        return heightWithBalanceFactor(root) != -1;
    }

    public int heightWithBalanceFactor(TreeNode root) {
        if(root == null) return 0;
        int left = heightWithBalanceFactor(root.left);
        int right = heightWithBalanceFactor(root.right);
        int balanceFactor = Math.abs(left-right);

        if(balanceFactor > 1 || left == -1 || right == -1) return -1;
        return 1 + Math.max(left,right);
    }

    public int height(TreeNode root) {
        if(root == null) return 0;
        return 1 + Math.max(height(root.left),
                height(root.right));
    }
}
