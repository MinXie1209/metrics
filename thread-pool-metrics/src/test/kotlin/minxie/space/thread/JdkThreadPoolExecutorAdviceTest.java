package minxie.space.thread;

import minxie.space.metrics.core.thread.ThreadPoolContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JdkThreadPoolExecutorAdviceTest {

    @Test
    public void exit() {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        JdkThreadPoolExecutorAdvice.exit(threadPool);
        Assert.assertTrue(ThreadPoolContext.getJdkThreadPoolSet().contains(threadPool));
    }
}