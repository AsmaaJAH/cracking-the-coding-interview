/*
 * 
4.9 BST Sequences: A binary search tree was created by traversing through an array from left to right
and inserting each element. Given a binary search tree with distinct elements, print all possible
arrays that could have led to this tree.
EXAMPLE
Input:     2
          /  \
         1    3

Output: {2, 1, 3}, {2, 3, 1}
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

public class BSTSequences9 {

    public static List<List<Integer>> allSequences(TreeNode node) {
        List<List<Integer>> result = new ArrayList<>();

        if (node == null) {
            result.add(new LinkedList<>());
            return result;
        }

        List<Integer> prefix = new LinkedList<>();
        prefix.add(node.value);

        // Recurse on left and right subtrees
        List<List<Integer>> leftSeq = allSequences(node.left);
        List<List<Integer>> rightSeq = allSequences(node.right);

        // Weave together each pair of left and right sequences
        for (List<Integer> left : leftSeq) {
            for (List<Integer> right : rightSeq) {
                List<List<Integer>> weaved = new ArrayList<>();
                weaveLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }

        return result;
    }

    private static void weaveLists(List<Integer> first, List<Integer> second,
                                   List<List<Integer>> results, List<Integer> prefix) {
        if (first.isEmpty() || second.isEmpty()) {
            List<Integer> result = new LinkedList<>(prefix);
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        // Recurse with head of first added to the prefix
        int headFirst = first.remove(0);
        prefix.add(headFirst);
        weaveLists(first, second, results, prefix);
        prefix.remove(prefix.size() - 1);
        first.add(0, headFirst);

        // Recurse with head of second added to the prefix
        int headSecond = second.remove(0);
        prefix.add(headSecond);
        weaveLists(first, second, results, prefix);
        prefix.remove(prefix.size() - 1);
        second.add(0, headSecond);
    }

    public static void main(String[] args) {
        System.out.println("4.9 BST Sequences:");
        /*
        *
        Input:    2
                /  \
               1    3

        Output: [2, 1, 3]
                [2, 3, 1]
         * 
         */
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        List<List<Integer>> results = allSequences(root);

        for (List<Integer> seq : results) {
            System.out.println(seq);
        }
    }
}
