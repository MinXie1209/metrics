package minxie.space.thread

import minxie.space.metrics.core.thread.ThreadPoolContext
import net.bytebuddy.asm.Advice

object Dubbo2ThreadPoolAdvice {
    @Advice.OnMethodExit
    @JvmStatic
    fun exit(@Advice.Return obj: Any) {
        ThreadPoolContext.addDubbo2ThreadPool(obj)
    }
}
