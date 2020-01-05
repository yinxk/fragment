package genericity.ver1;

public class Main {
    public static void main(String[] args) {
        SingleLinkQueue<String> queue = new SingleLinkQueue<>();
        //queue.add(11); 报错
        queue.add("ddd");
        System.out.println(queue.remove());
    }
}
