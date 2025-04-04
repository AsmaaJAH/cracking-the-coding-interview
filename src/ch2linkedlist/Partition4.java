package ch2linkedlist;
import java.util.LinkedList;

public class Partition4 {
        public static LinkedList<Integer> partition(LinkedList<Integer> list, int x) {
                LinkedList<Integer> less = new LinkedList<>();
                LinkedList<Integer> greater = new LinkedList<>();
                
                for (Integer num : list) {
                    if (num < x) {
                        less.add(num);
                    } else {
                        greater.add(num);
                    }
                }
                
                less.addAll(greater);
                return less;
        }
    
        public static void main(String[] args) {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(3);
            list.add(5);
            list.add(8);
            list.add(5);
            list.add(10);
            list.add(2);
            list.add(1);
            System.out.println("Partition:");

            System.out.println("Before partition: " + list);
    
            LinkedList<Integer> partitioned = partition(list, 5);
    
            System.out.println("After partition around 5: " + partitioned);
        }
    }
     
