/*
 * 
List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes
at each depth (e.g., if you have a tree with depth D, you'll have D linked lists).
 * 
 */
package ch4trees_graphs;
import java.util.*;

class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.value = val;
    }
}

public class ListOfDepths3 {

    public static List<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
        List<LinkedList<TreeNode>> result = new ArrayList<>();

        if (root == null) return result;

        Queue<TreeNode> currentLevel = new LinkedList<>();
        currentLevel.add(root);

        while (!currentLevel.isEmpty()) {
            int currentSize = currentLevel.size();
            LinkedList<TreeNode> levelList = new LinkedList<>();

            for (int i = 0; i < currentSize; i++) {
                TreeNode node = currentLevel.poll();
                levelList.add(node);

                if (node.left != null) currentLevel.add(node.left);
                if (node.right != null) currentLevel.add(node.right);
            }

            result.add(levelList);
        }

        return result;
    }

    public static void main(String[] args) {
        /*
        Self-balancing binary search tree:
                 1
                / \
               2   3
              / \   \
             4   5   6
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        List<LinkedList<TreeNode>> levelLists = createLevelLinkedList(root);
        
        System.out.println("List of Depths:");
        int depth = 0;
        for (LinkedList<TreeNode> level : levelLists) {
            System.out.print("Depth " + depth + ": ");
            for (TreeNode node : level) {
                System.out.print(node.value + " ");
            }
            System.out.println();
            depth++;
        }
    }
}
