package minxie.space.jvm.vo.metrics

import minxie.space.metrics.enums.MetricKeyEnum
import org.junit.Test

class ThreadMetricVoTest{
    @Test
    fun testThreadMetricVo(){
        val threadMetricVo = ThreadMetricVo()
        assert(threadMetricVo.toString().contains(MetricKeyEnum.JVM_THREADS_CURRENT.key))
        assert(threadMetricVo.toString().contains(MetricKeyEnum.JVM_THREADS_DAEMON.key))
        assert(threadMetricVo.toString().contains(MetricKeyEnum.JVM_THREADS_PEAK.key))
        assert(threadMetricVo.toString().contains(MetricKeyEnum.JVM_THREADS_STARTED_TOTAL.key))
        assert(threadMetricVo.toString().contains(MetricKeyEnum.JVM_THREADS_DEADLOCKED.key))
        assert(threadMetricVo.toString().contains(MetricKeyEnum.JVM_THREADS_DEADLOCKED_MONITOR.key))
        assert(threadMetricVo.toString().contains(MetricKeyEnum.JVM_THREADS.key))
        assert(threadMetricVo.toString().contains(MetricKeyEnum.JVM_THREADS_STATE.key))
    }

}