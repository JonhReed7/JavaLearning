void main() {
    System.out.println(Arrays.toString(twoSum(new int[]{7, 9, 11, 2}, 9)));
    System.out.println(binarySearch(new int[] { 2, 3, 4, 17, 22, 45, 100 }, 22));
}

public static int[] twoSum (int[] numbers, int target) {
    Map<Integer, Integer> diffMap = new HashMap<>();

    for (int index = 0; index < numbers.length; index++) {
        int value = numbers[index];
        Integer prevIndex = diffMap.get(value);

        if (prevIndex != null) {
            return new int[] { prevIndex, index };
        }
        diffMap.put(target - value, index);
    }
    return new int[] {};
}

/** Бинарный поиск, необходим отсортированный массив */
public static int binarySearch (int[] numbers, int target) {
    if (numbers == null) {
        throw new IllegalArgumentException("Входящий массив не может быть null.");
    }

    int low = 0;
    int high = numbers.length - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;
        int guess = numbers[mid];

        if (guess == target) {
            return mid;
        } else if (guess > target) {
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }

    return -1;
}

public boolean isValid(String s) {
    if (s == null || s.length() % 2 != 0) {
        return false;
    }

    Stack<Character> stack = new Stack<>();

    for (char c : s.toCharArray()) {
        if (c == '(') {
            stack.push(')');
        } else if (c == '[') {
            stack.push(']');
        } else if (c == '{') {
            stack.push('}');
        } else {
            if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
    }

    return stack.isEmpty();
}

public boolean isValid2(String s) {
    Stack<Character> stack = new Stack<>();
    Map<Character, Character> pairs = new HashMap<>(Map.of(
            ')', '(',
            ']', '[',
            '}', '{'
    ));

    for (char c: s.toCharArray()) {
        if (!pairs.containsKey(c)) {
            stack.push(c);
        } else {
            if (stack.isEmpty()) {
                return false;
            }

            char lastEl = stack.pop();
            if (pairs.get(c) != lastEl) {
                return false;
            }
        }
    }

    return stack.isEmpty();
}

public boolean isPalindrome(int number) {
    if (number < 0) return false;
    if (number < 10) return true;

    int original = number;
    int reversed = 0;

    while (original > 0) {
        int digit = original % 10;
        reversed = reversed * 10 + digit;
        original /= 10;
    }

    return number == reversed;
}

