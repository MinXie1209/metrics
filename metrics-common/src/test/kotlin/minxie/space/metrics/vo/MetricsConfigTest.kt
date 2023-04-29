package minxie.space.metrics.vo

import org.junit.Test
import java.io.File


class MetricsConfigTest {

    @Test
    fun test() {
        File("/Users/minxie/IdeaProjects/metrics/agent/target/agent-1.0-SNAPSHOT.jar").parent
        val file = File("/Users/minxie/IdeaProjects/metrics/agent/target/agent-1.0-SNAPSHOT.jar/../metrics-config.yml")
        println(file)
//        println(MetricsContext.getMetricsConfig())
//        println(MetricsContext.getMetricsConfig())

    }
}