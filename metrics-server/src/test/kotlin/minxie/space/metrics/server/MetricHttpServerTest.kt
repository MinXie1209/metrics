package minxie.space.metrics.server

import minxie.space.server.MetricHttpServer
import org.junit.jupiter.api.Test


open class MetricHttpServerTest {

    @Test
    fun start() {
        Thread {
            MetricHttpServer().start()
        }.let {
            it.name = "MetricHttpServer"
            it
        }.start()
    }
}