/*
 * 
 * 4.5 Validate BST: Implement a function to check if a binary tree is a binary search tree.
 * 
 */
package ch4trees_graphs;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.value = val;
        this.left = null;
        this.right = null;
    }
}

public class ValidateBST5 {

    public static boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    private static boolean validate(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;

        if ((min != null && node.value <= min) || (max != null && node.value >= max)) {
            return false;
        }

        // Left subtree must have values < node.value
        // Right subtree must have values > node.value
        return validate(node.left, min, node.value) &&
               validate(node.right, node.value, max);
    }

    public static void main(String[] args) {
        System.out.println("Validate BST:");
   /*
       10
      /  \
     5    15
         /  
        12  
   */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(12);

        System.out.println("Is Valid BST? " + isValidBST(root));  // true

   /*
       10
      /  \
     5    15
         /  
        6  
   */
        root.right.left = new TreeNode(6);
        System.out.println("Is Valid BST? " + isValidBST(root));  // false

    }
}
