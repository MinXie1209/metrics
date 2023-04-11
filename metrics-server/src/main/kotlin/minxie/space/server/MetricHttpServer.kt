package minxie.space.server

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import minxie.space.jvm.vo.metrics.*
import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.thread.metrics.JdkThreadPoolMetricsVo
import minxie.space.thread.metrics.TomcatThreadPoolMetricsVo
import java.net.InetSocketAddress
import java.text.SimpleDateFormat

class MetricHttpServer {


    fun start(port: Int, applicationName: String) {
        MetricBaseVo.applicationName = applicationName
        println("MetricHttpServer start on port $port applicationName-$applicationName")
        HttpServer.create(InetSocketAddress(port), 3).let {
            it.createContext("/metrics", HttpMetricHandler())
            it.start()
        }
    }
}

class HttpMetricHandler : HttpHandler {
    override fun handle(exchange: HttpExchange) {
        println("${SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis())} : POST: /metrics")
        val response =
            "${getJvmInfo()}${getClassInfo()}${getGcInfo()}${getMemoryInfo()}${getProcessInfo()}${getJvmThreadsState()}${getTomcatThreadPoolInfo()}${getJdkThreadPoolInfo()}"
        exchange.sendResponseHeaders(200, response.length.toLong())
        val os = exchange.responseBody
        os.write(response.toByteArray())
        exchange.close()
    }

    private fun getGcInfo(): String {
        return GcMetricVo().toString()
    }

    private fun getClassInfo(): String {
        return ClassMetricVo().toString()
    }

    private fun getMemoryInfo(): String {
        return MemoryMetricVo().toString()

    }

    private fun getProcessInfo(): String {
        return ProcessMetricVo().toString()
    }

    /**
     * jvm_info
     */
    private fun getJvmInfo(): String {
        return JvmInfoMetricVo.toString()
    }

    /**
     * 获取jvm线程状态
     */
    private fun getJvmThreadsState(): String {
        return ThreadMetricVo().toString()
    }

    private fun getTomcatThreadPoolInfo(): String {
        return TomcatThreadPoolMetricsVo().toString()
    }

    private fun getJdkThreadPoolInfo(): String {
        return JdkThreadPoolMetricsVo().toString()
    }
}