package minxie.space.thread

import net.bytebuddy.agent.builder.AgentBuilder
import net.bytebuddy.asm.Advice
import net.bytebuddy.matcher.ElementMatchers
import java.lang.instrument.Instrumentation
import java.util.concurrent.ThreadPoolExecutor

/**
 * 插桩 jdk线程池
 */
object JdkThreadPoolAgent {
    @JvmStatic
    fun premain(arg: String?, instrumentation: Instrumentation) {
        AgentBuilder.Default()
            .disableClassFormatChanges()
            .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
            .with(AgentBuilder.InitializationStrategy.NoOp.INSTANCE)
            .with(AgentBuilder.InjectionStrategy.UsingUnsafe.INSTANCE)
            .with(AgentBuilder.TypeStrategy.Default.REDEFINE)
            .with(AgentBuilder.Listener.StreamWriting.toSystemOut().withTransformationsOnly())
            .with(AgentBuilder.Listener.StreamWriting.toSystemOut().withErrorsOnly())
            .ignore(ElementMatchers.noneOf(ThreadPoolExecutor::class.java))
            .type(ElementMatchers.`is`(ThreadPoolExecutor::class.java))
            .transform { builder, _, _, _, _ ->
                builder.visit(Advice.to(JdkThreadPoolExecutorAdvice::class.java).on(ElementMatchers.isConstructor()))
            }.installOn(instrumentation)

    }
}
