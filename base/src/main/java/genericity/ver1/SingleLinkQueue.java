package genericity.ver1;

public class SingleLinkQueue<E> {
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
