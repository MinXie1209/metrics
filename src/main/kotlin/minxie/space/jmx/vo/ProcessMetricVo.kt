package minxie.space.jmx.vo

import com.sun.management.OperatingSystemMXBean
import com.sun.management.UnixOperatingSystemMXBean
import java.lang.management.ManagementFactory


/**
 * 应用指标
 */
class ProcessMetricVo public constructor() {
    // process_start_time_seconds
    private var processStartTimeSeconds = 0.0f

    // java_lang_OperatingSystem_AvailableProcessors
    private var availableProcessors = 0.0f

    // java_lang_OperatingSystem_SystemLoadAverage
    private var systemLoadAverage = 0.0f

    // java_lang_OperatingSystem_ProcessCpuLoad
    private var processCpuLoad = 0.0f

    //java_lang_OperatingSystem_CpuLoad
    private var cpuLoad = 0.0f

    // process_open_fds
    private var openFds = 0.0f

    // process_max_fds
    private var maxFds = 0.0f

    init {
        this.processStartTimeSeconds = (ManagementFactory.getRuntimeMXBean().startTime / 1000.0f)
        (ManagementFactory.getOperatingSystemMXBean() as OperatingSystemMXBean).let {
            this.availableProcessors = it.availableProcessors.toFloat()
            this.systemLoadAverage = it.systemLoadAverage.toFloat()
            this.processCpuLoad = it.processCpuLoad.toFloat()
            this.cpuLoad = it.cpuLoad.toFloat()
        }
        ManagementFactory.getPlatformMXBean(UnixOperatingSystemMXBean::class.java).let {
            this.openFds = it.openFileDescriptorCount.toFloat()
            this.maxFds = it.maxFileDescriptorCount.toFloat()
        }
    }

    override fun toString(): String {
        return "process_start_time_seconds $processStartTimeSeconds ${System.lineSeparator()}" +
                "java_lang_OperatingSystem_AvailableProcessors $availableProcessors ${System.lineSeparator()}" +
                "java_lang_OperatingSystem_SystemLoadAverage $systemLoadAverage ${System.lineSeparator()}" +
                "java_lang_OperatingSystem_ProcessCpuLoad $processCpuLoad ${System.lineSeparator()}" +
                "java_lang_OperatingSystem_CpuLoad $cpuLoad ${System.lineSeparator()}" +
                "process_open_fds $openFds ${System.lineSeparator()}" +
                "process_max_fds $maxFds ${System.lineSeparator()}"
    }
}