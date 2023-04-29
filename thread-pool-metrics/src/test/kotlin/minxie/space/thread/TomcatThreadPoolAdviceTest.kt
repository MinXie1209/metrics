package minxie.space.thread

import minxie.space.metrics.core.thread.ThreadPoolContext
import org.junit.Test
import java.util.concurrent.Executors

class TomcatThreadPoolAdviceTest {

    @Test
    fun enter() {
        val threadPool = Executors.newFixedThreadPool(1)
        TomcatThreadPoolAdvice.enter(threadPool)
        assert(ThreadPoolContext.getTomcatThreadPoolSet().contains(threadPool))
    }
}