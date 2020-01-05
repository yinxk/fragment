package genericity.nested;

/**
 * 如果嵌套类型是一个内部类, 那么外部类声明中的类型变量对于它来说就是可以访问的,
 * 并且可以直接使用.
 * 这一次单元的元素类型直接与其所属的队列的元素类型进行了绑定, 因此也就不需要将Cell
 * 声明为泛型类了.
 * 如果我们选择了使用泛型内部类, 那么内部类中的类型变量就会隐藏外部类中与其同名的
 * 任何类型变量, 但通常是应该避免隐藏的
 */
@SuppressWarnings("Duplicates")
public class SingleLinkQueue2<E> {
    class Cell {
        private Cell next;
        private E element;

        public Cell(E element) {
            this.element = element;
        }

        public Cell(Cell next, E element) {
            this.next = next;
            this.element = element;
        }

        public Cell getNext() {
            return next;
        }

        public void setNext(Cell next) {
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }
    }

    protected Cell head;
    protected Cell tail;

    public void add(E item) {
        Cell cell = new Cell(item);
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
        Cell cell = head;
        head = head.getNext();
        if (head == null) {
            tail = null; // empty queue
        }
        return cell.getElement();
    }
}



