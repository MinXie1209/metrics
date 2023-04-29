package minxie.space.jvm.vo.metrics

import minxie.space.metrics.enums.MetricKeyEnum
import org.junit.Test

class JvmInfoMetricVoTest {
    @Test
    fun testJvmInfoMetricVo() {
        val jvmInfoMetricVo = JvmInfoMetricVo
        assert(jvmInfoMetricVo.toString().contains(MetricKeyEnum.JVM_INFO.key))
    }
}