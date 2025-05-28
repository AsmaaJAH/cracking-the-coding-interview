/*
 * 
 * 4.6 Successor: Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a
binary search tree. You may assume that each node has a link to its parent.

note: this is not about finding the successor. this is about finding the next node in the in-order traversal.
 */
package ch4trees_graphs;

class TreeNode6 {
    int value;
    TreeNode6 left;
    TreeNode6 right;
    TreeNode6 parent;

    TreeNode6(int val) {
        this.value = val;
    }
}

public class InOrderSuccessor6 {

    public static TreeNode6 findSuccessor(TreeNode6 node) {
        if (node == null) return null;

        // Case 1: Has right child → leftmost of right subtree
        if (node.right != null) {
            return getLeftmost(node.right);
        }

        // Case 2: No right → go up until we find a parent for which node is a left child
        TreeNode6 current = node;
        TreeNode6 parent = node.parent;
        while (parent != null && parent.right == current) {
            current = parent;
            parent = parent.parent;
        }
        return parent;
    }

    private static TreeNode6 getLeftmost(TreeNode6 node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        System.out.println("Next node in in-order traversal:");
        /*
         * 
        20
       /  \
     10    30
       \
        15
         */
        TreeNode6 root = new TreeNode6(20);
        TreeNode6 node10 = new TreeNode6(10);
        TreeNode6 node30 = new TreeNode6(30);
        TreeNode6 node15 = new TreeNode6(15);

        root.left = node10;
        root.right = node30;
        node10.right = node15;

        node10.parent = root;
        node30.parent = root;
        node15.parent = node10;

        TreeNode6 testNode = node15;
        TreeNode6 successor = findSuccessor(testNode);
        if (successor != null) {
            System.out.println("Successor of " + testNode.value + " is: " + successor.value);
        } else {
            System.out.println("Successor of " + testNode.value + " is: null");
        }
    }
}

