package minxie.space.jvm.vo.metrics

import minxie.space.metrics.enums.MetricKeyEnum
import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.vo.MetricItemVo
import java.lang.management.ManagementFactory


private const val THREAD_NAME = "threadName"

private const val STATE = "state"

/**
 * 线程指标
 */
class ThreadMetricVo : MetricBaseVo() {
    var close = false
    init {
        ManagementFactory.getThreadMXBean()?.let {
            MetricItemVo.build(MetricKeyEnum.JVM_THREADS_CURRENT, it.threadCount.toFloat())
                ?.let { metricItem -> metricList.add(metricItem) }
            MetricItemVo.build(MetricKeyEnum.JVM_THREADS_DAEMON, it.daemonThreadCount.toFloat())
                ?.let { metricItem -> metricList.add(metricItem) }
            MetricItemVo.build(MetricKeyEnum.JVM_THREADS_PEAK, it.peakThreadCount.toFloat())
                ?.let { metricItem -> metricList.add(metricItem) }
            MetricItemVo.build(MetricKeyEnum.JVM_THREADS_STARTED_TOTAL, it.totalStartedThreadCount.toFloat())
                ?.let { metricItem -> metricList.add(metricItem) }
            if (!close) {
                // 此方法设计用于故障排除，但不用于同步控制。这可能是一项昂贵的操作。
                MetricItemVo.build(
                    MetricKeyEnum.JVM_THREADS_DEADLOCKED,
                    it.findDeadlockedThreads()?.size?.toFloat() ?: 0f
                )
                    ?.let { metricItem -> metricList.add(metricItem) }
                // 此方法设计用于故障排除，但不用于同步控制。这可能是一项昂贵的操作。
                MetricItemVo.build(
                    MetricKeyEnum.JVM_THREADS_DEADLOCKED_MONITOR,
                    it.findMonitorDeadlockedThreads()?.size?.toFloat() ?: 0f
                )?.let { metricItem ->
                    metricList.add(
                        metricItem
                    )
                }
            }
            it.allThreadIds?.let { threadIds ->
                it.getThreadInfo(threadIds, 0).let {
                    it.toList().forEach {
                        MetricItemVo.build(
                            MetricKeyEnum.JVM_THREADS, transformThreadState(
                                it.threadState
                            ), mapOf(THREAD_NAME to it.threadName)
                        )?.let { metricItem ->
                            metricList.add(
                                metricItem
                            )
                        }
                    }
                    it.groupBy { it.threadState }.mapValues { it.value.size.toFloat() }.forEach {
                        MetricItemVo.build(MetricKeyEnum.JVM_THREADS_STATE, it.value, mapOf(STATE to it.key.name))
                            ?.let { metricItem -> metricList.add(metricItem) }
                    }
                }
            }


        }
    }


    /**
     * TERMINATED
     * TIMED_WAITING
     * WAITING
     * RUNNABLE
     * BLOCKED
     * NEW
     */
    private fun transformThreadState(threadState: Thread.State): Float {
        when (threadState) {
            Thread.State.NEW -> return 0.0f
            Thread.State.RUNNABLE -> return 1.0f
            Thread.State.BLOCKED -> return 2.0f
            Thread.State.WAITING -> return 3.0f
            Thread.State.TIMED_WAITING -> return 4.0f
            Thread.State.TERMINATED -> return 5.0f
        }
    }

}