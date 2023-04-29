package minxie.space.thread

import net.bytebuddy.agent.ByteBuddyAgent
import org.junit.Test

class RocketMQThreadPoolAgentTest {

    @Test
    fun premain() {
        val notException = try {
            RocketMQThreadPoolAgent.premain(null, ByteBuddyAgent.install())
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        assert(notException)
    }
}