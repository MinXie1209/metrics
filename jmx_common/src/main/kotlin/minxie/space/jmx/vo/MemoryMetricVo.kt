package minxie.space.jmx.vo

import com.sun.management.OperatingSystemMXBean
import java.lang.management.ManagementFactory
import java.lang.management.MemoryUsage


/**
 * 内存指标
 */
class MemoryMetricVo {
    // jvm_memory_bytes_used
    private var heapUsed = 0.0f

    // jvm_memory_bytes_max
    private var heapMax = 0.0f

    // jvm_memory_bytes_used
    private var nonHeapUsed = 0.0f

    // jvm_memory_bytes_max
    private var nonHeapMax = 0.0f

    private var poolMap = mapOf<String, MemoryUsage>()

    // java_lang_OperatingSystem_TotalPhysicalMemorySize
    private var totalPhysicalMemorySize = 0.0f

    // java_lang_OperatingSystem_CommittedVirtualMemorySize
    private var committedVirtualMemorySize = 0.0f

    // java_lang_OperatingSystem_FreeMemorySize
    private var freePhysicalMemorySize = 0.0f

    constructor() {
        ManagementFactory.getMemoryMXBean().let {
            this.heapUsed = it.heapMemoryUsage.used.toFloat()
            this.heapMax = it.heapMemoryUsage.max.toFloat()
            this.nonHeapUsed = it.nonHeapMemoryUsage.used.toFloat()
            this.nonHeapMax = it.nonHeapMemoryUsage.max.toFloat()
        }
        ManagementFactory.getMemoryPoolMXBeans().let {
            this.poolMap = it.associate {
                it.name to it.usage
            }
        }
        ManagementFactory.getPlatformMXBean(OperatingSystemMXBean::class.java).let {
            this.totalPhysicalMemorySize = it.totalPhysicalMemorySize.toFloat()
            this.committedVirtualMemorySize = it.committedVirtualMemorySize.toFloat()
            this.freePhysicalMemorySize = it.freePhysicalMemorySize.toFloat()
        }
    }

    override fun toString(): String {
        return "jvm_memory_bytes_used{area=\"heap\",} $heapUsed ${System.lineSeparator()}" +
                "jvm_memory_bytes_max{area=\"heap\",} $heapMax ${System.lineSeparator()}" +
                "jvm_memory_bytes_used{area=\"nonheap\",} $nonHeapUsed ${System.lineSeparator()}" +
                "jvm_memory_bytes_max{area=\"nonheap\",} $nonHeapMax ${System.lineSeparator()}" +
                poolMap.map {
                    "jvm_memory_pool_bytes_max{pool=\"${it.key}\",} ${it.value.max.toFloat()} ${System.lineSeparator()}" +
                            "jvm_memory_pool_bytes_used{pool=\"${it.key}\",} ${it.value.used.toFloat()} ${System.lineSeparator()}" +
                            "jvm_memory_pool_bytes_committed{pool=\"${it.key}\",} ${it.value.committed.toFloat()} ${System.lineSeparator()}"
                }.joinToString(separator = "") +
                "java_lang_OperatingSystem_TotalPhysicalMemorySize $totalPhysicalMemorySize ${System.lineSeparator()}" +
                "java_lang_OperatingSystem_CommittedVirtualMemorySize $committedVirtualMemorySize ${System.lineSeparator()}" +
                "java_lang_OperatingSystem_FreeMemorySize $freePhysicalMemorySize ${System.lineSeparator()}"
    }


}