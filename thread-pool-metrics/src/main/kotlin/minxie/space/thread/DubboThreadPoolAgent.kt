package minxie.space.thread

import net.bytebuddy.agent.builder.AgentBuilder
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection
import net.bytebuddy.asm.Advice
import net.bytebuddy.description.type.TypeDescription
import net.bytebuddy.matcher.ElementMatchers
import java.lang.instrument.Instrumentation

/**
 * 插桩 dubbo线程池
 */
object DubboThreadPoolAgent {
    @JvmStatic
    fun premain(arg: String?, instrumentation: Instrumentation) {
        println("DubboThreadPoolAgent premain")
        println("DubboThreadPoolAgent arg: $arg")
        println("DubboThreadPoolAgent instrumentation: $instrumentation")
        // 为prestartAllCoreThreads方法添加一个Advice
        AgentBuilder.Default()
            .with(SelfInjection.Eager())
            .with(AgentBuilder.Listener.StreamWriting.toSystemOut().withTransformationsOnly())
            .with(AgentBuilder.Listener.StreamWriting.toSystemOut().withErrorsOnly())
            .type(ElementMatchers.nameStartsWith<TypeDescription?>("com.alibaba.dubbo.common.threadpool.support").or(
                ElementMatchers.nameStartsWith<TypeDescription?>("org.apache.dubbo.common.threadpool.support")
            ))
            .transform { builder, _, _, _, _ ->
                builder
                    .method(ElementMatchers.nameStartsWith("getExecutor"))
                    .intercept(Advice.to(DubboThreadPoolAdvice::class.java))
            }
            .installOn(instrumentation)

    }
}
