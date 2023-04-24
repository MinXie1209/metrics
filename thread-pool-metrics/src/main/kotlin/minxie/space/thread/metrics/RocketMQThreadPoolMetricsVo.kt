package minxie.space.thread.metrics

import minxie.space.metrics.core.thread.ThreadPoolContext
import minxie.space.metrics.enums.MetricKeyEnum
import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.vo.MetricItemVo
import java.util.concurrent.ThreadPoolExecutor

private const val THREAD_POOL_NAME = "threadPoolName"


private const val HASH_CODE = "hashCode"

class RocketMQThreadPoolMetricsVo : MetricBaseVo() {
    init {
        ThreadPoolContext.getRocketMQThreadPoolMap().filter { it.key is ThreadPoolExecutor }
            .forEach { (it, threadPoolName) ->
                it as ThreadPoolExecutor
                val tag = mapOf(THREAD_POOL_NAME to threadPoolName, HASH_CODE to it.hashCode().toString())
                it.activeCount.let { activeCount ->
                    MetricItemVo.build(MetricKeyEnum.ROCKETMQ_THREAD_POOL_ACTIVE_COUNT, activeCount.toFloat(), tag)
                        ?.let { metricItem -> metricList.add(metricItem) }
                }

                it.corePoolSize.let { corePoolSize ->
                    MetricItemVo.build(MetricKeyEnum.ROCKETMQ_THREAD_POOL_CORE_POOL_SIZE, corePoolSize.toFloat(), tag)
                        ?.let { metricItem -> metricList.add(metricItem) }
                }

                it.maximumPoolSize.let { maximumPoolSize ->
                    MetricItemVo.build(
                        MetricKeyEnum.ROCKETMQ_THREAD_POOL_MAXIMUM_POOL_SIZE,
                        maximumPoolSize.toFloat(),
                        tag
                    )
                        ?.let { metricItem -> metricList.add(metricItem) }
                }

                it.queue?.let { queue ->
                    MetricItemVo.build(MetricKeyEnum.ROCKETMQ_THREAD_POOL_QUEUE_SIZE, queue.size.toFloat(), tag)
                        ?.let { metricItem -> metricList.add(metricItem) }
                }

                it.completedTaskCount.let { completedTaskCount ->
                    MetricItemVo.build(
                        MetricKeyEnum.ROCKETMQ_THREAD_POOL_COMPLETED_TASK_COUNT,
                        completedTaskCount.toFloat(),
                        tag
                    )?.let { metricItem ->
                        metricList.add(
                            metricItem
                        )
                    }
                }
            }
    }
}