package challenges;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInorderTraversalChallenge {
    /**
     * Given the root of a binary tree, return the inorder traversal of its nodes' values.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * <p>
     * Input: root = [1,null,2,3]
     * Output: [1,3,2]
     * Example 2:
     * <p>
     * Input: root = []
     * Output: []
     * Example 3:
     * <p>
     * Input: root = [1]
     * Output: [1]
     * Example 4:
     * <p>
     * <p>
     * Input: root = [1,2]
     * Output: [2,1]
     * Example 5:
     * <p>
     * <p>
     * Input: root = [1,null,2]
     * Output: [1,2]
     * <p>
     * <p>
     * Constraints:
     * <p>
     * The number of nodes in the tree is in the range [0, 100].
     * -100 <= Node.val <= 100
     * <p>
     * <p>
     * Follow up: Recursive solution is trivial, could you do it iteratively?
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2, new TreeNode(3), null);

        System.out.println(inorderTraversal(root));
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> output = new ArrayList<>();
        if (root == null) return output;
        traverse(output, root);
        return output;
    }

    private static void traverse(List<Integer> output, TreeNode root) {
        if (root == null) return;
        traverse(output, root.left);
        output.add(root.val);
        traverse(output, root.right);
    }
}
