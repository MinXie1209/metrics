package minxie.space.metrics.core.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolContextTest {

    @Test
    public void addTomcatThreadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        ThreadPoolContext.addTomcatThreadPool(threadPool);
        assert (ThreadPoolContext.getTomcatThreadPoolSet().contains(threadPool));
    }

    @Test
    public void addJdkThreadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        ThreadPoolContext.addJdkThreadPool(threadPool);
        assert (ThreadPoolContext.getJdkThreadPoolSet().contains(threadPool));
    }

    @Test
    public void addDubboThreadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        ThreadPoolContext.addDubboThreadPool(threadPool);
        assert (ThreadPoolContext.getDubboThreadPoolMap().containsKey(threadPool));
    }

    @Test
    public void addRocketMQThreadPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        ThreadPoolContext.addRocketMQThreadPool(threadPool);
        assert (ThreadPoolContext.getRocketMQThreadPoolMap().containsKey(threadPool));
    }

}