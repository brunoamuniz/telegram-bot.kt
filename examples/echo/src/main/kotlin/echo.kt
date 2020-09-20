import io.nooblabs.kbot.bot
import io.nooblabs.kbot.models.Message
import io.nooblabs.kbot.network.TClient
import io.nooblabs.kbot.text
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    val bot = bot {
        token = System.getenv("TOKEN")
        usePolling = true
        pollDelaySeconds = 2

        listeners {
//            generic { client: TClient, update: Update ->
//                val chatId = update.message?.chat?.id!!
//                val text = update.message?.text!!
//                client.sendMessage(chatId, text)
//            }

            text(match = "start") { client: TClient, message: Message, _: MatchResult ->
                val chatId = message.chat?.id!!
                client.sendMessage(chatId, "Lets gooo!!!!")
            }

            text(match = """^url (\w+)$""".toRegex()) { client: TClient, message: Message, matchResult: MatchResult ->
                val chatId = message.chat?.id!!
                client.sendMessage(chatId, matchResult.groups[1]!!.value)
            }
        }
    }
    bot.startPolling()
}
