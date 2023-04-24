package minxie.space.metrics.vo

import minxie.space.metrics.enums.MetricKeyEnum


private const val APPLICATION_NAME = "applicationName"

class MetricItemVo {
    private var itemKey: String = ""
    private var itemValue: Float = 0.0f
    private var tagMap = mapOf(APPLICATION_NAME to MetricsContext.getMetricsConfig().applicationName)

    companion object {
        fun build(itemKey: MetricKeyEnum, itemValue: Float, tagMap: Map<String, String>? = null): MetricItemVo? {
            // 判断是否是需要采集的指标,如果不是则返回空
            if (DisplayEnum.display(itemKey.display)) {
                return MetricItemVo(itemKey, itemValue, tagMap)
            }
            return null
        }
    }

    private constructor(itemKey: MetricKeyEnum, itemValue: Float, tagMap: Map<String, String>? = null) {
        this.itemKey = itemKey.key
        this.itemValue = itemValue
        tagMap?.let {
            this.tagMap = it + this.tagMap
        }
    }


    override fun toString(): String {
        return if (tagMap.isNotEmpty()) {
            val tagStr = tagMap.map {
                "${it.key} = \"${it.value}\""
            }.joinToString(",")
            "${itemKey}{$tagStr} $itemValue${System.lineSeparator()}"
        } else {
            "${itemKey} $itemValue ${System.lineSeparator()}"
        }
    }
}