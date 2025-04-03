package ch2linkedlist;
import java.util.HashSet;
import java.util.LinkedList;

public class RemoveDups1 {
    public static void deleteDuplicates(LinkedList<Integer> list) {
        HashSet<Integer> set = new HashSet<>();
        int i = 0;

        while (i < list.size()) {
            int elem = list.get(i);
            if (set.contains(elem)) {
                list.remove(i);  
            } else {
                set.add(elem);
                i++; 
            }
        }

        System.out.print("\nAfter function call: ");
        for (Integer num : list) {
            System.out.print(" " + num);
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(4);
        list.add(4);
        list.add(5);
        list.add(6);

        System.out.print("Before function call: ");
        for (Integer num : list) {
            System.out.print(" " + num);
        }

        deleteDuplicates(list);
    }
}
