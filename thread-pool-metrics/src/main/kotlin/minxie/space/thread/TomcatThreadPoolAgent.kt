package minxie.space.thread

import net.bytebuddy.agent.builder.AgentBuilder
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection
import net.bytebuddy.asm.Advice
import net.bytebuddy.description.type.TypeDescription
import net.bytebuddy.dynamic.DynamicType
import net.bytebuddy.matcher.ElementMatchers
import net.bytebuddy.utility.JavaModule
import java.lang.Exception
import java.lang.instrument.Instrumentation
import java.security.ProtectionDomain

/**
 * 插桩 tomcat线程池
 */
object TomcatThreadPoolAgent {
    @JvmStatic
    fun premain(arg: String?, instrumentation: Instrumentation) {
        println("TomcatThreadPoolAgent premain")
        println("TomcatThreadPoolAgent arg: $arg")
        println("TomcatThreadPoolAgent instrumentation: $instrumentation")
        // 为所有的方法添加一个Advice
        try {
            AgentBuilder.Default()
                .with(SelfInjection.Eager())
                .type(ElementMatchers.nameStartsWith("org.apache.tomcat.util.threads.ThreadPoolExecutor"))
                .transform { builder: DynamicType.Builder<*>, typeDescription: TypeDescription?, classLoader: ClassLoader?, module: JavaModule?, domain: ProtectionDomain? ->
                    builder
                        .method(ElementMatchers.nameContains("prestartAllCoreThreads"))
                        .intercept(Advice.to(TomcatThreadPoolAdvice::class.java))
                }
                .installOn(instrumentation)
        } catch (e: Exception) {
            println("error")
            e.printStackTrace()
        }

    }
}

object TomcatThreadPoolAdvice {
    @Advice.OnMethodEnter
    @JvmStatic
    fun enter(@Advice.This obj: Any) {
        ThreadPoolContext.addTomcatThreadPool(obj)
    }
}
