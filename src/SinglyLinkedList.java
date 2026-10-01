public class SinglyLinkedList {

    private static class Node {
        int value;
        Node next;

        Node() {}

        Node(int value) {
            this.value = value;
        }

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private int size;
    private Node head;

    /**
     * Создает пустой односвязный список.
     */
    public SinglyLinkedList() {
        this.size = 0;
        this.head = null;
    }

    /**
     * Добавляет элемент в конец списка.
     * Сложность: O(n), так как требуется дойти до последнего узла.
     *
     * @param value добавляемое значение
     */
    public void add(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Возвращает значение элемента по указанному индексу.
     * Сложность: O(n).
     *
     * @param index индекс элемента (начиная с 0)
     * @return значение элемента по индексу
     * @throws IndexOutOfBoundsException если индекс некорректен
     */
    public int get(int index) {
        checkIndex(index);

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.value;
    }

    /**
     * Удаляет элемент по указанному индексу и возвращает его значение.
     * Сложность: O(1) для первого элемента, O(n) для остальных.
     *
     * @param index индекс удаляемого элемента
     * @return удаленное значение
     * @throws IndexOutOfBoundsException если индекс некорректен
     */
    public int remove(int index) {
        checkIndex(index);

        int removedValue;

        if (index == 0) {
            removedValue = head.value;
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedValue = current.next.value;
            current.next = current.next.next;
        }

        size--;
        return removedValue;
    }

    /**
     * Возвращает текущее количество элементов в списке.
     *
     * @return размер списка
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли список.
     *
     * @return {@code true}, если список пуст; иначе {@code false}
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Вспомогательный метод для проверки границ индекса.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
    }
}
