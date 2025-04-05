package ch3stack_queue;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class SetOfStacks3 {
    private int capacityThresholdOfOneStack;
    private ArrayList<Stack<Integer>> stacksList = new ArrayList<>();

    public SetOfStacks3(int capacity) {
        if (capacity <= 0) {
            //throw new IllegalArgumentException("Capacity must be greater than zero.");  IllegalArgumentException extends RuntimeException Thrown to indicate that a method has been passed an illegal or inappropriate argument
        System.out.println("Capacity must be greater than zero.");
            return;
        }
        this.capacityThresholdOfOneStack = capacity;
    }

    public void push(int value) {
        Stack<Integer> last = getLastStack();
        if (last != null && last.size() < capacityThresholdOfOneStack) {
            last.push(value);
        } else {
            Stack<Integer> stack = new Stack<>();
            stack.push(value);
            stacksList.add(stack);
        }
    }

    public int pop() {
        Stack<Integer> last = getLastStack();
        if (last == null || last.isEmpty()) {
            throw new EmptyStackException();
        }
        int value = last.pop();
        if (last.isEmpty()) {
            stacksList.remove(stacksList.size() - 1);
        }
        return value;
    }

    public int peek() {
        Stack<Integer> last = getLastStack();
        if (last == null || last.isEmpty()) {
            throw new EmptyStackException();
        }
        return last.peek();
    }

    private Stack<Integer> getLastStack() {
        if (stacksList.isEmpty()) return null;
        return stacksList.get(stacksList.size() - 1);
    }


    public boolean isEmptyStackList() {
        return stacksList.isEmpty();
    }

    public int numberOfStacks() {
        return stacksList.size();
    }

    // Optional: popAt(index) - pop from a specific sub-stack
    public int popAt(int index) {
        if (index < 0 || index >= stacksList.size()) {
            throw new IndexOutOfBoundsException("Invalid stack index.");
        }
        Stack<Integer> stack = stacksList.get(index);
        int value = stack.pop();
        if (stack.isEmpty()) {
            stacksList.remove(index);
        }
        return value;
    }

    public static void main(String[] args) {
        SetOfStacks3 set = new SetOfStacks3(3); // each stack can hold 3 items max

        for (int i = 1; i <= 10; i++) {
            set.push(i);
            System.out.println("Pushed: " + i + " (Total Stacks: " + set.numberOfStacks() + ")");
        }

        while (!set.isEmptyStackList()) {
            System.out.println("Popped: " + set.pop() + " (Total Stacks: " + set.numberOfStacks() + ")");
        }
    }
}

