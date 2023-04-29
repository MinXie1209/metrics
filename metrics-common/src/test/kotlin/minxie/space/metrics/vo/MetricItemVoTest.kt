package minxie.space.metrics.vo

import minxie.space.metrics.enums.MetricKeyEnum
import org.junit.Test

class MetricItemVoTest {
    @Test
    fun testMetricItemVo() {
        val tagMap = mapOf("test" to "test")
        val metricItemVo = MetricItemVo.build(MetricKeyEnum.JVM_THREADS_CURRENT, 1.0f, tagMap)
        assert(metricItemVo.toString().contains(MetricKeyEnum.JVM_THREADS_CURRENT.key))
        assert(metricItemVo.toString().contains("1.0"))
        tagMap.forEach {
            assert(metricItemVo.toString().contains("${it.key} = \"${it.value}\""))
        }
    }
}