/*
Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an
algorithm to create a binary search tree with minimal height.
*/

package ch4trees_graphs;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.value = val;
        this. left = null;
        this. right = null;
    }
}

public class MinimalTree {

    public static TreeNode createMinimalBST(int[] array) {
        return createMinimalBST(array, 0, array.length - 1);
    }

    private static TreeNode createMinimalBST(int[] arr, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(arr[mid]); 
        node.left = createMinimalBST(arr, start, mid - 1);
        node.right = createMinimalBST(arr, mid + 1, end);
        return node;
    }

    public static void printInOrder(TreeNode node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.value + " ");
        printInOrder(node.right);
    }

    public static void main(String[] args) {
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7}; //int[] sortedArray = [1, 2, 3, 4, 5, 6, 7];  // ❌ Compilation Error in java
        TreeNode root = createMinimalBST(sortedArray);
        System.out.println("Minimal Height BST");
        System.out.println("In-order traversal of minimal height BST:");
        printInOrder(root);
    }
}
