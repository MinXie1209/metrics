package minxie.space.thread;

import minxie.space.metrics.core.thread.ThreadPoolContext;
import net.bytebuddy.asm.Advice;


public class JdkThreadPoolExecutorAdvice {
    @Advice.OnMethodExit
    public static void exit(@Advice.This Object obj) {
        ThreadPoolContext.addJdkThreadPool(obj);
    }
}