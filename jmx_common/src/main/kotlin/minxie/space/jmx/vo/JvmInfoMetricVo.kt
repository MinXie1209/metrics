package minxie.space.jmx.vo


/**
 * JvmInfo指标
 */
object JvmInfoMetricVo {
    private val version = System.getProperty("java.runtime.version", "unknown")
    private val vendor = System.getProperty("java.vm.vendor", "unknown")
    private val runtime = System.getProperty("java.runtime.name", "unknown")
    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(
            "jvm_info{version=\"$version\",vendor=\"$vendor\",runtime=\"$runtime\"} 1.0"
        ).append(System.lineSeparator())
        return stringBuilder.toString()
    }
}