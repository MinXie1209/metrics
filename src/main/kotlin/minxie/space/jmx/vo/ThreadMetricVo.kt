package minxie.space.jmx.vo

import java.lang.management.ThreadMXBean

/**
 * 线程指标
 */
class ThreadMetricVo(threadMXBean: ThreadMXBean) {
    private var threadCount: Float = 0f
    private var daemonThreadCount: Float = 0f
    private var peakThreadCount: Float = 0f
    private var totalStartedThreadCount: Float = 0f
    private var deadlockedThreadCount: Float = 0f
    private var deadlockedMonitorThreadCount: Float = 0f
    private var threadStateMap: Map<Thread.State, Float> = mapOf()

    init {
        this.threadCount = threadMXBean.threadCount.toFloat()
        this.daemonThreadCount = threadMXBean.daemonThreadCount.toFloat()
        this.peakThreadCount = threadMXBean.peakThreadCount.toFloat()
        this.totalStartedThreadCount = threadMXBean.totalStartedThreadCount.toFloat()
        this.deadlockedThreadCount = threadMXBean.findDeadlockedThreads()?.size?.toFloat() ?: 0f
        this.deadlockedMonitorThreadCount = threadMXBean.findMonitorDeadlockedThreads()?.size?.toFloat() ?: 0f
        this.threadStateMap = threadMXBean.allThreadIds?.let { threadIds ->
            val threadInfoMap = threadMXBean.getThreadInfo(threadIds, 0).groupBy { it.threadState }
            threadInfoMap.mapValues { it.value.size.toFloat() }
        } ?: mapOf()
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("${JvmThreadMetricEnum.JVM_THREADS_CURRENT.metricName} $threadCount")
            .append(System.lineSeparator())
            .append("${JvmThreadMetricEnum.JVM_THREADS_DAEMON.metricName} $daemonThreadCount")
            .append(System.lineSeparator())
            .append("${JvmThreadMetricEnum.JVM_THREADS_PEAK.metricName} $peakThreadCount")
            .append(System.lineSeparator())
            .append("${JvmThreadMetricEnum.JVM_THREADS_STARTED_TOTAL.metricName} $totalStartedThreadCount")
            .append(System.lineSeparator())
            .append("${JvmThreadMetricEnum.JVM_THREADS_DEADLOCKED.metricName} $deadlockedThreadCount")
            .append(System.lineSeparator())
            .append("${JvmThreadMetricEnum.JVM_THREADS_DEADLOCKED_MONITOR.metricName} $deadlockedMonitorThreadCount")
            .append(System.lineSeparator())
        threadStateMap.forEach { (threadState, count) ->
            stringBuilder.append("${JvmThreadMetricEnum.JVM_THREADS_STATE.metricName}{state=\"${threadState}\",} $count")
                .append(System.lineSeparator())
        }
        return stringBuilder.toString()
    }

    enum class JvmThreadMetricEnum(val metricName: String) {
        JVM_THREADS_CURRENT("jvm_threads_current"),
        JVM_THREADS_DAEMON("jvm_threads_daemon"),
        JVM_THREADS_PEAK("jvm_threads_peak"),
        JVM_THREADS_STARTED_TOTAL("jvm_threads_started_total"),
        JVM_THREADS_DEADLOCKED("jvm_threads_deadlocked"),
        JVM_THREADS_DEADLOCKED_MONITOR("jvm_threads_deadlocked_monitor"),
        JVM_THREADS_STATE("jvm_threads_state")
    }

}