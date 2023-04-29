package minxie.space.metrics.core.thread;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;

public class ThreadPoolContext {
    private static final Set<WeakReference<Object>> tomcatThreadPoolSet = new HashSet<>();
    private static final Set<WeakReference<Object>> jdkThreadPoolSet = new HashSet<>();
    // 加个名称，方便区分
    private static final WeakHashMap<Object, String> dubboThreadPoolMap = new WeakHashMap<>();

    private static final Set<String> dubboThreadPoolNameSet = new HashSet<>();

    // 加个名称，方便区分
    private static final WeakHashMap<Object, String> rocketMQThreadPoolMap = new WeakHashMap<>();

    private static final Set<String> rocketMQThreadPoolNameSet = new HashSet<>();

    public static Set<Object> getTomcatThreadPoolSet() {
        // 新的set
        Set<Object> tomcatThreadPoolSet = new HashSet<Object>();
        for (WeakReference<Object> weakReference : ThreadPoolContext.tomcatThreadPoolSet) {
            Object obj = weakReference.get();
            if (obj != null) {
                tomcatThreadPoolSet.add(obj);
            }
        }
        return tomcatThreadPoolSet;
    }

    public static void addTomcatThreadPool(Object obj) {
        System.out.println("addTomcatThreadPool: " + obj);
        // 先判断tomcatThreadPoolSet是否有obj
        Boolean containObj = false;
        for (WeakReference<Object> weakReference : ThreadPoolContext.tomcatThreadPoolSet) {
            Object o = weakReference.get();
            if (o != null && o.equals(obj)) {
                containObj = true;
            }
        }
        if (!containObj){
            tomcatThreadPoolSet.add(new WeakReference(obj));
        }
    }

    public static Set<Object> getJdkThreadPoolSet() {
        // 新的set
        Set<Object> jdkThreadPoolSet = new HashSet<>();
        for (WeakReference<Object> weakReference : ThreadPoolContext.jdkThreadPoolSet) {
            Object obj = weakReference.get();
            if (obj != null) {
                jdkThreadPoolSet.add(obj);
            }
        }
        return jdkThreadPoolSet;
    }

    public static void addJdkThreadPool(Object obj) {
        System.out.println("addJdkThreadPool: " + obj);
        Boolean containObj = false;
        for (WeakReference<Object> weakReference : ThreadPoolContext.jdkThreadPoolSet) {
            Object o = weakReference.get();
            if (o != null && o.equals(obj)) {
                containObj = true;
            }
        }
        if (!containObj){
            jdkThreadPoolSet.add(new WeakReference(obj));
        }
    }

    public static WeakHashMap<Object, String> getDubboThreadPoolMap() {
        return dubboThreadPoolMap;
    }

    public static void addDubboThreadPool(Object obj) {
        System.out.println("addDubboThreadPool: " + obj);
        removeItemFromJdkThreadPool(obj);
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

    private static void removeItemFromJdkThreadPool(Object obj) {
        WeakReference<Object> removeObj = null;
        for (WeakReference<Object> objectWeakReference : jdkThreadPoolSet) {
            Object o = objectWeakReference.get();
            if (o != null && o.equals(obj)) {
                removeObj = objectWeakReference;
            }
        }
        if (removeObj != null) {
            jdkThreadPoolSet.remove(obj);
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
//            e.printStackTrace();
            return obj.getClass().getSimpleName() + "@" + Integer.toHexString(obj.hashCode());
        }
    }

    public static void addRocketMQThreadPool(Object obj) {
        System.out.println("addRocketMQThreadPool: " + obj);
        removeItemFromJdkThreadPool(obj);
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
//            e.printStackTrace();
            return obj.getClass().getSimpleName() + "@" + Integer.toHexString(obj.hashCode());
        }
    }

    public static WeakHashMap<Object, String> getRocketMQThreadPoolMap() {
        return rocketMQThreadPoolMap;
    }
}