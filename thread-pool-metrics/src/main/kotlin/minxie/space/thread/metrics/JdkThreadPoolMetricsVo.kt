package minxie.space.thread.metrics

import minxie.space.metrics.core.thread.ThreadPoolContext
import minxie.space.metrics.enums.MetricKeyEnum
import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.vo.MetricItemVo
import java.util.concurrent.ThreadPoolExecutor

private const val THREAD_POOL_NAME = "threadPoolName"

class JdkThreadPoolMetricsVo : MetricBaseVo() {
    init {
        ThreadPoolContext.getJdkThreadPoolSet().filterIsInstance<ThreadPoolExecutor>().forEach {
            val threadPoolName = it.javaClass.simpleName + "@" + Integer.toHexString(it.hashCode())
            val tag = mapOf(THREAD_POOL_NAME to threadPoolName)
            it.activeCount.let { activeCount ->
                MetricItemVo.build(MetricKeyEnum.JDK_THREAD_POOL_ACTIVE_COUNT, activeCount.toFloat(), tag)
                    ?.let { metricItem -> metricList.add(metricItem) }
            }

            it.corePoolSize.let { corePoolSize ->
                MetricItemVo.build(MetricKeyEnum.JDK_THREAD_POOL_CORE_POOL_SIZE, corePoolSize.toFloat(), tag)
                    ?.let { metricItem -> metricList.add(metricItem) }
            }

            it.maximumPoolSize.let { maximumPoolSize ->
                MetricItemVo.build(MetricKeyEnum.JDK_THREAD_POOL_MAXIMUM_POOL_SIZE, maximumPoolSize.toFloat(), tag)
                    ?.let { metricItem -> metricList.add(metricItem) }
            }

            it.queue?.let { queue ->
                MetricItemVo.build(MetricKeyEnum.JDK_THREAD_POOL_QUEUE_SIZE, queue.size.toFloat(), tag)
                    ?.let { metricItem -> metricList.add(metricItem) }
            }

            it.completedTaskCount.let { completedTaskCount ->
                MetricItemVo.build(
                    MetricKeyEnum.JDK_THREAD_POOL_COMPLETED_TASK_COUNT, completedTaskCount.toFloat(), tag
                )?.let { metricItem -> metricList.add(metricItem) }
            }

        }
    }
}