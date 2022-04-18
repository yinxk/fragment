package jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\opt\applog\xx\xx_gc.hprof
 */
@SuppressWarnings({"InfiniteLoopStatement", "MismatchedQueryAndUpdateOfCollection"})
public class HeapOom {

    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }


}
