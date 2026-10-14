import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Stack;

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
}
