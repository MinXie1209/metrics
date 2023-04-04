package minxie.space.jmx

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import minxie.space.jmx.vo.metrics.ClassMetricVo
import minxie.space.jmx.vo.metrics.GcMetricVo
import minxie.space.jmx.vo.metrics.JvmInfoMetricVo
import minxie.space.jmx.vo.metrics.MemoryMetricVo
import minxie.space.jmx.vo.metrics.ProcessMetricVo
import minxie.space.jmx.vo.metrics.ThreadMetricVo
import java.net.InetSocketAddress
import java.text.SimpleDateFormat

class MetricHttpServer {
    companion object {
        var applicationName = "java"
    }

    fun start(port: Int, applicationName: String) {
        MetricHttpServer.applicationName = applicationName
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
            "${getJvmInfo()}${getClassInfo()}${getGcInfo()}${getMemoryInfo()}${getProcessInfo()}${getJvmThreadsState()}"
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
}