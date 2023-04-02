import minxie.space.jmx.MetricHttpServer

fun main(args: Array<String>) {

    Thread {
        MetricHttpServer().start(12345)
    }.let {
        it.name = "MetricHttpServer"
        it
    }.start()

}