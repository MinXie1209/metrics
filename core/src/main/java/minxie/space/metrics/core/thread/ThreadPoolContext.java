package minxie.space.metrics.core.thread;

import java.util.HashSet;
import java.util.Set;

public class ThreadPoolContext {
    private static final Set<Object> tomcatThreadPoolSet = new HashSet<Object>();
    private static final Set<Object> jdkThreadPoolSet = new HashSet<Object>();

    public static Set<Object> getTomcatThreadPoolSet() {
        return tomcatThreadPoolSet;
    }

    public static void addTomcatThreadPool(Object obj) {
        System.out.println("addTomcatThreadPool: " + obj);
        tomcatThreadPoolSet.add(obj);
    }

    public static Set<Object> getJdkThreadPoolSet() {
        return jdkThreadPoolSet;
    }

    public static void addJdkThreadPool(Object obj) {
        System.out.println("addJdkThreadPool: " + obj);
        jdkThreadPoolSet.add(obj);
    }
}