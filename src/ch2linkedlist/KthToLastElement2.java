package ch2linkedlist;

import java.util.LinkedList;

public class KthToLastElement2 {

    public static Integer kthToLast(LinkedList<Integer> list, int k) {
        if (k <= 0 || k > list.size()) return null;

        // The kth to last element is at index (length - k)
        return list.get(list.size() - k);
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        int[] array = {0, 1, 2, 3};

        for (int num : array) {
            list.add(num);
        }

        System.out.println("the kth to last element:");
        for (int i = 1; i <= array.length ; i++) {
            Integer node = kthToLast(list, i);
            String nodeValue = node == null ? "null" : "" + node;
            System.out.println(i + ": " + nodeValue);
        }
    }
}
