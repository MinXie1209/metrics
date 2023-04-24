package minxie.space.jvm.vo.metrics

import com.sun.management.OperatingSystemMXBean
import com.sun.management.UnixOperatingSystemMXBean
import minxie.space.metrics.enums.MetricKeyEnum
import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.vo.MetricItemVo
import java.lang.management.ManagementFactory


/**
 * 应用指标
 */
class ProcessMetricVo : MetricBaseVo() {

    init {
        MetricItemVo.build(
            MetricKeyEnum.PROCESS_START_TIME_SECONDS,
            ManagementFactory.getRuntimeMXBean().startTime / 1000.0f
        )
            ?.let { metricList.add(it) }
        ManagementFactory.getPlatformMXBean(OperatingSystemMXBean::class.java)?.let {
            MetricItemVo.build(
                MetricKeyEnum.JAVA_LANG_OPERATING_SYSTEM_AVAILABLE_PROCESSORS,
                it.availableProcessors.toFloat()
            )
                ?.let { metricItem -> metricList.add(metricItem) }
            MetricItemVo.build(
                MetricKeyEnum.JAVA_LANG_OPERATING_SYSTEM_SYSTEM_LOAD_AVERAGE,
                it.systemLoadAverage.toFloat()
            )
                ?.let { metricItem -> metricList.add(metricItem) }
            MetricItemVo.build(MetricKeyEnum.JAVA_LANG_OPERATING_SYSTEM_PROCESS_CPU_LOAD, it.processCpuLoad.toFloat())
                ?.let { metricItem -> metricList.add(metricItem) }
            MetricItemVo.build(MetricKeyEnum.JAVA_LANG_OPERATING_SYSTEM_CPU_LOAD, it.systemCpuLoad.toFloat())
                ?.let { metricItem -> metricList.add(metricItem) }
        }
        ManagementFactory.getPlatformMXBean(UnixOperatingSystemMXBean::class.java)?.let {
            MetricItemVo.build(MetricKeyEnum.PROCESS_OPEN_FDS, it.openFileDescriptorCount.toFloat())
                ?.let { metricItem -> metricList.add(metricItem) }
            MetricItemVo.build(MetricKeyEnum.PROCESS_MAX_FDS, it.maxFileDescriptorCount.toFloat())
                ?.let { metricItem -> metricList.add(metricItem) }
        }
    }
}