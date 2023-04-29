package minxie.space.thread

import minxie.space.metrics.core.thread.ThreadPoolContext
import org.junit.Test
import java.util.concurrent.Executors

class RocketMQThreadPoolAdviceTest {

    @Test
    fun exit() {
        val consumeMessageConcurrentlyService = ConsumeMessageConcurrentlyService().apply {
            this.consumeExecutor = Executors.newFixedThreadPool(1)
        }
        RocketMQThreadPoolAdvice.exit(consumeMessageConcurrentlyService)
        assert(
            ThreadPoolContext.getRocketMQThreadPoolMap().containsKey(consumeMessageConcurrentlyService.consumeExecutor)
        )
    }
}