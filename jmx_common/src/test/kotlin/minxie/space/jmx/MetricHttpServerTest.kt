package minxie.space.jmx

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MetricHttpServerTest {

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