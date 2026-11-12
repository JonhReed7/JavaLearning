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

    static class MinStack {
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
            if (mins.peek() == value) mins.pop();
        }

        public int top() {
            return numbers.peek();
        }

        public int getMin() {
            return mins.peek();
        }
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                ListNode first = head;
                ListNode second = slow;

                while (first != second) {
                    first = first.next;
                    second = second.next;
                }

                return first;
            }
        }

        return null;
    }

    // зарефакторить
    public int fib(int n) {
        if (n == 0) return 0;
        else if (n == 1 || n == 2) return 1;
        else return fib(n - 1) + fib(n - 2);
    }

    //
    public boolean isPowerOfTwo(int n) {
        return false;
    }

    public void BFS(int[][] graph, int start) {
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> seen = new HashSet<>();
        q.add(start);

        while (!q.isEmpty()) {
            start = q.remove();
            seen.add(start);
            int[] neigh = graph[start];

            for (int n : neigh) {
                if (seen.contains(n)) continue;
                q.add(n);
            }
        }
    }

    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String t : tokens) {
            if (t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                if (t.equals("+")) stack.push(a + b);
                else if (t.equals("-")) stack.push(a - b);
                else if (t.equals("*")) stack.push(a * b);
                else stack.push(a / b);
            } else {
                stack.push(Integer.parseInt(t));
            }
        }
        return stack.pop();
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean validPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return isPalindrome(s, l + 1, r) || isPalindrome(s, l, r - 1);
            }
            l++;
            r--;
        }
        return true;
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) return false;
        }
        return true;
    }

    public int[] searchRange(int[] nums, int target) {
        int first = lowerBound(nums, target);
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        }
        int last = lowerBound(nums, target + 1) - 1;
        return new int[]{first, last};
    }

    private int lowerBound(int[] nums, int x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] < x) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1));
                for (int a : left) {
                    for (int b : right) {
                        if (c == '+') result.add(a + b);
                        else if (c == '-') result.add(a - b);
                        else result.add(a * b);
                    }
                }
            }
        }
        if (result.isEmpty()) {
            result.add(Integer.parseInt(expression));
        }
        return result;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 0;
        for (int p : piles) r = Math.max(r, p);

        while (l < r) {
            int mid = (l + r) / 2;
            if (canFinish(piles, mid, h)) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    private boolean canFinish(int[] piles, int speed, int h) {
        long hours = 0;
        for (int p : piles) {
            hours += (p + speed - 1) / speed;
        }
        return hours <= h;
    }

    public int find(HashMap<Integer, Integer> graph) {
        HashSet<Integer> visited = new HashSet<>();
        int counter = 0;
        visited.add(graph.get(0));

        while (visited.size() != graph.size()){
            // todo
        }

        return 1;
    }

    static class TreeNode {
        protected TreeNode left;
        protected TreeNode right;
        protected TreeNode previous;
        protected int value;
    }

    public TreeNode findMost(TreeNode node) {
        TreeNode current = node;

        while (current != null) {
            if (current.left != null) current = current.left;
        }
        // directions
        return current;
    }

    public boolean isValid(TreeNode node) {

    }

    public boolean isValidI(TreeNode node, int min, int max) {
        if (node.value < min || node.value > max) return false;

        return isValidI(node, min, node.value) && isValidI(node, node.value, max);
    }

    public void inOrder(TreeNode node) {
        if (node == null) return;

        inOrder(node.left);
        inOrder(node.right);
    }
}
