package minxie.space.metrics.vo


private const val APPLICATION_NAME = "applicationName"

class MetricItemVo(private var itemKey: String, private var itemValue: Float, tagMap: Map<String, String>? = null) {
    private var tagMap = mapOf(APPLICATION_NAME to MetricBaseVo.applicationName)

    init {
        tagMap?.let {
            this.tagMap = it.toMutableMap().also {
                it[APPLICATION_NAME] = MetricBaseVo.applicationName
            }
        }
    }


    override fun toString(): String {
        return if (tagMap.isNotEmpty()) {
            val tagStr = tagMap.map {
                "${it.key} = \"${it.value}\""
            }.joinToString(",")
            "$itemKey{$tagStr} $itemValue${System.lineSeparator()}"
        } else {
            "$itemKey $itemValue ${System.lineSeparator()}"
        }
    }
}