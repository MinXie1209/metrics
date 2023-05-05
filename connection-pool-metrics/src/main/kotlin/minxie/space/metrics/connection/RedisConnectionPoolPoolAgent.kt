package minxie.space.metrics.connection

import net.bytebuddy.agent.builder.AgentBuilder
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection
import net.bytebuddy.asm.Advice
import net.bytebuddy.matcher.ElementMatchers
import java.lang.instrument.Instrumentation

/**
 * 插桩 Redis 连接池
 */
object RedisConnectionPoolPoolAgent {
    @JvmStatic
    fun premain(arg: String?, instrumentation: Instrumentation) {
        println("RedisConnectionPoolPoolAgent premain")
        println("RedisConnectionPoolPoolAgent arg: $arg")
        println("RedisConnectionPoolPoolAgent instrumentation: $instrumentation")
        AgentBuilder.Default()
            .with(SelfInjection.Eager())
            .with(AgentBuilder.Listener.StreamWriting.toSystemOut().withTransformationsOnly())
            .with(AgentBuilder.Listener.StreamWriting.toSystemOut().withErrorsOnly())
            .type(ElementMatchers.named("org.apache.commons.pool2.impl.GenericObjectPool"))
            .transform { builder, _, _, _, _ ->
                builder.visit(Advice.to(RedisConnectionPoolAdvice::class.java).on(ElementMatchers.isConstructor()))
            }
            .installOn(instrumentation)

    }
}
