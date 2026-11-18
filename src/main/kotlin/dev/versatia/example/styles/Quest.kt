package dev.versatia.example.styles

import dev.versatia.text.Presentable
import dev.versatia.text.presentation
import net.kyori.adventure.text.Component.text

/** Names the style it is shown in, so a message writes `{quest}` and no tag around it. */
class Quest(val name: String, val reward: Int) : Presentable {
    override val style get() = "quest"

    override fun presentation() = presentation {
        component { text(name) }
        property("reward") { reward }
    }
}
