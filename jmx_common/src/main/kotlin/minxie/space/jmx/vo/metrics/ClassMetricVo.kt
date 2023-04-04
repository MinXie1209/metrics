package minxie.space.jmx.vo.metrics

import minxie.space.jmx.vo.MetricBaseVo
import minxie.space.jmx.vo.MetricItemVo
import java.lang.management.ManagementFactory

// jvm_classes_currently_loaded
private const val LOADED_CLASSES = "jvm_classes_currently_loaded"

// jvm_classes_loaded_total
private const val TOTAL_LOADED_CLASSES = "jvm_classes_loaded_total"

// jvm_classes_unloaded_total
private const val UNLOADED_CLASSES = "jvm_classes_unloaded_total"


class ClassMetricVo : MetricBaseVo() {



    init {
        ManagementFactory.getClassLoadingMXBean().let {
            metricList.add(MetricItemVo(LOADED_CLASSES, it.loadedClassCount.toFloat()))
            metricList.add(MetricItemVo(TOTAL_LOADED_CLASSES, it.totalLoadedClassCount.toFloat()))
            metricList.add(MetricItemVo(UNLOADED_CLASSES, it.unloadedClassCount.toFloat()))
        }
    }

}