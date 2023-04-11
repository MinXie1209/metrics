package minxie.space.thread.metrics

import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.vo.MetricItemVo
import minxie.space.thread.ThreadPoolContext
import java.util.concurrent.ThreadPoolExecutor

class JdkThreadPoolMetricsVo : MetricBaseVo() {
    init {
        ThreadPoolContext.getJdkThreadPoolSet().filterIsInstance<ThreadPoolExecutor>().forEach {
            it.activeCount?.let { activeCount ->
                metricList.add(MetricItemVo("jdk_thread_pool_active_count", activeCount.toFloat()))
            }

            it.corePoolSize?.let { corePoolSize ->
                metricList.add(MetricItemVo("jdk_thread_pool_core_pool_size", corePoolSize.toFloat()))
            }

            it.maximumPoolSize?.let { maximumPoolSize ->
                metricList.add(MetricItemVo("jdk_thread_pool_maximum_pool_size", maximumPoolSize.toFloat()))
            }

            it.queue?.let { queue ->
                metricList.add(MetricItemVo("jdk_thread_pool_queue_size", queue.size.toFloat()))
            }

            it.completedTaskCount?.let { completedTaskCount ->
                metricList.add(MetricItemVo("jdk_thread_pool_completed_task_count", completedTaskCount.toFloat()))
            }

        }
    }
}