package minxie.space.jmx.vo

import java.lang.management.ManagementFactory

class GcMetricVo {
    // jvm_gc_collection_seconds_count
    private var gcCollectionSecondsCount = 0.0f

    // jvm_gc_collection_seconds_sum
    private var gcCollectionSecondsSum = 0.0f

    public constructor() {
        ManagementFactory.getGarbageCollectorMXBeans().forEach {
            this.gcCollectionSecondsCount += it.collectionCount.toFloat()
            this.gcCollectionSecondsSum += it.collectionTime.toFloat()
        }
    }

    override fun toString(): String {
        return "jvm_gc_collection_seconds_count $gcCollectionSecondsCount ${System.lineSeparator()}" +
                "jvm_gc_collection_seconds_sum $gcCollectionSecondsSum ${System.lineSeparator()}"
    }
}