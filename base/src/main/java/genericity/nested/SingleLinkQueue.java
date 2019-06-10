package genericity.nested;

/**
 * 嵌套类型可以被声明为拥有其自己的类型变量的泛型.
 *
 * 因为静态成员不可以引用它们自己的类中的类型变量, 所以任何静态嵌套类型中的
 * 类型变量与任何外部类型的类型变量之间都有所区别, 即使它们拥有相同的名字
 *
 * 这里的两个E是不同的类型, 没有必然的联系
 * 两个不同的类型在head和tail的声明中被关联起来
 * 就像 {@link SingleLinkQueue1}中, 两个类型名不同, 和{@link SingleLinkQueue}都是一样的效果
 */
@SuppressWarnings("Duplicates")
public class SingleLinkQueue<E> {
    static class Cell<E> {
        private Cell<E> next;
        private E element;

        public Cell(E element) {
            this.element = element;
        }

        public Cell(Cell<E> next, E element) {
            this.next = next;
            this.element = element;
        }

        public Cell<E> getNext() {
            return next;
        }

        public void setNext(Cell<E> next) {
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }
    }

    protected Cell<E> head;
    protected Cell<E> tail;

    public void add(E item) {
        Cell<E> cell = new Cell<>(item);
        if (null == tail) {
            head = tail = cell;
        } else {
            tail.setNext(cell);
            tail = cell;
        }
    }

    public E remove() {
        if (null == head) {
            return null;
        }
        Cell<E> cell = head;
        head = head.getNext();
        if (head == null) {
            tail = null; // empty queue
        }
        return cell.getElement();
    }
}

@SuppressWarnings("Duplicates")
class SingleLinkQueue1<E> {
    static class Cell<T> {
        private Cell<T> next;
        private T element;

        public Cell(T element) {
            this.element = element;
        }

        public Cell(Cell<T> next, T element) {
            this.next = next;
            this.element = element;
        }

        public Cell<T> getNext() {
            return next;
        }

        public void setNext(Cell<T> next) {
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }
    }

    protected Cell<E> head;
    protected Cell<E> tail;

    public void add(E item) {
        Cell<E> cell = new Cell<>(item);
        if (null == tail) {
            head = tail = cell;
        } else {
            tail.setNext(cell);
            tail = cell;
        }
    }

    public E remove() {
        if (null == head) {
            return null;
        }
        Cell<E> cell = head;
        head = head.getNext();
        if (head == null) {
            tail = null; // empty queue
        }
        return cell.getElement();
    }
}



