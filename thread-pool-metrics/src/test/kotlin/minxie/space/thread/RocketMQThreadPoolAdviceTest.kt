package minxie.space.thread

import minxie.space.metrics.core.thread.ThreadPoolContext
import minxie.space.metrics.enums.MetricKeyEnum
import minxie.space.thread.metrics.RocketMQThreadPoolMetricsVo
import org.junit.Test
import java.util.concurrent.Executors

class RocketMQThreadPoolAdviceTest {

    @Test
    fun exit() {
        val consumeMessageConcurrentlyService = ConsumeMessageConcurrentlyService().apply {
            this.consumeExecutor = Executors.newFixedThreadPool(1)
        }
        RocketMQThreadPoolAdvice.exit(consumeMessageConcurrentlyService)
        assert(
            ThreadPoolContext.getRocketMQThreadPoolMap().containsKey(consumeMessageConcurrentlyService.consumeExecutor)
        )
        val rocketMQThreadPoolMetricsVo = RocketMQThreadPoolMetricsVo()
        assert(rocketMQThreadPoolMetricsVo.toString().contains(MetricKeyEnum.ROCKETMQ_THREAD_POOL_ACTIVE_COUNT.key))
        assert(rocketMQThreadPoolMetricsVo.toString().contains(MetricKeyEnum.ROCKETMQ_THREAD_POOL_CORE_POOL_SIZE.key))
        assert(
            rocketMQThreadPoolMetricsVo.toString().contains(MetricKeyEnum.ROCKETMQ_THREAD_POOL_MAXIMUM_POOL_SIZE.key)
        )
        assert(
            rocketMQThreadPoolMetricsVo.toString().contains(MetricKeyEnum.ROCKETMQ_THREAD_POOL_COMPLETED_TASK_COUNT.key)
        )
        assert(rocketMQThreadPoolMetricsVo.toString().contains(MetricKeyEnum.ROCKETMQ_THREAD_POOL_QUEUE_SIZE.key))
    }
}