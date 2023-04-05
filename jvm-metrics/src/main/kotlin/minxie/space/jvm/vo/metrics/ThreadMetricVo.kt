package minxie.space.jvm.vo.metrics

import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.vo.MetricItemVo
import java.lang.management.ManagementFactory

private const val JVM_THREADS_CURRENT = "jvm_threads_current"

private const val JVM_THREADS_DAEMON = "jvm_threads_daemon"

private const val JVM_THREADS_PEAK = "jvm_threads_peak"

private const val JVM_THREADS_STARTED_TOTAL = "jvm_threads_started_total"

private const val JVM_THREADS_DEADLOCKED = "jvm_threads_deadlocked"

private const val JVM_THREADS_DEADLOCKED_MONITOR = "jvm_threads_deadlocked_monitor"

private const val JVM_THREADS = "jvm_threads"

private const val THREAD_NAME = "threadName"

private const val JVM_THREADS_STATE = "jvm_threads_state"

private const val STATE = "state"

/**
 * 线程指标
 */
class ThreadMetricVo() : MetricBaseVo() {
    init {
        ManagementFactory.getThreadMXBean()?.let {
            metricList.add(MetricItemVo(JVM_THREADS_CURRENT, it.threadCount.toFloat()))
            metricList.add(MetricItemVo(JVM_THREADS_DAEMON, it.daemonThreadCount.toFloat()))
            metricList.add(MetricItemVo(JVM_THREADS_PEAK, it.peakThreadCount.toFloat()))
            metricList.add(MetricItemVo(JVM_THREADS_STARTED_TOTAL, it.totalStartedThreadCount.toFloat()))
            metricList.add(MetricItemVo(JVM_THREADS_DEADLOCKED, it.findDeadlockedThreads()?.size?.toFloat() ?: 0f))
            metricList.add(
                MetricItemVo(
                    JVM_THREADS_DEADLOCKED_MONITOR,
                    it.findMonitorDeadlockedThreads()?.size?.toFloat() ?: 0f
                )
            )
            it.allThreadIds?.let { threadIds ->
                it.getThreadInfo(threadIds, 0).let {
                    it.toList().forEach {
                        metricList.add(
                            MetricItemVo(
                                JVM_THREADS, transformThreadState(
                                    it.threadState
                                ), mapOf(THREAD_NAME to it.threadName)
                            )
                        )
                    }
                    it.groupBy { it.threadState }.mapValues { it.value.size.toFloat() }.forEach {
                        metricList.add(MetricItemVo(JVM_THREADS_STATE, it.value, mapOf(STATE to it.key.name)))
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