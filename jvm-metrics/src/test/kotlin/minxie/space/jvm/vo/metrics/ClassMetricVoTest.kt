package minxie.space.jvm.vo.metrics

import minxie.space.metrics.enums.MetricKeyEnum
import org.junit.Test

class ClassMetricVoTest {
    @Test
    fun testClassMetricVo() {
        val classMetricVo = ClassMetricVo()
        assert(classMetricVo.toString().contains(MetricKeyEnum.LOADED_CLASSES.key))
        assert(classMetricVo.toString().contains(MetricKeyEnum.TOTAL_LOADED_CLASSES.key))
        assert(classMetricVo.toString().contains(MetricKeyEnum.UNLOADED_CLASSES.key))
    }
}