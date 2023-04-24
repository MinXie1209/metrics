package minxie.space.jvm.vo.metrics

import minxie.space.metrics.enums.MetricKeyEnum
import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.vo.MetricItemVo



private const val VERSION = "version"

private const val VENDOR = "vendor"

private const val RUNTIME = "runtime"

/**
 * JvmInfo指标
 */
object JvmInfoMetricVo : MetricBaseVo() {
    private val version = System.getProperty("java.runtime.version", "unknown")
    private val vendor = System.getProperty("java.vm.vendor", "unknown")
    private val runtime = System.getProperty("java.runtime.name", "unknown")

    init {
        MetricItemVo.build(MetricKeyEnum.JVM_INFO, 1.0f, mapOf(VERSION to version, VENDOR to vendor, RUNTIME to runtime))
            ?.let { metricList.add(it) }
    }
}