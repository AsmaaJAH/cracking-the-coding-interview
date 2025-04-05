package ch3stack_queue;
import java.util.Stack;

public class SortStack5 {

    public static void sortStackUsingStack(Stack<Integer> originalStack) {
		Stack<Integer> tempStack = new Stack<Integer>();
		while(!originalStack.isEmpty()) {
			int tmpVariable = originalStack.pop();
			while(!tempStack.isEmpty() && tempStack.peek() > tmpVariable) {
				originalStack.push(tempStack.pop());
			}
			tempStack.push(tmpVariable);
		}
		//finishing and restoring elements in the original stack:
		while (!tempStack.isEmpty()) {
			originalStack.push(tempStack.pop());
		}
	}

    public static void main(String[] args) {
        System.out.println("Sorting Stack using only one additional stack:");
        Stack<Integer> stack = new Stack<>();
        stack.push(34);
        stack.push(3);
        stack.push(31);
        stack.push(98);
        stack.push(92);
        stack.push(23);

        System.out.println("Original Stack: " + stack);
        sortStackUsingStack(stack);
        System.out.println("Sorted Stack (smallest on top): ");
        while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
    }
}
