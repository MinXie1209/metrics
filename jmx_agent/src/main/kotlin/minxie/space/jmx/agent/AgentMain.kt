package minxie.space.jmx.agent


import minxie.space.jmx.MetricHttpServer
import java.lang.instrument.Instrumentation


object AgentMain {
    private const val DEFAULT_PORT = 12345
    private const val DEFAULT_APPLICATION_NAME = "java"

    @JvmStatic
    fun premain(arg: String?, instrumentation: Instrumentation) {
        println("premain")
        println("arg: $arg")
        val (port, applicationName) = arg?.let {
            val args = arg.split(":")
            when {
                args.size > 1 -> {
                    Pair(args[0].toInt(), args[1])
                }

                args.isNotEmpty() -> {
                    Pair(args[0].toInt(), DEFAULT_APPLICATION_NAME)

                }

                else -> {
                    Pair(DEFAULT_PORT, DEFAULT_APPLICATION_NAME)
                }
            }
        } ?: Pair(DEFAULT_PORT, DEFAULT_APPLICATION_NAME)
        Thread {
            MetricHttpServer().start(port, applicationName)
        }.let {
            it.name = "MetricHttpServer"
            it
        }.start()
        println("instrumentation: $instrumentation");
    }

}