package minxie.space.thread

import net.bytebuddy.agent.ByteBuddyAgent
import org.junit.Test

class DubboThreadPoolAgentTest {

    @Test
    fun premain() {
        val notException = try {
            DubboThreadPoolAgent.premain(null, ByteBuddyAgent.install())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        assert(notException)
    }
}