package dev.versatia.example.styles

import dev.versatia.messages.Messages
import dev.versatia.text.arg
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

/** `{player}` stands out without a tag: a value takes the server's `accent`, or its `player` style once the server defines one; `{quest}` takes the style its class names. */
class QuestBoard(private val messages: Messages) : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) =
        messages["quest.offered"].send(event.player, arg("player", event.player), arg("quest", Quest("The Lost Mine", 120)))
}
