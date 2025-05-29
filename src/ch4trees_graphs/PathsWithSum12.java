/*
 * 
4.12 Paths with Sum: You are given a binary tree in which each node contains an integer value (which
might be positive or negative). Design an algorithm to count the number of paths that sum to a
given value. The path does not need to start or end at the root or a leaf, but it must go downwards
(traveling only from parent nodes to child nodes).
 * 
 */
package ch4trees_graphs;

public class PathsWithSum12 {
    
    public static class TreeNode12 {
        int data;
        TreeNode12 left;
        TreeNode12 right;

        public TreeNode12(int d) {
            this.data = d;
        }

        public void insertInOrder(int d) {
            if (d <= data) {
                if (left == null) left = new TreeNode12(d);
                else left.insertInOrder(d);
            } else {
                if (right == null) right = new TreeNode12(d);
                else right.insertInOrder(d);
            }
        }
    }

    public static int countPathsWithSum(TreeNode12 root, int targetSum) {
        if (root == null) return 0;

        int pathsFromRoot = countFromNode(root, targetSum, 0);

        int pathsOnLeft = countPathsWithSum(root.left, targetSum);
        int pathsOnRight = countPathsWithSum(root.right, targetSum);

        return pathsFromRoot + pathsOnLeft + pathsOnRight;
    }

    // Counts the number of paths with the given sum starting from the node
    private static int countFromNode(TreeNode12 node, int targetSum, int currentSum) {
        if (node == null) return 0;

        currentSum += node.data;

        int totalPaths = 0;
        if (currentSum == targetSum) {
            totalPaths++;
        }

        totalPaths += countFromNode(node.left, targetSum, currentSum);
        totalPaths += countFromNode(node.right, targetSum, currentSum);

        return totalPaths;
    }

    public static void main(String[] args) {
        System.out.println("4.12 Paths with Sum (Brute Force Algorithm) :");
        /*
         * 
         10
        /  \
       5   -3
      / \    \
     3   2    11
    / \   \
   3  -2   1 
         * 
         */
        TreeNode12 root = new TreeNode12(10);
        root.left = new TreeNode12(5);
        root.right = new TreeNode12(-3);
        root.left.left = new TreeNode12(3);
        root.left.right = new TreeNode12(2);
        root.right.right = new TreeNode12(11);
        root.left.left.left = new TreeNode12(3);
        root.left.left.right = new TreeNode12(-2);
        root.left.right.right = new TreeNode12(1);

        int targetSum = 8;

        int result = countPathsWithSum(root, targetSum);
        System.out.println("Number of paths with sum " + targetSum + ": " + result);
    }
}

/*
 * Complexity: runtime is O(N log N) in a balanced tree. O(N²) worst case in unbalanced tree.
*/