package minxie.space.jmx.vo.metrics

import minxie.space.jmx.vo.MetricBaseVo
import minxie.space.jmx.vo.MetricItemVo
import java.lang.management.ManagementFactory

private const val JVM_GC_COLLECTION_SECONDS_COUNT = "jvm_gc_collection_seconds_count"

private const val JVM_GC_COLLECTION_SECONDS_SUM = "jvm_gc_collection_seconds_sum"

private const val GC = "gc"

class GcMetricVo : MetricBaseVo() {
    // jvm_gc_collection_seconds_count

    // jvm_gc_collection_seconds_sum

    init {
        ManagementFactory.getGarbageCollectorMXBeans()
            .associate { it.name to Pair(it.collectionCount.toFloat(), it.collectionTime.toFloat() / 1000.0f) }
            .forEach {
                metricList.add(MetricItemVo(JVM_GC_COLLECTION_SECONDS_COUNT, it.value.first, mapOf(GC to it.key)))
                metricList.add(MetricItemVo(JVM_GC_COLLECTION_SECONDS_SUM, it.value.second, mapOf(GC to it.key)))
            }
    }
}