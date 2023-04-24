package minxie.space.server

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpRequestDecoder
import io.netty.handler.codec.http.HttpResponseEncoder
import minxie.space.jvm.vo.metrics.*
import minxie.space.metrics.vo.MetricBaseVo
import minxie.space.metrics.vo.MetricsContext
import minxie.space.thread.metrics.DubboThreadPoolMetricsVo
import minxie.space.thread.metrics.JdkThreadPoolMetricsVo
import minxie.space.thread.metrics.RocketMQThreadPoolMetricsVo
import minxie.space.thread.metrics.TomcatThreadPoolMetricsVo

class MetricHttpServer {

    @Throws(Exception::class)
    fun start() {
        println("MetricHttpServer start on port ${MetricsContext.getMetricsConfig().port} applicationName-${MetricsContext.getMetricsConfig().applicationName}")
        val bossGroup: EventLoopGroup = NioEventLoopGroup()
        val workerGroup: EventLoopGroup = NioEventLoopGroup()
        try {
            val b = ServerBootstrap()
            b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel::class.java)
                .childHandler(object : ChannelInitializer<SocketChannel>() {
                    @Throws(Exception::class)
                    override fun initChannel(ch: SocketChannel) {
                        ch.pipeline().addLast(HttpRequestDecoder())
                        ch.pipeline().addLast(HttpResponseEncoder())
                        ch.pipeline().addLast("aggregator", HttpObjectAggregator(10 * 1024 * 1024))
                        ch.pipeline().addLast(HttpServerHandler())
                    }
                })
            val f = b.bind(MetricsContext.getMetricsConfig().port).sync()
            f.channel().closeFuture().sync()
        } finally {
            workerGroup.shutdownGracefully()
            bossGroup.shutdownGracefully()
        }
    }

}

object HttpMetricResponse {

    fun response(): String {
        val responseStr = StringBuilder()
        responseStr.append(getJvmInfo())
        responseStr.append(getClassInfo())
        responseStr.append(getGcInfo())
        responseStr.append(getMemoryInfo())
        responseStr.append(getProcessInfo())
        responseStr.append(getJvmThreadsState())
        responseStr.append(getTomcatThreadPoolInfo())
        responseStr.append(getJdkThreadPoolInfo())
        responseStr.append(getDubboThreadPoolInfo())
        responseStr.append(getRocketMQThreadPoolInfo())
        return responseStr.toString()
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

    private fun getDubboThreadPoolInfo(): String {
        return DubboThreadPoolMetricsVo().toString()
    }


    private fun getRocketMQThreadPoolInfo(): String {
        return RocketMQThreadPoolMetricsVo().toString()
    }
}