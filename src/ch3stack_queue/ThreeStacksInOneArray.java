package ch3stack_queue;

public class ThreeStacksInOneArray {
    private int[] arr;
    private int size;
    private int top1, top2, top3;

    public ThreeStacksInOneArray(int size) {
        this.size = size;
        arr = new int[size];
        top1 = -1; // Stack 1 grows towards the right
        top2 = size / 3 - 1; // Stack 2 grows towards the right
        top3 = size; // Stack 3 grows towards the left
    }

    public void push1(int value) {
        if (top1 < size / 3 - 1) {
            arr[++top1] = value;
        } else {
            System.out.println("Stack 1 Overflow");
        }
    }

    public void push2(int value) {
        if (top2 < size / 3 * 2 - 1) {
            arr[++top2] = value;
        } else {
            System.out.println("Stack 2 Overflow");
        }
    }

    public void push3(int value) {
        if (top3 > size / 3 * 2) {
            arr[--top3] = value;
        } else {
            System.out.println("Stack 3 Overflow");
        }
    }

    public int pop1() {
        if (top1 >= 0) {
            return arr[top1--];
        } else {
            System.out.println("Stack 1 Underflow");
            return -1;
        }
    }

    public int pop2() {
        if (top2 >= size / 3) {
            return arr[top2--];
        } else {
            System.out.println("Stack 2 Underflow");
            return -1;
        }
    }

    public int pop3() {
        if (top3 < size) {
            return arr[top3++];
        } else {
            System.out.println("Stack 3 Underflow");
            return -1;
        }
    }

    public int peek1() {
        if (top1 >= 0) {
            return arr[top1];
        } else {
            System.out.println("Stack 1 Empty");
            return -1;
        }
    }

    public int peek2() {
        if (top2 >= size / 3) {
            return arr[top2];
        } else {
            System.out.println("Stack 2 Empty");
            return -1;
        }
    }

    public int peek3() {
        if (top3 < size) {
            return arr[top3];
        } else {
            System.out.println("Stack 3 Empty");
            return -1;
        }
    }

    public static void main(String[] args) {
        ThreeStacksInOneArray stacks = new ThreeStacksInOneArray(9);

        stacks.push1(10);
        stacks.push1(20);
        System.out.println("Stack 1 Peek: " + stacks.peek1());
        System.out.println("Stack 1 Pop: " + stacks.pop1());
        System.out.println("Stack 1 Peek after Pop: " + stacks.peek1());


        stacks.push2(30);
        stacks.push2(40);
        System.out.println("Stack 2 Peek: " + stacks.peek2());
        System.out.println("Stack 2 Pop: " + stacks.pop2());
        System.out.println("Stack 2 Peek after Pop: " + stacks.peek2());


        stacks.push3(50);
        stacks.push3(60);
        System.out.println("Stack 3 Peek: " + stacks.peek3());
        System.out.println("Stack 3 Pop: " + stacks.pop3());
        System.out.println("Stack 3 Peek after Pop: " + stacks.peek3());
    }
}

       

