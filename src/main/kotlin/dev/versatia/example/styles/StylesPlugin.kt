package dev.versatia.example.styles

import dev.versatia.FriendlyName
import dev.versatia.Phase
import dev.versatia.VersatiaPlugin
import dev.versatia.lifecycle.AutoInvoke
import dev.versatia.messages.messages
import dev.versatia.text.arg
import dev.versatia.text.text

/**
 * No colour is written in this plugin. Its messages name styles: the ones every plugin shares come
 * with VersatiaCore, `styles.yml` next to `plugin.yml` ships the defaults of the plugin's own
 * `quest` and `questTag`, and the server's `plugins/VersatiaCore/styles.yml` says how each of them
 * looks. A [Quest] names its style itself, so no message wraps it in a tag.
 */
@FriendlyName("styles")
class StylesPlugin : VersatiaPlugin() {
    /** Runs at start and at `/versatia reload`, which reads the server's styles again first, so an edited style shows here. */
    @AutoInvoke(Phase.PLUGIN_RELOAD_OR_START)
    fun show() {
        val quest = arg("quest", Quest("The Lost Mine", 120))
        componentLogger.info(messages["quest.done"].get(quest))
        componentLogger.info(messages["quest.failed"].get(quest))
        componentLogger.info(messages["quest.hint"].get())
        // Text built in code takes the same styles, and its values the same accent
        componentLogger.info(text.parse("<positive>Saved {count} quests.", arg("count", 3)))
    }
}
