/*
 * 
 * 
4.10  Check Subtree: Tl and T2 are two very large binary trees, with Tl much bigger than T2. Create an 
algorithm to determine ifT2 is a subtree of Tl.
A tree T2 is a subtree of Tl if there exists a node n in Tl such that the subtree of n is identical to T2.
That is, if you cut off the tree at node n, the two trees would be identical.
 * 
 * 
 */

package ch4trees_graphs;

class TreeNode10 {
    int data;
    TreeNode10 left;
    TreeNode10 right;

    TreeNode10(int x) {
        data = x;
    }
}

public class CheckSubtree10 {
    public static boolean containsTree(TreeNode10 t1, TreeNode10 t2) {
        if (t2 == null) return true; // An empty tree is always a subtree
        if (t1 == null && t2 != null) return false; // Big tree is empty & small is not —> can't be a subtree

        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();

        getOrderString(t1, string1);
        getOrderString(t2, string2);

        return string1.indexOf(string2.toString()) != -1;
    }

    public static void getOrderString(TreeNode10 node, StringBuilder string) {
        if (node == null) {
            string.append("X "); // Null marker
            return;
        }

        string.append(node.data).append(" "); // Add root
        getOrderString(node.left, string);    // Add left
        getOrderString(node.right, string);   // Add right
    }
    
    public static void main(String[] args) {
        System.out.println("4.10 Check Subtree:");
        /* Build T1:
        --------------
            1
           / \
          2   3
         / \
        4   5

         */
        TreeNode10 T1 = new TreeNode10(1);
        T1.left = new TreeNode10(2);
        T1.right = new TreeNode10(3);
        T1.left.left = new TreeNode10(4);
        T1.left.right = new TreeNode10(5);

        /*  Build T2 (which is a subtree of T1):
        --------------
            2
           / \
          4   5

        */

        TreeNode10 T2 = new TreeNode10(2);
        T2.left = new TreeNode10(4);
        T2.right = new TreeNode10(5);

        boolean result = containsTree(T1, T2);
        System.out.println("T2 is a subtree of T1: " + result);
    }

}
/*
 * Complexity: O(n+m) where n & m are num of nodes
 */
