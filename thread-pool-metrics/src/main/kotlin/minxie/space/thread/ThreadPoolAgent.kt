package minxie.space.thread

import java.lang.instrument.Instrumentation

object ThreadPoolAgent {
    @JvmStatic
    fun premain(arg: String?, instrumentation: Instrumentation) {
        // 插桩 tomcat线程池
        TomcatThreadPoolAgent.premain(arg, instrumentation)
        // 插桩 jdk线程池
        JdkThreadPoolAgent.premain(arg, instrumentation)
        // 插桩 dubbo2线程池
        Dubbo2ThreadPoolAgent.premain(arg, instrumentation)
    }


}