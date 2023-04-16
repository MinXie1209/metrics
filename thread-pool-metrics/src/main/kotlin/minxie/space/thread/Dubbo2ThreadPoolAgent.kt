package minxie.space.thread

import net.bytebuddy.agent.builder.AgentBuilder
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection
import net.bytebuddy.asm.Advice
import net.bytebuddy.matcher.ElementMatchers
import java.lang.instrument.Instrumentation

/**
 * 插桩 dubbo2线程池
 */
object Dubbo2ThreadPoolAgent {
    @JvmStatic
    fun premain(arg: String?, instrumentation: Instrumentation) {
        println("Dubbo2ThreadPoolAgent premain")
        println("Dubbo2ThreadPoolAgent arg: $arg")
        println("Dubbo2ThreadPoolAgent instrumentation: $instrumentation")
        // 为prestartAllCoreThreads方法添加一个Advice
        AgentBuilder.Default()
            .with(SelfInjection.Eager())
            .with(AgentBuilder.Listener.StreamWriting.toSystemOut().withTransformationsOnly())
            .with(AgentBuilder.Listener.StreamWriting.toSystemOut().withErrorsOnly())
            .type(ElementMatchers.nameStartsWith("com.alibaba.dubbo.common.threadpool.support"))
            .transform { builder, _, _, _, _ ->
                builder
                    .method(ElementMatchers.nameStartsWith("getExecutor"))
                    .intercept(Advice.to(Dubbo2ThreadPoolAdvice::class.java))
            }
            .installOn(instrumentation)

    }
}
