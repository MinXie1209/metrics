package minxie.space.metrics.vo

import org.junit.Assert.assertEquals
import org.junit.Test

class MetricsContextTest {

    @Test
    fun getMetricsConfig() {
        assertEquals(MetricsContext.getMetricsConfig(), MetricsContext.getMetricsConfig())
    }
}