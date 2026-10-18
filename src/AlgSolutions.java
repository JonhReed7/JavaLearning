import java.util.*;

public class AlgSolutions {
    public int[] twoSum2(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else return new int[] { left + 1, right + 1 };
        }

        return new int[] {};
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }


    // два указателя

    public int maxProfit(int[] prices) {
        int left = 0;
        int right = 1;
        int maxProfit = 0;

        while (right < prices.length) {
            if (prices[left] < prices[right]) {
                int currentProfit = prices[right] - prices[left];
                maxProfit = Math.max(maxProfit, currentProfit);
            } else left = right;

            right++;
        }

        return maxProfit;
    }

    static class MyQueue {
        private final Deque<Integer> in = new ArrayDeque<>();
        private final Deque<Integer> out = new ArrayDeque<>();

        public MyQueue() {}

        public void push(int x) {
            in.push(x);
        }

        public int pop() {
            move();
            if (out.isEmpty()) throw new NoSuchElementException("Queue is empty");
            return out.pop();
        }

        public int peek() {
            move();
            if (out.isEmpty()) throw new NoSuchElementException("Queue is empty");
            return out.peek();
        }

        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }

        private void move() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) out.push(in.pop());
            }
        }
    }

    static class MyStack {
        private Queue<Integer> q1 = new ArrayDeque<>();
        private Queue<Integer> q2 = new ArrayDeque<>();

        public MyStack() {}

        public void push(int x) {
            q2.add(x);
            while(!q1.isEmpty()) q2.add(q1.remove());
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
        }

        public int pop() {
            if (q1.isEmpty()) throw new NoSuchElementException("Stack is empty");
            else return q1.remove();
        }

        public int top() {
            return q1.element();
        }

        public boolean empty() {
            return q1.isEmpty();
        }
    }

    static class MyStack2 {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();

        public MyStack2() {}

        public void push(int x) {
            q1.add(x);
        }

        public int pop() {
            if (q1.isEmpty()) throw new NoSuchElementException("Stack is empty");
            for (int i = 0; i < q1.size() - 1; i++) q2.add(q1.remove());
            int value = q1.remove();
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            return value;
        }

        public int top() {
            // нет смысла, в любом случае н
            if (q1.isEmpty()) throw new NoSuchElementException("Stack is empty");
            for (int i = 0; i <= q1.size() - 1; i++) q2.add(q1.remove());
            int value = q1.element();
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
            return value;
        }

        public boolean empty() {
            return q1.isEmpty();
        }
    }

    static class MyStack3 {
        private final Queue<Integer> q = new ArrayDeque<>();

        public MyStack3() {}

        public void push(int x) {
            q.add(x);
        }

        public int pop() {
            if (q.isEmpty()) throw new NoSuchElementException("Stack is empty");
            int size = q.size();
            for (int i = 0; i < size - 1; i++) q.add(q.remove());
            return q.remove();
        }

        public int top() {
            if (q.isEmpty()) throw new NoSuchElementException("Stack is empty");
            int size = q.size();
            for (int i = 0; i < size - 1; i++) q.add(q.remove());
            int result = q.element(); // лучше через peek(); но линтер ругается на гипотетический null
            q.add(q.remove());
            return result;
        }

        public boolean empty() {
            return q.isEmpty();
        }
    }

    class MinStack {
        private final Deque<Integer> numbers = new ArrayDeque<>();
        private final Deque<Integer> mins = new ArrayDeque<>();

        public MinStack() {

        }

        public void push(int value) {
            numbers.push(value);
            if (mins.isEmpty() || mins.peek() >= value) mins.push(value);
        }

        public void pop() {
            int value = numbers.pop();
            if (value == mins.peek()) mins.pop();
        }

        public int top() {
            return numbers.peek();
        }

        public int getMin() {
            return mins.peek();
        }
    }
}
