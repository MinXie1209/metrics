package minxie.space.thread

import minxie.space.metrics.core.thread.ThreadPoolContext
import minxie.space.metrics.enums.MetricKeyEnum
import minxie.space.thread.metrics.DubboThreadPoolMetricsVo
import org.junit.Test
import java.util.concurrent.Executors

class DubboThreadPoolAdviceTest {

    @Test
    fun exit() {
        val threadPool = Executors.newFixedThreadPool(1)
        DubboThreadPoolAdvice.exit(threadPool)
        assert(ThreadPoolContext.getDubboThreadPoolMap().containsKey(threadPool))
        val dubboThreadPoolMetricsVo = DubboThreadPoolMetricsVo()
        assert(dubboThreadPoolMetricsVo.toString().contains(MetricKeyEnum.DUBBO_THREAD_POOL_ACTIVE_COUNT.key))
        assert(dubboThreadPoolMetricsVo.toString().contains(MetricKeyEnum.DUBBO_THREAD_POOL_CORE_POOL_SIZE.key))
        assert(dubboThreadPoolMetricsVo.toString().contains(MetricKeyEnum.DUBBO_THREAD_POOL_MAXIMUM_POOL_SIZE.key))
        assert(dubboThreadPoolMetricsVo.toString().contains(MetricKeyEnum.DUBBO_THREAD_POOL_COMPLETED_TASK_COUNT.key))
        assert(dubboThreadPoolMetricsVo.toString().contains(MetricKeyEnum.DUBBO_THREAD_POOL_QUEUE_SIZE.key))
    }
}