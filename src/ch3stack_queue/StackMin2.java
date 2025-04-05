package ch3stack_queue;
import java.util.Stack;

class Node {
    public int data;
    public int min;
    public Node(int v, int min){
        data = v;
        this.min = min;
    }
}
public class StackMin2 extends Stack<Node> {
    public void push(int newData) {
        int min = Math.min(newData, getCurrentMin());
        super.push(new Node(newData, min));
    }
    
    public int getCurrentMin() {
    	if (this.isEmpty()) {
    		return Integer.MAX_VALUE;
    	} else {
    		return peek().min;
    	}
    }
    public static void main(String[] args) {
		StackMin2 stack = new StackMin2();
        System.out.println("Stack Min in O(1):");

		int[] array = {5, 1, 4, 1};
		for (int value : array) {
			stack.push(value);
			System.out.print(value + ", ");
		}
		System.out.println('\n');
		for (int i = 0; i < array.length -1; i++) {
			System.out.println("Popped " + stack.pop().data );
			System.out.println("New min is " + stack.getCurrentMin());
		}
	}
}

