package minxie.space.jvm.vo.metrics

import com.sun.management.OperatingSystemMXBean
import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.vo.MetricItemVo
import java.lang.management.ManagementFactory


private const val JVM_MEMORY_BYTES_USED = "jvm_memory_bytes_used"

private const val JVM_MEMORY_BYTES_MAX = "jvm_memory_bytes_max"

private const val AREA = "area"

private const val HEAP = "heap"

private const val NON_HEAP = "nonheap"

private const val JVM_MEMORY_POOL_BYTES_MAX = "jvm_memory_pool_bytes_max"

private const val JVM_MEMORY_POOL_BYTES_USED = "jvm_memory_pool_bytes_used"

private const val JVM_MEMORY_POOL_BYTES_COMMITTED = "jvm_memory_pool_bytes_committed"

private const val POOL = "pool"

private const val JAVA_LANG_OPERATING_SYSTEM_TOTAL_PHYSICAL_MEMORY_SIZE =
    "java_lang_OperatingSystem_TotalPhysicalMemorySize"

private const val JAVA_LANG_OPERATING_SYSTEM_COMMITTED_VIRTUAL_MEMORY_SIZE =
    "java_lang_OperatingSystem_CommittedVirtualMemorySize"

private const val JAVA_LANG_OPERATING_SYSTEM_FREE_MEMORY_SIZE = "java_lang_OperatingSystem_FreeMemorySize"

/**
 * 内存指标
 */
class MemoryMetricVo() : MetricBaseVo() {

    init {
        ManagementFactory.getMemoryMXBean().let {
            metricList.add(MetricItemVo(JVM_MEMORY_BYTES_USED, it.heapMemoryUsage.used.toFloat(), mapOf(AREA to HEAP)))
            metricList.add(MetricItemVo(JVM_MEMORY_BYTES_MAX, it.heapMemoryUsage.max.toFloat(), mapOf(AREA to HEAP)))
            metricList.add(
                MetricItemVo(
                    JVM_MEMORY_BYTES_USED,
                    it.nonHeapMemoryUsage.used.toFloat(),
                    mapOf(AREA to NON_HEAP)
                )
            )
            metricList.add(
                MetricItemVo(
                    JVM_MEMORY_BYTES_MAX,
                    it.nonHeapMemoryUsage.max.toFloat(),
                    mapOf(AREA to NON_HEAP)
                )
            )
        }
        ManagementFactory.getMemoryPoolMXBeans()
            .forEach {
                metricList.add(MetricItemVo(JVM_MEMORY_POOL_BYTES_MAX, it.usage.max.toFloat(), mapOf(POOL to it.name)))
                metricList.add(
                    MetricItemVo(
                        JVM_MEMORY_POOL_BYTES_USED,
                        it.usage.used.toFloat(),
                        mapOf(POOL to it.name)
                    )
                )
                metricList.add(
                    MetricItemVo(
                        JVM_MEMORY_POOL_BYTES_COMMITTED,
                        it.usage.committed.toFloat(),
                        mapOf(POOL to it.name)
                    )
                )
            }
        ManagementFactory.getPlatformMXBean(OperatingSystemMXBean::class.java).let {
            metricList.add(
                MetricItemVo(
                    JAVA_LANG_OPERATING_SYSTEM_TOTAL_PHYSICAL_MEMORY_SIZE,
                    it.totalPhysicalMemorySize.toFloat()
                )
            )
            metricList.add(
                MetricItemVo(
                    JAVA_LANG_OPERATING_SYSTEM_COMMITTED_VIRTUAL_MEMORY_SIZE,
                    it.committedVirtualMemorySize.toFloat()
                )
            )
            metricList.add(
                MetricItemVo(
                    JAVA_LANG_OPERATING_SYSTEM_FREE_MEMORY_SIZE,
                    it.freePhysicalMemorySize.toFloat()
                )
            )

        }
    }

}