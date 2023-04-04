package minxie.space.jmx.vo

open class MetricBaseVo {
    protected val metricList = mutableListOf<MetricItemVo>()
    override fun toString(): String {
        val stringBuilder = StringBuilder()
        if (metricList.isNotEmpty()) {
            metricList.forEach {
                stringBuilder.append(it)
            }
        }
        return stringBuilder.toString()
    }

}