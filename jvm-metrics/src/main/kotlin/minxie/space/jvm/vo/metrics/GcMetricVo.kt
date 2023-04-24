package minxie.space.jvm.vo.metrics


import minxie.space.metrics.enums.MetricKeyEnum
import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.vo.MetricItemVo
import java.lang.management.ManagementFactory


private const val GC = "gc"

class GcMetricVo : MetricBaseVo() {

    init {
        ManagementFactory.getGarbageCollectorMXBeans()
            .associate { it.name to Pair(it.collectionCount.toFloat(), it.collectionTime.toFloat() / 1000.0f) }
            .forEach {

                MetricItemVo.build(MetricKeyEnum.JVM_GC_COLLECTION_SECONDS_COUNT, it.value.first, mapOf(GC to it.key))
                    ?.let { metricItem -> metricList.add(metricItem) }

                MetricItemVo.build(MetricKeyEnum.JVM_GC_COLLECTION_SECONDS_SUM, it.value.second, mapOf(GC to it.key))
                    ?.let { metricItem -> metricList.add(metricItem) }
            }
    }
}