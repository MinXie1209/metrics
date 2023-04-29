package minxie.space.server

import minxie.space.metrics.enums.MetricKeyEnum
import org.junit.Test

class HttpMetricResponseTest {
    @Test
    fun test() {
        val httpMetricResponse = HttpMetricResponse
        assert(httpMetricResponse.response().contains(MetricKeyEnum.JVM_INFO.key))
    }
}