package stacks;

import java.util.Stack;

public class MinStack {

    class StackNode {
        int value;
        StackNode next;
    }

    StackNode head, tail;

    int size;


    /**
     * initialize your data structure here.
     */
    public MinStack() {
        size = 0;
    }

    public void push(int x) {
        StackNode node = new StackNode();
        node.value = x;

        if (isEmpty()) {
            head = tail = node;
        } else {
            head.next = node;
            head = node;
        }

        size++;
    }

    public void pop() {
        if (isEmpty()) return;

        if (size == 1) {
            head = tail = null;
        } else {
            StackNode prev = tail;

            while (prev.next != head) {
                prev = prev.next;
            }
            prev.next = null;
            head = prev;
        }
        size--;
    }

    public int top() {
        return head.value;
    }

    public int getMin() {
        int min = Integer.MAX_VALUE;
        StackNode current = tail;
        int i = 0;
        while (i < size) {
            min = Math.min(min, current.value);
            current = current.next;
            i++;
        }
        return min;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    static public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();

        String[] items = path.split("/");

        for (String item : items) {
            if (item.equals(".") || item.equals("")) {
                continue;
            } else if (item.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(item);
            }
        }

        Stack<String> resultStack = new Stack<>();

        while (!stack.isEmpty()) {
            resultStack.push(stack.pop());
        }

        StringBuilder output = new StringBuilder("/");
        while (!resultStack.isEmpty()) {
            output.append(resultStack.pop());
            if (resultStack.size() != 0) output.append("/");
        }

        return output.toString();
    }

    static public String removeKdigits(String num, int k) {

        if (num.length() == k) return "0";

        Stack<Integer> stack = new Stack<>();

        char[] numbers = num.toCharArray();

        for (char c : numbers) {
            int value = Character.getNumericValue(c);

            while (!stack.isEmpty() && stack.peek() > value && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(value);
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        Stack<Integer> solutionStack = new Stack<>();

        while (!stack.isEmpty()) {
            solutionStack.push(stack.pop());
        }

        String solution = "";
        while (!solutionStack.isEmpty()) {
            solution +=(solutionStack.pop());
        }


        while (solution.length()!= 1 && solution.startsWith("0")) {
            solution = solution.substring(1);
        }

        return solution;
    }

    static boolean isOperation(String c) {
        return (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/"));
    }

    static boolean isPriorityOperation(String c) {
        return (c.equals("*") || c.equals("/"));
    }

    static public int calculate(String s) {
        if (!s.contains("+") && !s.contains("-") && !s.contains("*") && !s.contains("/")) return Integer.parseInt(s.trim());

        Stack<Integer> numbersStack = new Stack<>();
        Stack<String> operationsStack = new Stack<>();


        s = s.trim().replaceAll(" ","");
        String[] nums = s.split("[\\+\\-\\*/]+");
        String[] ops = s.split("[\\d]+");

        int i =0;
        for (String op : ops) {
            if (isOperation(op)) {
                if (isPriorityOperation(op)) {
                    numbersStack.push(Integer.parseInt(nums[i]));
                    i++;
                    int secondNo = numbersStack.pop();
                    int firstNo = numbersStack.pop();

                    if (op.equals("*")) {
                        numbersStack.push(firstNo * secondNo);
                    } else {
                        numbersStack.push(firstNo / secondNo);
                    }
                } else {
                    operationsStack.push(op);
                    numbersStack.push(Integer.parseInt(nums[i]));
                    i++;
                }
            } else {
                numbersStack.push(Integer.parseInt(nums[i]));
                i++;
            }
        }

        Stack<Integer> tempNumbersStack = new Stack<>();
        Stack<String> tempOperationsStack = new Stack<>();

        while (!operationsStack.isEmpty()) {
            tempOperationsStack.push(operationsStack.pop());
        }
        operationsStack = tempOperationsStack;

        while (!numbersStack.isEmpty()) {
            tempNumbersStack.push(numbersStack.pop());
        }
        numbersStack = tempNumbersStack;

        while (!operationsStack.isEmpty()) {
            String  operation = operationsStack.pop();

            int firstNo =  numbersStack.pop();
            int secondNo =  numbersStack.pop();

            if (operation.equals("+")) {
                numbersStack.push(firstNo + secondNo);
            } else {
                numbersStack.push(firstNo - secondNo);
            }
        }

        return numbersStack.pop();
    }

    public static void main(String[] args) {
        System.out.println(calculate("2*3*4"));
        System.out.println(calculate("2*3+4"));
        System.out.println(calculate("1-1+1"));
        System.out.println(calculate("1 + 1"));
        System.out.println(calculate("  30"));
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate("3/2"));
        System.out.println(calculate("3+5 / 2"));
    }

}
