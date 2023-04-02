package minxie.space.jmx.agent


import java.lang.instrument.Instrumentation
import minxie.space.jmx.MetricHttpServer


object AgentMain {
    private val DEFAULT_PORT = 12345

    @JvmStatic
    fun premain(arg: String?, instrumentation: Instrumentation) {
        println("premain")
        println("arg: " + arg)
        val port = arg?.let {
            val args = arg.split(":")
            if (args.isNotEmpty()) {
                args[0].toInt()
            } else {
                DEFAULT_PORT
            }
        } ?: DEFAULT_PORT
        Thread {
            MetricHttpServer().start(port)
        }.let {
            it.name = "MetricHttpServer"
            it
        }.start()
        println("instrumentation: " + instrumentation);
    }

}