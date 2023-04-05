package minxie.space.thread.metrics

import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.vo.MetricItemVo
import minxie.space.thread.TomcatThreadPoolContext
import java.util.concurrent.AbstractExecutorService

class TomcatThreadPoolMetricsVo : MetricBaseVo() {
    init {
        TomcatThreadPoolContext.getThreadPoolSet().filterIsInstance<AbstractExecutorService>().forEach {
            // 获取字段
            it.javaClass.getMethod("getActiveCount").invoke(it)?.let { activeCount ->
                // 添加到metricItemVo
                metricList.add(MetricItemVo("tomcat_thread_pool_active_count", (activeCount as Int).toFloat()))
            }

            // 获取字段
            it.javaClass.getMethod("getCorePoolSize").invoke(it)?.let { corePoolSize ->
                // 添加到metricItemVo
                metricList.add(MetricItemVo("tomcat_thread_pool_core_pool_size", (corePoolSize as Int).toFloat()))
            }

            // 获取字段
            it.javaClass.getMethod("getMaximumPoolSize").invoke(it)?.let { maximumPoolSize ->
                // 添加到metricItemVo
                metricList.add(MetricItemVo("tomcat_thread_pool_maximum_pool_size", (maximumPoolSize as Int).toFloat()))
            }

            // 获取queue size
           it.javaClass.getMethod("getQueue").invoke(it)?.let { queue ->
               queue.javaClass.getMethod("size").invoke(queue)?.let { queueSize ->
                   metricList.add(MetricItemVo("tomcat_thread_pool_queue_size", (queueSize as Int).toFloat()))
               }
           }

            // completed tasks
            it.javaClass.getMethod("getCompletedTaskCount").invoke(it)?.let { completedTaskCount ->
                metricList.add(MetricItemVo("tomcat_thread_pool_completed_task_count", (completedTaskCount as Long).toFloat()))
            }

        }
    }
}