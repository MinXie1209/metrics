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

    private static final Set<String> dubboThreadPoolNameSet = new HashSet<>();

    // 加个名称，方便区分
    private static final HashMap<Object, String> rocketMQThreadPoolMap = new HashMap<>();

    private static final Set<String> rocketMQThreadPoolNameSet = new HashSet<>();

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
        String threadPoolName = getDubboThreadPoolName(obj);
        // 同名的话 加_index
        boolean contains = !dubboThreadPoolNameSet.add(threadPoolName);
        if (contains) {
            int index = 1;
            while (contains) {
                contains = !dubboThreadPoolNameSet.add(threadPoolName + "_" + index);
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
    private static String getDubboThreadPoolName(Object obj) {
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

    public static void addRocketMQThreadPool(Object obj) {
        System.out.println("addRocketMQThreadPool: " + obj);
        jdkThreadPoolSet.remove(obj);
        String threadPoolName = getRocketThreadPoolName(obj);
        // 同名的话 加_index
        boolean contains = !rocketMQThreadPoolNameSet.add(threadPoolName);
        if (contains) {
            int index = 1;
            while (contains) {
                contains = !rocketMQThreadPoolNameSet.add(threadPoolName + "_" + index);
                if (!contains) {
                    rocketMQThreadPoolMap.put(obj, threadPoolName + "_" + index);
                }
                index++;
            }
        } else {
            rocketMQThreadPoolMap.put(obj, threadPoolName);
        }

    }

    /**
     * 获取线程池名称
     * 从类的字段 threadFactory 获取父类NamedThreadFactory 获取他的字段值mPrefix
     *
     * @param obj
     * @return
     */
    private static String getRocketThreadPoolName(Object obj) {
        try {
            Field threadFactoryField = obj.getClass().getDeclaredField("threadFactory");
            threadFactoryField.setAccessible(true);
            Object threadFactory = threadFactoryField.get(obj);
            Field threadNamePrefixField = threadFactory.getClass().getDeclaredField("threadNamePrefix");
            threadNamePrefixField.setAccessible(true);
            String threadNamePrefix = (String) threadNamePrefixField.get(threadFactory);
            return threadNamePrefix.substring(0, threadNamePrefix.indexOf("_"));
        } catch (Exception e) {
            e.printStackTrace();
            return obj.getClass().getSimpleName() + "@" + Integer.toHexString(obj.hashCode());
        }
    }

    public static HashMap<Object, String> getRocketMQThreadPoolMap() {
        return rocketMQThreadPoolMap;
    }
}