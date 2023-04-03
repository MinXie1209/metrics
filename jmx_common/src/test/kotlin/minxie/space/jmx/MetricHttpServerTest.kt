package minxie.space.jmx

import org.junit.jupiter.api.Test


open class MetricHttpServerTest {

    @Test
    fun start() {
        Thread {
            MetricHttpServer().start(12345)
        }.let {
            it.name = "MetricHttpServer"
            it
        }.start()
    }
}