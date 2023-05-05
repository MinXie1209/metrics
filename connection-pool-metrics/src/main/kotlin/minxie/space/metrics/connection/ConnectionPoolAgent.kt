package minxie.space.metrics.connection

import java.lang.instrument.Instrumentation

object ConnectionPoolAgent {
    @JvmStatic
    fun premain(arg: String?, instrumentation: Instrumentation) {
        RedisConnectionPoolPoolAgent.premain(arg, instrumentation)
    }
}