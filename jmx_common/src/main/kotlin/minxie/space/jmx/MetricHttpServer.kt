package minxie.space.jmx

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import minxie.space.jmx.vo.ClassMetricVo
import minxie.space.jmx.vo.GcMetricVo
import minxie.space.jmx.vo.JvmInfoMetricVo
import minxie.space.jmx.vo.MemoryMetricVo
import minxie.space.jmx.vo.ProcessMetricVo
import minxie.space.jmx.vo.ThreadMetricVo
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
        val threadMXBean = ManagementFactory.getThreadMXBean()
        val threadMetricVo = ThreadMetricVo(threadMXBean)
        return threadMetricVo.toString()
    }
}