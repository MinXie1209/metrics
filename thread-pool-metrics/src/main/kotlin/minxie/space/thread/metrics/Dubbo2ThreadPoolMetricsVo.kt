package minxie.space.thread.metrics

import minxie.space.metrics.core.thread.ThreadPoolContext
import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.vo.MetricItemVo
import java.util.concurrent.ThreadPoolExecutor

private const val THREAD_POOL_NAME = "threadPoolName"
class Dubbo2ThreadPoolMetricsVo : MetricBaseVo() {
    init {
        ThreadPoolContext.getDubbo2ThreadPoolSet().filterIsInstance<ThreadPoolExecutor>().forEach {
            val threadPoolName = it.javaClass.simpleName + "@" + Integer.toHexString(it.hashCode())
            val tag = mapOf(THREAD_POOL_NAME to threadPoolName)
            it.activeCount?.let { activeCount ->
                metricList.add(MetricItemVo("dubbo2thread_pool_active_count", activeCount.toFloat(), tag))
            }

            it.corePoolSize?.let { corePoolSize ->
                metricList.add(MetricItemVo("dubbo2thread_pool_core_pool_size", corePoolSize.toFloat(), tag))
            }

            it.maximumPoolSize?.let { maximumPoolSize ->
                metricList.add(MetricItemVo("dubbo2thread_pool_maximum_pool_size", maximumPoolSize.toFloat(), tag))
            }

            it.queue?.let { queue ->
                metricList.add(MetricItemVo("dubbo2thread_pool_queue_size", queue.size.toFloat(), tag))
            }

            it.completedTaskCount?.let { completedTaskCount ->
                metricList.add(MetricItemVo("dubbo2thread_pool_completed_task_count", completedTaskCount.toFloat(), tag))
            }

        }
    }
}