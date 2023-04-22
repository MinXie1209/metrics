package minxie.space.metrics.core.thread;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ThreadPoolContext {
    private static final Set<Object> tomcatThreadPoolSet = new HashSet<Object>();
    private static final Set<Object> jdkThreadPoolSet = new HashSet<Object>();
    // 加个名称，方便区分
    private static final HashMap<Object, String> dubboThreadPoolMap = new HashMap<>();

    private static final Set<String> threadPoolNameSet = new HashSet<>();

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

    public static HashMap<Object, String> getDubboThreadPoolMap() {
        return dubboThreadPoolMap;
    }

    public static void addDubboThreadPool(Object obj) {
        System.out.println("addDubboThreadPool: " + obj);
        jdkThreadPoolSet.remove(obj);
        String threadPoolName = getThreadPoolName(obj);
        // 同名的话 加_index
        boolean contains = !threadPoolNameSet.add(threadPoolName);
        if (contains) {
            int index = 1;
            while (contains) {
                contains = !threadPoolNameSet.add(threadPoolName + "_" + index);
                if (!contains) {
                    dubboThreadPoolMap.put(obj, threadPoolName + "_" + index);
                }
                index++;
            }
        } else {
            dubboThreadPoolMap.put(obj, threadPoolName);
        }

    }

    /**
     * 获取线程池名称
     * 从类的字段 threadFactory 获取父类NamedThreadFactory 获取他的字段值mPrefix
     *
     * @param obj
     * @return
     */
    private static String getThreadPoolName(Object obj) {
        try {
            Field threadFactoryField = obj.getClass().getDeclaredField("threadFactory");
            threadFactoryField.setAccessible(true);
            Object threadFactory = threadFactoryField.get(obj);
            Field mPrefixField = threadFactory.getClass().getSuperclass().getDeclaredField("mPrefix");
            mPrefixField.setAccessible(true);
            String mPrefix = (String) mPrefixField.get(threadFactory);
            return mPrefix.substring(0, mPrefix.indexOf("-"));
        } catch (Exception e) {
            e.printStackTrace();
            return obj.getClass().getSimpleName() + "@" + Integer.toHexString(obj.hashCode());
        }
    }
}