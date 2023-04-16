package minxie.space.thread

import minxie.space.metrics.core.thread.ThreadPoolContext
import net.bytebuddy.asm.Advice

object DubboThreadPoolAdvice {
    @Advice.OnMethodExit
    @JvmStatic
    fun exit(@Advice.Return obj: Any) {
        ThreadPoolContext.addDubboThreadPool(obj)
    }
}
