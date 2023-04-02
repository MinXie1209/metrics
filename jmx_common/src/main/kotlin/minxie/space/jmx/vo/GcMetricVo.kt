package minxie.space.jmx.vo

import java.lang.management.ManagementFactory

class GcMetricVo {
    // jvm_gc_collection_seconds_count

    // jvm_gc_collection_seconds_sum
    private var gcMap = mapOf<String, Pair<Float, Float>>()

    public constructor() {
        ManagementFactory.getGarbageCollectorMXBeans()
            .associate { it.name to Pair(it.collectionCount.toFloat(), it.collectionTime.toFloat() / 1000.0f) }.let {
                this.gcMap = it
            }
    }

    override fun toString(): String {
        return gcMap.map {
            "jvm_gc_collection_seconds_count{gc=\"${it.key}\",} ${it.value.first} ${System.lineSeparator()}" +
                    "jvm_gc_collection_seconds_sum{gc=\"${it.key}\",} ${it.value.second} ${System.lineSeparator()}"
        }.joinToString(separator = "")
    }
}