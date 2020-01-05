package genericity.bounded;

/**
 * Comparable接口为E类型的上届, 而E则是一个有界的(bounded)类型参数
 * 在这里使用的关键字extends的含义很宽泛, 即可以是"扩展"也可以是"实现", 这取决于后面跟随的类型是类类型还是接口类型
 * 任何给定的类只能扩展一个其他的类, 但是可以通过类或接口实现多个接口
 */
public interface SortedCollection <E extends Comparable<E>>{
}

/**
 * 类型边界可以通过以下方式来表示这样的多重继承关系: 声明类型参数扩展了一个类或接口, 并在后面
 * 跟随一个有"&"符号分隔开的其他必须实现的接口的列表.
 */
interface SortedCharSeqCollection<E extends Comparable<E> & CharSequence>{

}
