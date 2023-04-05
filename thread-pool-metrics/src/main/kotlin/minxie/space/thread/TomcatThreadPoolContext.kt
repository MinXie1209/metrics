package minxie.space.thread

object TomcatThreadPoolContext {
    private val threadPoolSet = mutableSetOf<Any>()
    fun getThreadPoolSet(): Set<Any> {
        return threadPoolSet
    }

    fun addThreadPool(obj: Any) {
        threadPoolSet.add(obj)
    }
}