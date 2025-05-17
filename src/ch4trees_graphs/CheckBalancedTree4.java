/* 
Check Balanced: Implement a function to check if a binary tree is balanced. For the purposes of
this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any
node never differ by more than one.
*/

package ch4trees_graphs;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.value = val;
    }
}

public class CheckBalancedTree4 {

    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != Integer.MIN_VALUE;
    }

    private int checkHeight(TreeNode node) {
        if (node == null)
            return -1;

        int leftHeight = checkHeight(node.left);
        if (leftHeight == Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        int rightHeight = checkHeight(node.right);
        if (rightHeight == Integer.MIN_VALUE)
            return Integer.MIN_VALUE;

        if (Math.abs(leftHeight - rightHeight) > 1)
            return Integer.MIN_VALUE; // Current node not balanced

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        /*
         * 1
         * / \
         * 2 3
         * /
         * 4
         * /
         * 5
         */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);

        CheckBalancedTree4 checker = new CheckBalancedTree4();
        boolean result = checker.isBalanced(root);

        System.out.println("Check Balanced Tree:");
        System.out.println("Is tree balanced? " + result); // Output: false


        /*
            1
           / \
          2   3
         /
        4
        */

        TreeNode anotherRoot = new TreeNode(1);
        anotherRoot.left = new TreeNode(2);
        anotherRoot.right = new TreeNode(3);
        anotherRoot.left.left = new TreeNode(4);

        result = checker.isBalanced(anotherRoot);

        System.out.println("Is the second tree balanced after removing the last node? " + result); // Output: false
    }
}
