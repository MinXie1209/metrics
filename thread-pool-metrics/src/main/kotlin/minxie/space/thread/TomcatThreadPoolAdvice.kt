package minxie.space.thread

import minxie.space.metrics.core.thread.ThreadPoolContext
import net.bytebuddy.asm.Advice

object TomcatThreadPoolAdvice {
    @Advice.OnMethodEnter
    @JvmStatic
    fun enter(@Advice.This obj: Any) {
        ThreadPoolContext.addTomcatThreadPool(obj)
    }
}
