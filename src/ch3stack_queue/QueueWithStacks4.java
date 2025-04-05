package ch3stack_queue;
import java.util.Stack;

public class QueueWithStacks4<T> {

	Stack<T> newestStack, oldestStack;
	
	public QueueWithStacks4() {
		newestStack = new Stack<T>();
		oldestStack = new Stack<T>();
	}
	
	public int size() {
		return newestStack.size() + oldestStack.size();
	}
	
	public void enqueue(T value) {
		newestStack.push(value);
	}
	

	private void moveElementsFromNewToOld() {
		if (oldestStack.isEmpty()) { 
			while (!newestStack.isEmpty()) {
				oldestStack.push(newestStack.pop());
			}
		}
	}
	
	public T peek() {
		moveElementsFromNewToOld();
		return oldestStack.peek(); 
	}
	
	public T dequeue() {
		moveElementsFromNewToOld();
		return oldestStack.pop(); 
	}
    public boolean isEmpty() {
        return newestStack.isEmpty() && oldestStack.isEmpty();
    }
    public static void main(String[] args) {
        QueueWithStacks4<Integer> queue = new QueueWithStacks4<>();
        System.out.println("Queue With two Stacks:");  

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("Peek: " + queue.peek());  
        System.out.println("Dequeue: " + queue.dequeue()); 
        System.out.println("Peek after Dequeue: " + queue.peek()); 

        queue.enqueue(40);
        System.out.println("Dequeue: " + queue.dequeue()); 
        System.out.println("Dequeue: " + queue.dequeue()); 
        System.out.println("Dequeue: " + queue.dequeue());  

        System.out.println("Is queue empty? " + queue.isEmpty()); // true
    }
}

