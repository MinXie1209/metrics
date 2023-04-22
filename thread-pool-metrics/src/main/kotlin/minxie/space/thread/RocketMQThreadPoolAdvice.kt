package minxie.space.thread

import minxie.space.metrics.core.thread.ThreadPoolContext
import net.bytebuddy.asm.Advice

object RocketMQThreadPoolAdvice {
    @Advice.OnMethodExit
    @JvmStatic
    fun exit(@Advice.This obj: Any) {
        println("RocketMQThreadPoolAdvice exit: $obj")
        val consumeExecutorField = obj.javaClass.getDeclaredField("consumeExecutor")
        consumeExecutorField.isAccessible = true
        consumeExecutorField.get(obj)?.let {
            ThreadPoolContext.addRocketMQThreadPool(it)
        }
    }
}
