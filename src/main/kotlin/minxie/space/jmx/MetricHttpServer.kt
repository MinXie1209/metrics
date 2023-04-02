package minxie.space.jmx

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import java.lang.management.ManagementFactory
import java.net.InetSocketAddress
import java.text.SimpleDateFormat

class MetricHttpServer {
    fun start(port: Int) {
        println("MetricHttpServer start on port $port")
        HttpServer.create(InetSocketAddress(port), 3).let {
            it.createContext("/metrics", HttpMetricHandler())
            it.start()
        }
    }
}

class HttpMetricHandler : HttpHandler {
    override fun handle(exchange: HttpExchange) {
        println(" ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())} : handle")
        val jvmThreadsState = getJvmThreadsState()
        val response = getJvmInfo()+
                jvmThreadsState
        exchange.sendResponseHeaders(200, response.length.toLong())
        val os = exchange.responseBody
        os.write(response.toByteArray())
        exchange.close()
    }

    /**
     * jvm_info
     */
    private fun getJvmInfo(): String {
        val stringBuilder = StringBuilder()
//        "version", System.getProperty("java.runtime.version", "unknown"),
//        "vendor", System.getProperty("java.vm.vendor", "unknown"),
//        "runtime", System.getProperty("java.runtime.name", "unknown")
        stringBuilder.append("jvm_info{version=\"${System.getProperty("java.runtime.version", "unknown")}\",vendor=\"${System.getProperty("java.vm.vendor", "unknown")}\",runtime=\"${System.getProperty("java.runtime.name", "unknown")}\"} 1")
            .append(System.lineSeparator())
        return stringBuilder.toString()
    }

    /**
     * 获取jvm线程状态
     */
    private fun getJvmThreadsState(): String {
        val threadMXBean = ManagementFactory.getThreadMXBean()
        val stringBuilder = StringBuilder()
        threadMXBean.allThreadIds?.let {
            // 获取了所有的线程id ,先分组
            val threadInfoMap = threadMXBean.getThreadInfo(it, 0).groupBy { it.threadState }
            // 遍历转为
            threadInfoMap.forEach { (threadState, threadInfos) ->
                stringBuilder.append("jvm_threads_state{state=\"${threadState}\",} ${threadInfos.size.toFloat()}")
                    .append(System.lineSeparator())
            }
        }
        stringBuilder.append("jvm_threads_current ${threadMXBean.threadCount.toFloat()}")
            .append(System.lineSeparator())
            .append("jvm_threads_daemon ${threadMXBean.daemonThreadCount.toFloat()}")
            .append(System.lineSeparator())
            .append("jvm_threads_peak ${threadMXBean.peakThreadCount.toFloat()}")
            .append(System.lineSeparator())
            .append("jvm_threads_started_total ${threadMXBean.totalStartedThreadCount.toFloat()}")
            .append(System.lineSeparator())
            .append("jvm_threads_deadlocked ${threadMXBean.findDeadlockedThreads()?.size?.toFloat() ?: 0f}")
            .append(System.lineSeparator())
            .append("jvm_threads_deadlocked_monitor ${threadMXBean.findMonitorDeadlockedThreads()?.size?.toFloat() ?: 0f}")
            .append(System.lineSeparator())
        return stringBuilder.toString()
    }
}