package minxie.space.jvm.vo.metrics

import minxie.space.metrics.enums.MetricKeyEnum
import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.vo.MetricItemVo
import java.lang.management.ManagementFactory


class ClassMetricVo : MetricBaseVo() {
    init {
        ManagementFactory.getClassLoadingMXBean().let {

            MetricItemVo.build(MetricKeyEnum.LOADED_CLASSES, it.loadedClassCount.toFloat())
                ?.let { metricItem -> metricList.add(metricItem) }

            MetricItemVo.build(MetricKeyEnum.TOTAL_LOADED_CLASSES, it.totalLoadedClassCount.toFloat())
                ?.let { metricItem -> metricList.add(metricItem) }

            MetricItemVo.build(MetricKeyEnum.UNLOADED_CLASSES, it.unloadedClassCount.toFloat())
                ?.let { metricItem -> metricList.add(metricItem) }
        }
    }

}