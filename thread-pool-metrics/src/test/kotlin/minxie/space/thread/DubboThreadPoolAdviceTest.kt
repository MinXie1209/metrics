package minxie.space.thread

import minxie.space.metrics.core.thread.ThreadPoolContext
import org.junit.Test
import java.util.concurrent.Executors

class DubboThreadPoolAdviceTest {

    @Test
    fun exit() {
        val threadPool = Executors.newFixedThreadPool(1)
        DubboThreadPoolAdvice.exit(threadPool)
        assert(ThreadPoolContext.getDubboThreadPoolMap().containsKey(threadPool))
    }
}