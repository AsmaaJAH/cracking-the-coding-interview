package ch2linkedlist;
//import java.util.Collections;
import java.util.LinkedList;

public class Palindrome6 {
     public static boolean isPalindrome(LinkedList<Integer> list) {
        //LinkedList<Integer> reversed = new LinkedList<>(list);
        ///Collections.reverse(reversed);
        
        LinkedList<Integer> reversed = reverse(list);
        return list.equals(reversed);
    }

    public static LinkedList<Integer> reverse(LinkedList<Integer> list) {
        LinkedList<Integer> reversed = new LinkedList<>();
        for(Integer value : list){
            reversed.addFirst(value);
        }
        return reversed;
     }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(0);
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(0);

        System.out.println("Palindrome:");
        System.out.println("List: " + list);
        System.out.println("Is Palindrome? " + isPalindrome(list));
    }
}
