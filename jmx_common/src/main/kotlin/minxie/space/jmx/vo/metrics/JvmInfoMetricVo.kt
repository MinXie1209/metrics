package minxie.space.jmx.vo.metrics

import minxie.space.jmx.vo.MetricBaseVo
import minxie.space.jmx.vo.MetricItemVo


private const val JVM_INFO = "jvm_info"

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
        metricList.add(MetricItemVo(JVM_INFO, 1.0f, mapOf(VERSION to version, VENDOR to vendor, RUNTIME to runtime)))
    }
}