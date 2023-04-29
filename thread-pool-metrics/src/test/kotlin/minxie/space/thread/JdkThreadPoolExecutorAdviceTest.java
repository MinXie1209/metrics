package minxie.space.thread;

import minxie.space.metrics.core.thread.ThreadPoolContext;
import minxie.space.metrics.enums.MetricKeyEnum;
import minxie.space.thread.metrics.JdkThreadPoolMetricsVo;
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
        JdkThreadPoolMetricsVo jdkThreadPoolMetricsVo = new JdkThreadPoolMetricsVo();
        assert (jdkThreadPoolMetricsVo.toString().contains(MetricKeyEnum.JDK_THREAD_POOL_ACTIVE_COUNT.getKey()));
        assert (jdkThreadPoolMetricsVo.toString().contains(MetricKeyEnum.JDK_THREAD_POOL_CORE_POOL_SIZE.getKey()));
        assert (jdkThreadPoolMetricsVo.toString().contains(MetricKeyEnum.JDK_THREAD_POOL_MAXIMUM_POOL_SIZE.getKey()));
        assert (jdkThreadPoolMetricsVo.toString().contains(MetricKeyEnum.JDK_THREAD_POOL_COMPLETED_TASK_COUNT.getKey()));
        assert (jdkThreadPoolMetricsVo.toString().contains(MetricKeyEnum.JDK_THREAD_POOL_QUEUE_SIZE.getKey()));

    }
}