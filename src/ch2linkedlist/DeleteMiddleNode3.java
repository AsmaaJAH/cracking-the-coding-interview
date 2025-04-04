package ch2linkedlist;
import java.util.LinkedList;


public class DeleteMiddleNode3 {
    
    public static boolean deleteNode(LinkedList<Integer> list, int index) {
            if(index <= 0 || index >= list.size()) {
                return false;
            }
            //System.out.println(list.size());
            Integer data= list.get(index);
            list.set(index, data);
            list.remove(index+1);
            return true;
        
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i); 
        }
        System.out.println("Delete middle node");

        System.out.println("Before deletion: " + list);
        
        boolean success = deleteNode(list, 4);
        if(success){
            System.out.println("After deletion: " + list);

        }else{
            System.out.println("Deletion failed");
        }
    }
}