package minxie.space.thread.metrics

import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.vo.MetricItemVo
import minxie.space.metrics.core.thread.ThreadPoolContext
import minxie.space.metrics.enums.MetricKeyEnum
import java.util.concurrent.AbstractExecutorService

class TomcatThreadPoolMetricsVo : MetricBaseVo() {
    init {
        ThreadPoolContext.getTomcatThreadPoolSet().filterIsInstance<AbstractExecutorService>().forEach {
            // 获取字段
            it.javaClass.getMethod("getActiveCount").also { it.isAccessible = true }.invoke(it)?.let { activeCount ->
                // 添加到metricItemVo
                MetricItemVo.build(MetricKeyEnum.TOMCAT_THREAD_POOL_ACTIVE_COUNT, (activeCount as Int).toFloat())
                    ?.let { metricItem -> metricList.add(metricItem) }
            }

            // 获取字段
            it.javaClass.getMethod("getCorePoolSize").also { it.isAccessible = true }.invoke(it)?.let { corePoolSize ->
                // 添加到metricItemVo
                MetricItemVo.build(MetricKeyEnum.TOMCAT_THREAD_POOL_CORE_POOL_SIZE, (corePoolSize as Int).toFloat())
                    ?.let { metricItem -> metricList.add(metricItem) }
            }

            // 获取字段
            it.javaClass.getMethod("getMaximumPoolSize").also { it.isAccessible = true }.invoke(it)
                ?.let { maximumPoolSize ->
                    // 添加到metricItemVo
                    MetricItemVo.build(
                        MetricKeyEnum.TOMCAT_THREAD_POOL_MAXIMUM_POOL_SIZE,
                        (maximumPoolSize as Int).toFloat()
                    )?.let { metricItem ->
                        metricList.add(
                            metricItem
                        )
                    }
                }

            // 获取queue size
            it.javaClass.getMethod("getQueue").also { it.isAccessible = true }.invoke(it)?.let { queue ->
                queue.javaClass.getMethod("size").also { it.isAccessible = true }.invoke(queue)?.let { queueSize ->
                    MetricItemVo.build(MetricKeyEnum.TOMCAT_THREAD_POOL_QUEUE_SIZE, (queueSize as Int).toFloat())
                        ?.let { metricItem -> metricList.add(metricItem) }
                }
            }

            // completed tasks
            it.javaClass.getMethod("getCompletedTaskCount").also { it.isAccessible = true }.invoke(it)
                ?.let { completedTaskCount ->
                    MetricItemVo.build(
                        MetricKeyEnum.TOMCAT_THREAD_POOL_COMPLETED_TASK_COUNT,
                        (completedTaskCount as Long).toFloat()
                    )?.let { metricItem ->
                        metricList.add(
                            metricItem
                        )
                    }
                }

        }
    }
}