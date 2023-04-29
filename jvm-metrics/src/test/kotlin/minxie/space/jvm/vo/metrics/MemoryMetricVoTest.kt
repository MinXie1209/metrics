package minxie.space.jvm.vo.metrics

import minxie.space.metrics.enums.MetricKeyEnum
import org.junit.Test

class MemoryMetricVoTest {
    @Test
    fun testMemoryMetricVo() {
        val memoryMetricVo = MemoryMetricVo()
        assert(memoryMetricVo.toString().contains(MetricKeyEnum.JVM_MEMORY_BYTES_USED.key))
        assert(memoryMetricVo.toString().contains(MetricKeyEnum.JVM_MEMORY_BYTES_MAX.key))
        assert(memoryMetricVo.toString().contains(MetricKeyEnum.JVM_MEMORY_POOL_BYTES_MAX.key))
        assert(memoryMetricVo.toString().contains(MetricKeyEnum.JVM_MEMORY_POOL_BYTES_USED.key))
    }
}