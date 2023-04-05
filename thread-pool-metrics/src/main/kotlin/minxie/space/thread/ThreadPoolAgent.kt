package minxie.space.thread

import java.lang.instrument.Instrumentation

object ThreadPoolAgent {
    @JvmStatic
    fun premain(arg: String?, instrumentation: Instrumentation) {
        // 插桩 tomcat线程池
        TomcatThreadPoolAgent.premain(arg, instrumentation)
    }


}