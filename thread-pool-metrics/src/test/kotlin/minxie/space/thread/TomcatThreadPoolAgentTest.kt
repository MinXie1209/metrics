package minxie.space.thread

import net.bytebuddy.agent.ByteBuddyAgent
import org.junit.Test

class TomcatThreadPoolAgentTest {

    @Test
    fun premain() {
        val notException = try {
            TomcatThreadPoolAgent.premain(null, ByteBuddyAgent.install())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        assert(notException)
    }
}