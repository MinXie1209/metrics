package minxie.space.jmx.vo

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
                }.joinToString(separator = "")
    }


}