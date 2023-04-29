package minxie.space.jvm.vo.metrics

import minxie.space.metrics.enums.MetricKeyEnum
import org.junit.Test

class GcMetricVoTest {
    @Test
    fun testGcMetricVo() {
        val gcMetricVo = GcMetricVo()
        assert(gcMetricVo.toString().contains(MetricKeyEnum.JVM_GC_COLLECTION_SECONDS_COUNT.key))
        assert(gcMetricVo.toString().contains(MetricKeyEnum.JVM_GC_COLLECTION_SECONDS_SUM.key))
    }
}