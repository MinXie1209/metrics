package minxie.space.jvm.vo.metrics

import com.sun.management.OperatingSystemMXBean
import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.enums.MetricKeyEnum
import minxie.space.metrics.vo.MetricItemVo
import java.lang.management.ManagementFactory




private const val AREA = "area"

private const val HEAP = "heap"

private const val NON_HEAP = "nonheap"

private const val POOL = "pool"


/**
 * 内存指标
 */
class MemoryMetricVo : MetricBaseVo() {

    init {
        ManagementFactory.getMemoryMXBean().let {
            MetricItemVo.build(MetricKeyEnum.JVM_MEMORY_BYTES_USED, it.heapMemoryUsage.used.toFloat(), mapOf(AREA to HEAP))
                ?.let { metricItem -> metricList.add(metricItem) }
            MetricItemVo.build(MetricKeyEnum.JVM_MEMORY_BYTES_MAX, it.heapMemoryUsage.max.toFloat(), mapOf(AREA to HEAP))
                ?.let { metricItem -> metricList.add(metricItem) }
            MetricItemVo.build(
                MetricKeyEnum.JVM_MEMORY_BYTES_USED,
                it.nonHeapMemoryUsage.used.toFloat(),
                mapOf(AREA to NON_HEAP)
            )?.let { metricItem ->
                metricList.add(
                    metricItem
                )
            }
            MetricItemVo.build(
                MetricKeyEnum.JVM_MEMORY_BYTES_MAX,
                it.nonHeapMemoryUsage.max.toFloat(),
                mapOf(AREA to NON_HEAP)
            )?.let { metricItem ->
                metricList.add(
                    metricItem
                )
            }
        }
        ManagementFactory.getMemoryPoolMXBeans()
            .forEach {
                MetricItemVo.build(MetricKeyEnum.JVM_MEMORY_POOL_BYTES_MAX, it.usage.max.toFloat(), mapOf(POOL to it.name))
                    ?.let { metricItem -> metricList.add(metricItem) }
                MetricItemVo.build(
                    MetricKeyEnum.JVM_MEMORY_POOL_BYTES_USED,
                    it.usage.used.toFloat(),
                    mapOf(POOL to it.name)
                )?.let { metricItem ->
                    metricList.add(
                        metricItem
                    )
                }
                MetricItemVo.build(
                    MetricKeyEnum.JVM_MEMORY_POOL_BYTES_COMMITTED,
                    it.usage.committed.toFloat(),
                    mapOf(POOL to it.name)
                )?.let { metricItem ->
                    metricList.add(
                        metricItem
                    )
                }
            }
        ManagementFactory.getPlatformMXBean(OperatingSystemMXBean::class.java).let {
            MetricItemVo.build(
                MetricKeyEnum.JAVA_LANG_OPERATING_SYSTEM_TOTAL_PHYSICAL_MEMORY_SIZE,
                it.totalPhysicalMemorySize.toFloat()
            )?.let { metricItem ->
                metricList.add(
                    metricItem
                )
            }
            MetricItemVo.build(
                MetricKeyEnum.JAVA_LANG_OPERATING_SYSTEM_COMMITTED_VIRTUAL_MEMORY_SIZE,
                it.committedVirtualMemorySize.toFloat()
            )?.let { metricItem ->
                metricList.add(
                    metricItem
                )
            }
            MetricItemVo.build(
                MetricKeyEnum.JAVA_LANG_OPERATING_SYSTEM_FREE_MEMORY_SIZE,
                it.freePhysicalMemorySize.toFloat()
            )?.let { metricItem ->
                metricList.add(
                    metricItem
                )
            }

        }
    }

}