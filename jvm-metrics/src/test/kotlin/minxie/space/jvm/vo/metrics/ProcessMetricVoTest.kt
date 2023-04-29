package minxie.space.jvm.vo.metrics

import minxie.space.metrics.enums.MetricKeyEnum
import org.junit.Test

class ProcessMetricVoTest{
    @Test
    fun testProcessMetricVo(){
        val processMetricVo = ProcessMetricVo()
        assert(processMetricVo.toString().contains(MetricKeyEnum.PROCESS_START_TIME_SECONDS.key))
        assert(processMetricVo.toString().contains(MetricKeyEnum.JAVA_LANG_OPERATING_SYSTEM_AVAILABLE_PROCESSORS.key))
        assert(processMetricVo.toString().contains(MetricKeyEnum.JAVA_LANG_OPERATING_SYSTEM_SYSTEM_LOAD_AVERAGE.key))
        assert(processMetricVo.toString().contains(MetricKeyEnum.JAVA_LANG_OPERATING_SYSTEM_PROCESS_CPU_LOAD.key))
        assert(processMetricVo.toString().contains(MetricKeyEnum.JAVA_LANG_OPERATING_SYSTEM_CPU_LOAD.key))
        assert(processMetricVo.toString().contains(MetricKeyEnum.PROCESS_OPEN_FDS.key))
        assert(processMetricVo.toString().contains(MetricKeyEnum.PROCESS_MAX_FDS.key))
    }
}