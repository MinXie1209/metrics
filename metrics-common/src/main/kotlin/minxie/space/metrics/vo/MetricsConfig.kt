package minxie.space.metrics.vo

import org.yaml.snakeyaml.Yaml
import java.io.File


object MetricsContext {
    // 单例模式
    private var metricsConfig: MetricsConfig? = null
    fun getMetricsConfig(): MetricsConfig {
        if (metricsConfig == null) {
            synchronized(MetricsContext::class.java) {
                if (metricsConfig != null) return metricsConfig!!
                val location = MetricsContext.javaClass.protectionDomain.codeSource.location
                try {
                    metricsConfig = Yaml().loadAs(
                        File("${File(location.path).parent}${File.separator}metrics-config.yml").inputStream(),
                        MetricsConfig::class.java
                    ) ?: MetricsConfig()
                } catch (e: Exception) {
                    MetricsConfig()
                }
            }

        }
        return metricsConfig!!
    }

}

class MetricsConfig {
    /**
     * 显示的内容
     */
    var display: Set<String> = setOf(DisplayEnum.ALL.type)

    /**
     * 暴露的端口
     */
    var port: Int = 12345

    /**
     * 应用名称
     */
    var applicationName: String = "java"
}

/**
 *   - all
 *   - overview
 *   - gc
 *   - thread
 *   - thread_used
 *   - thread_state
 *   - thread_info
 *   - heap_memory
 *   - jdk_thread_pool
 *   - dubbo_thread_pool
 *   - tomcat_thread_pool
 *   - mq_thread_pool
 */
enum class DisplayEnum(val type: String) {
    ALL("all"),
    OVERVIEW("overview"),
    GC("gc"),
    THREAD("thread"),
    THREAD_INFO("thread_info"),
    HEAP_MEMORY("heap_memory"),
    JDK_THREAD_POOL("jdk_thread_pool"),
    DUBBO_THREAD_POOL("dubbo_thread_pool"),
    ROCKETMQ_THREAD_POOL("rocketmq_thread_pool"),
    TOMCAT_THREAD_POOL("tomcat_thread_pool");

    companion object {
        fun display(displayEnum: DisplayEnum): Boolean {
            return MetricsContext.getMetricsConfig().display.contains(displayEnum.type)
                    || MetricsContext.getMetricsConfig().display.contains(ALL.type)
        }
    }
}

