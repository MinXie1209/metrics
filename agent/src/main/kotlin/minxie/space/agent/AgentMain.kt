package minxie.space.agent


import minxie.space.metrics.connection.ConnectionPoolAgent
import minxie.space.server.MetricHttpServer
import minxie.space.thread.ThreadPoolAgent
import java.lang.instrument.Instrumentation


object AgentMain {
    @JvmStatic
    fun premain(arg: String?, instrumentation: Instrumentation) {
        println("premain")
        println("arg: $arg")
        Thread {
            MetricHttpServer().start()
        }.let {
            it.isDaemon = true
            it.name = "MetricHttpServer"
            it
        }.start()
        println("instrumentation: $instrumentation")

        // 插桩线程池
        ThreadPoolAgent.premain(arg, instrumentation)
        // 插桩连接池
        ConnectionPoolAgent.premain(arg, instrumentation)
    }

}