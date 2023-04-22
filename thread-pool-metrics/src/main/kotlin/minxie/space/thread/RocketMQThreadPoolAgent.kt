package minxie.space.thread

import net.bytebuddy.agent.builder.AgentBuilder
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection
import net.bytebuddy.asm.Advice
import net.bytebuddy.description.type.TypeDescription
import net.bytebuddy.matcher.ElementMatchers
import java.lang.instrument.Instrumentation
import java.util.concurrent.ThreadPoolExecutor

/**
 * 插桩 rocketmq线程池
 * 消费者线程池
 * org.apache.rocketmq.client.impl.consumer.ConsumeMessageOrderlyService
 * org.apache.rocketmq.client.impl.consumer.ConsumeMessageConcurrentlyService
 */
object RocketMQThreadPoolAgent {
    @JvmStatic
    fun premain(arg: String?, instrumentation: Instrumentation) {
        println("RocketMQThreadPoolAgent premain")
        println("RocketMQThreadPoolAgent arg: $arg")
        println("RocketMQThreadPoolAgent instrumentation: $instrumentation")
        // 为prestartAllCoreThreads方法添加一个Advice
        AgentBuilder.Default()
            .with(SelfInjection.Eager())
            .with(AgentBuilder.Listener.StreamWriting.toSystemOut().withTransformationsOnly())
            .with(AgentBuilder.Listener.StreamWriting.toSystemOut().withErrorsOnly())
            .type(ElementMatchers.nameStartsWith<TypeDescription?>("org.apache.rocketmq.client.impl.consumer.ConsumeMessageOrderlyService").or(
                ElementMatchers.nameStartsWith<TypeDescription?>("org.apache.rocketmq.client.impl.consumer.ConsumeMessageConcurrentlyService")
            ))
            .transform { builder, _, _, _, _ ->
                builder
                    .method(ElementMatchers.nameStartsWith("start"))
                    .intercept(Advice.to(RocketMQThreadPoolAdvice::class.java))
            }
            .installOn(instrumentation)


    }
}
