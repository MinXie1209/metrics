package minxie.space.jvm.vo.metrics

import com.sun.management.OperatingSystemMXBean
import com.sun.management.UnixOperatingSystemMXBean
import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.vo.MetricItemVo
import java.lang.management.ManagementFactory


private const val PROCESS_START_TIME_SECONDS = "process_start_time_seconds"

private const val JAVA_LANG_OPERATING_SYSTEM_AVAILABLE_PROCESSORS = "java_lang_OperatingSystem_AvailableProcessors"

private const val JAVA_LANG_OPERATING_SYSTEM_SYSTEM_LOAD_AVERAGE = "java_lang_OperatingSystem_SystemLoadAverage"

private const val JAVA_LANG_OPERATING_SYSTEM_PROCESS_CPU_LOAD = "java_lang_OperatingSystem_ProcessCpuLoad"

private const val JAVA_LANG_OPERATING_SYSTEM_CPU_LOAD = "java_lang_OperatingSystem_CpuLoad"

private const val PROCESS_OPEN_FDS = "process_open_fds"

private const val PROCESS_MAX_FDS = "process_max_fds"

/**
 * 应用指标
 */
class ProcessMetricVo : MetricBaseVo() {

    init {
        metricList.add(MetricItemVo(PROCESS_START_TIME_SECONDS, ManagementFactory.getRuntimeMXBean().startTime / 1000.0f))
        ManagementFactory.getPlatformMXBean(OperatingSystemMXBean::class.java)?.let {
            metricList.add(MetricItemVo(JAVA_LANG_OPERATING_SYSTEM_AVAILABLE_PROCESSORS, it.availableProcessors.toFloat()))
            metricList.add(MetricItemVo(JAVA_LANG_OPERATING_SYSTEM_SYSTEM_LOAD_AVERAGE, it.systemLoadAverage.toFloat()))
            metricList.add(MetricItemVo(JAVA_LANG_OPERATING_SYSTEM_PROCESS_CPU_LOAD, it.processCpuLoad.toFloat()))
            metricList.add(MetricItemVo(JAVA_LANG_OPERATING_SYSTEM_CPU_LOAD, it.systemCpuLoad.toFloat()))
        }
        ManagementFactory.getPlatformMXBean(UnixOperatingSystemMXBean::class.java)?.let {
            metricList.add(MetricItemVo(PROCESS_OPEN_FDS, it.openFileDescriptorCount.toFloat()))
            metricList.add(MetricItemVo(PROCESS_MAX_FDS, it.maxFileDescriptorCount.toFloat()))
        }
    }
}