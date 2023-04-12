package minxie.space.thread

import net.bytebuddy.agent.builder.AgentBuilder
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection
import net.bytebuddy.asm.Advice
import net.bytebuddy.matcher.ElementMatchers
import java.lang.instrument.Instrumentation

/**
 * 插桩 tomcat线程池
 */
object TomcatThreadPoolAgent {
    @JvmStatic
    fun premain(arg: String?, instrumentation: Instrumentation) {
        println("TomcatThreadPoolAgent premain")
        println("TomcatThreadPoolAgent arg: $arg")
        println("TomcatThreadPoolAgent instrumentation: $instrumentation")
        // 为prestartAllCoreThreads方法添加一个Advice
        AgentBuilder.Default()
            .with(SelfInjection.Eager())
            .type(ElementMatchers.nameStartsWith("org.apache.tomcat.util.threads.ThreadPoolExecutor"))
            .transform { builder, _, _, _, _ ->
                builder
                    .method(ElementMatchers.nameContains("prestartAllCoreThreads"))
                    .intercept(Advice.to(TomcatThreadPoolAdvice::class.java))
            }
            .installOn(instrumentation)

    }
}
