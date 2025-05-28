/*
 * 
4.8 First Common Ancestor: Design an algorithm and write code to find the first common ancestor
of two nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
necessarily a binary search tree.
 * 
 */

package ch4trees_graphs;

class TreeNode8 {
    int value;
    TreeNode8 left;
    TreeNode8 right;
    TreeNode8 parent;

    public TreeNode8(int val) {
        this.value = val;
    }
}

public class FirstCommonAncestor8 {

    public static TreeNode8 commonAncestor(TreeNode8 first, TreeNode8 second) {
        int firstDepth = depth(first);
        int secondDepth = depth(second);

        // Bring both nodes to the same level
        if (firstDepth > secondDepth) {
            first = goUpBy(first, firstDepth - secondDepth);
        } else {
            second = goUpBy(second, secondDepth - firstDepth);
        }

        // Move up both nodes together
        while (first != second && first != null && second != null) {
            first = first.parent;
            second = second.parent;
        }

        return (first == null || second == null) ? null : first;
    }

    private static int depth(TreeNode8 node) {
        int depth = 0;
        while (node != null) {
            node = node.parent;
            depth++;
        }
        return depth;
    }

    private static TreeNode8 goUpBy(TreeNode8 node, int depthDifferent) {
        while (depthDifferent > 0 && node != null) {
            node = node.parent;
            depthDifferent--;
        }
        return node;
    }

    public static void main(String[] args) {
        System.out.println("4.8 First Common Ancestor:");
        /*


          1
         / \
        2   3
       / \
      4   5


         */
        TreeNode8 root = new TreeNode8(1);
        TreeNode8 a = new TreeNode8(2);
        TreeNode8 b = new TreeNode8(3);
        TreeNode8 c = new TreeNode8(4);
        TreeNode8 d = new TreeNode8(5);

        root.left = a;
        root.right = b;
        a.parent = root;
        b.parent = root;

        a.left = c;
        a.right = d;
        c.parent = a;
        d.parent = a;

        TreeNode8 result = commonAncestor(c, d);
        System.out.println("Common Ancestor of " + c.value + " and " + d.value + " is: " + (result != null ? result.value : "null")); //Common Ancestor of 4 and 5 is: 2
    }
}
