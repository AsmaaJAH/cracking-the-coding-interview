/*
 * 
4.11 Random Node: You are implementing a binary search tree class from scratch, which, in addition
to insert, find, and delete, has a method getRandomNode() which returns a random node
from the tree. All nodes should be equally likely to be chosen. Design and implement an algorithm
for getRandomNode, and explain how you would implement the rest of the methods.

*
*/
package ch4trees_graphs;
import java.util.Random;

class TreeNode11 {
    private int data;
    public TreeNode11 left;
    public TreeNode11 right;
    private int size = 0;

    public TreeNode11(int d) {
        data = d;
        size = 1;
    }

    public TreeNode11 getRandomNode() {
        int leftSize = (left == null) ? 0 : left.size();
        Random random = new Random();
        int index = random.nextInt(size); // 0 to size-1
        if (index < leftSize) {
            return left.getRandomNode();
        } else if (index == leftSize) {
            return this;
        } else {
            return right.getRandomNode();
        }
    }

    public void insertInOrder(int d) {
        if (d <= data) {
            if (left == null) {
                left = new TreeNode11(d);
            } else {
                left.insertInOrder(d);
            }
        } else {
            if (right == null) {
                right = new TreeNode11(d);
            } else {
                right.insertInOrder(d);
            }
        }
        size++;
    }

    public int size() {
        return size;
    }

    public int data() {
        return data;
    }

    public TreeNode11 find(int d) {
        if (d == data) {
            return this;
        } else if (d < data) {
            return left != null ? left.find(d) : null;
        } else {
            return right != null ? right.find(d) : null;
        }
    }
}

public class RandomNode11 {
    public static void main(String[] args) {
        System.out.println("4.11 Random Node:");
        /*
          10
         /  \
        5    15
       / \   / \
       3  7 12 18
         */
        TreeNode11 root = new TreeNode11(10);
        root.insertInOrder(5);
        root.insertInOrder(15);
        root.insertInOrder(3);
        root.insertInOrder(7);
        root.insertInOrder(12);
        root.insertInOrder(18);

        System.out.println("Tree size: " + root.size());//output: 7

        // Get 5 random nodes
        System.out.println("Random nodes:");
        for (int i = 0; i < 5; i++) {
            TreeNode11 rand = root.getRandomNode();
            System.out.println("Random Node: " + rand.data());
        }

        // Try to find:
        TreeNode11 found = root.find(7);
        System.out.println("Find 7? " + (found != null ? "Found: "+found.data() : "Not Found!"));//output:"Find 7? Found: 7"
    }
}

/*
 * Complexity: O(log N)
 */