package minxie.space.thread

import minxie.space.metrics.core.thread.ThreadPoolContext
import minxie.space.metrics.enums.MetricKeyEnum
import minxie.space.thread.metrics.TomcatThreadPoolMetricsVo
import org.junit.Test
import java.util.concurrent.Executors

class TomcatThreadPoolAdviceTest {

    @Test
    fun enter() {
        val threadPool = Executors.newFixedThreadPool(1)
        TomcatThreadPoolAdvice.enter(threadPool)
        assert(ThreadPoolContext.getTomcatThreadPoolSet().contains(threadPool))
        val tomcatThreadPoolMetricsVo = TomcatThreadPoolMetricsVo()
        assert(tomcatThreadPoolMetricsVo.toString().contains(MetricKeyEnum.TOMCAT_THREAD_POOL_ACTIVE_COUNT.key))
        assert(tomcatThreadPoolMetricsVo.toString().contains(MetricKeyEnum.TOMCAT_THREAD_POOL_CORE_POOL_SIZE.key))
        assert(tomcatThreadPoolMetricsVo.toString().contains(MetricKeyEnum.TOMCAT_THREAD_POOL_MAXIMUM_POOL_SIZE.key))
        assert(tomcatThreadPoolMetricsVo.toString().contains(MetricKeyEnum.TOMCAT_THREAD_POOL_COMPLETED_TASK_COUNT.key))
        assert(tomcatThreadPoolMetricsVo.toString().contains(MetricKeyEnum.TOMCAT_THREAD_POOL_QUEUE_SIZE.key))
    }
}