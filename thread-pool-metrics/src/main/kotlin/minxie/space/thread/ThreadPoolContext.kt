package minxie.space.thread

object ThreadPoolContext {
    private val tomcatThreadPoolSet = mutableSetOf<Any>()
    private val jdkThreadPoolSet = mutableSetOf<Any>()
    fun getTomcatThreadPoolSet(): Set<Any> {
        return tomcatThreadPoolSet
    }

    fun addTomcatThreadPool(obj: Any) {
        println("addTomcatThreadPool: $obj")
        tomcatThreadPoolSet.add(obj)
    }

    fun getJdkThreadPoolSet(): Set<Any> {
        return jdkThreadPoolSet
    }

    fun addJdkThreadPool(obj: Any) {
        println("addJdkThreadPool: $obj")
        jdkThreadPoolSet.add(obj)
    }
}