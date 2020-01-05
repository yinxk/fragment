package genericity.ver1;

/**
 * E被称为类型变量, 它可以被具体的类型替代
 * E并不是特指的名称, 例如也可以是ElementType, 但是E是"元素(element)"的一个很好的缩写
 * 按照惯例, 类型变量可以用单个字母来命名; E代表元素类型, K代表键类型, V代表值类型, T代表通用类型, 等等
 *
 */
public class Cell<E> {
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
