/**
 * Точка входа в программу.
 * Демонстрирует работу методов {@link #twoSum(int[], int)} и {@link #binarySearch(int[], int)}.
 */
void main() {
    System.out.println(Arrays.toString(twoSum(new int[]{7, 9, 11, 2}, 9)));
    System.out.println(binarySearch(new int[] { 2, 3, 4, 17, 22, 45, 100 }, 22));
}

/**
 * Находит два числа в массиве, сумма которых равна целевому значению,
 * и возвращает их индексы.
 *
 * @param numbers массив целых чисел
 * @param target целевая сумма
 * @return массив из двух индексов найденных элементов или пустой массив, если подходящая пара не найдена
 */
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

/**
 * Выполняет бинарный поиск целевого значения в отсортированном массиве.
 *
 * @param numbers отсортированный по возрастанию массив целых чисел
 * @param target искомое значение
 * @return индекс искомого элемента в массиве или {@code -1}, если элемент не найден
 * @throws IllegalArgumentException если переданный массив {@code numbers} равен {@code null}
 */
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

/**
 * Проверяет корректность расстановки круглых, квадратных и фигурных скобок в строке.
 * Строка считается валидной, если все открытые скобки закрыты соответствующими
 * закрывающими скобками в правильном порядке.
 *
 * @param s строка, содержащая символы скобок '(', ')', '{', '}', '[' и ']'
 * @return {@code true}, если скобочная последовательность корректна; иначе {@code false}
 */
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

/**
 * Альтернативная реализация проверки валидности скобочной последовательности
 * с использованием {@link Stack} и словаря соответствия закрывающих и открывающих скобок.
 *
 * @param s строка, содержащая символы скобок '(', ')', '{', '}', '[' и ']'
 * @return {@code true}, если скобочная последовательность корректна; иначе {@code false}
 */
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

/**
 * Проверяет, является ли заданное целое число палиндромом.
 * Число является палиндромом, если оно читается одинаково слева направо и справа налево.
 * Отрицательные числа палиндромами не являются.
 *
 * @param number проверяемое целое число
 * @return {@code true}, если число является палиндромом; иначе {@code false}
 */
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

/**
 * Подсчитывает количество симметричных чисел в диапазоне от {@code low} до {@code high} включительно.
 * Число считается симметричным, если оно имеет четное количество цифр и сумма цифр
 * первой половины равна сумме цифр второй половины.
 *
 * @param low нижняя граница диапазона (включительно)
 * @param high верхняя граница диапазона (включительно)
 * @return количество симметричных чисел в заданном диапазоне
 */
public int countSymmetricIntegers(int low, int high) {
    int count = 0;
    for (int i = low; i <= high; i++) {
        String s = String.valueOf(i);
        int len = s.length();

        if (len % 2 != 0) continue;

        int half = len / 2;
        int left = 0;
        int right = 0;

        for (int j = 0; j < half; j++) {
            left += s.charAt(j) - '0';
            right += s.charAt(half + j) - '0';
        }

        if (left == right) count++;
    }

    return count;
}

/**
 * Преобразует число {@code n} в систему счисления с основанием {@code k}
 * и вычисляет сумму его цифр в этой системе счисления.
 *
 * @param n исходное положительное целое число в десятичной системе счисления
 * @param k основание целевой системы счисления ({@code k >= 2})
 * @return сумма цифр числа {@code n} в системе счисления с основанием {@code k}
 */
public int sumBase(int n, int k) {
    int sum = 0;

    while (n > 0) {
        sum += n % k;
        n /= k;
    }

    return sum;
}

/**
 * Находит единственный уникальный элемент в массиве, где все остальные элементы
 * встречаются ровно по два раза.
 *
 * @param numbers массив целых чисел, в котором каждый элемент дублируется, кроме одного
 * @return уникальное число, встречающееся в массиве только один раз
 */
public int singleNumber(int[] numbers) {
    Set<Integer> seen = new HashSet<>();

    // 1 2 1 2 3 4 5 4 5
    for (int number : numbers) {
        if (seen.contains(number)) {
            seen.remove(number);
        } else {
            seen.add(number);
        }
    }

    return seen.iterator().next();
}

