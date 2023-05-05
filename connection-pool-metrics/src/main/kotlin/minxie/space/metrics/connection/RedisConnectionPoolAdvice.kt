package minxie.space.metrics.connection

import net.bytebuddy.asm.Advice
import net.bytebuddy.asm.Advice.Argument

object RedisConnectionPoolAdvice {
    @Advice.OnMethodExit
    @JvmStatic
    fun exit(@Advice.This obj: Any, @Argument(value = 0) arg: Any) {
        println("RedisConnectionPoolAdvice enter obj: $obj, arg: $arg")
    }
}
