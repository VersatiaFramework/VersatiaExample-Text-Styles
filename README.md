# VersatiaExample-Text-Styles

Messages that name styles instead of colours: `<positive>Quest {quest} done.` says what the text is, and the server's `plugins/VersatiaCore/styles.yml` says how it looks, for every plugin at once. The styles every plugin shares (`positive`, `negative`, `warning`, `neutral`, `accent`, `hint`, `clickable`, `label`, `heading`) come with VersatiaCore, and a value in braces takes the accent by itself. The plugin adds two styles of its own, `quest` and its tag `questTag`, and ships their defaults in [`styles.yml`](src/main/resources/styles.yml) next to `plugin.yml`; a [`Quest`](src/main/kotlin/dev/versatia/example/styles/Quest.kt) names the style it is shown in, so `{quest}` needs no tag around it.

Part of the [Versatia examples](https://github.com/VersatiaFramework/VersatiaExamples); documentation: [Styles](https://versatia.dev/docs/messages/styles).

**Start with** [`quests.yml`](src/main/resources/resources/messages/quests.yml) and [`styles.yml`](src/main/resources/styles.yml), then [`Quest.kt`](src/main/kotlin/dev/versatia/example/styles/Quest.kt), [`StylesPlugin.kt`](src/main/kotlin/dev/versatia/example/styles/StylesPlugin.kt) and [`QuestBoard.kt`](src/main/kotlin/dev/versatia/example/styles/QuestBoard.kt).

## What the log shows

```
Added the default styles quest, questTag to plugins/VersatiaCore/styles.yml
[Quests] Quest The Lost Mine done: 120 coins.
[Quests] Quest The Lost Mine failed.
Type /quest to see your quests.
Saved 3 quests.
```

The first line comes once: on a server without these styles the plugin's defaults are appended to
`plugins/VersatiaCore/styles.yml` under `# Default styles of VersatiaExample-Text-Styles`. In a
console that shows colours the tag is dark purple and bold, the quest's name light purple, and
the reward, the count and `/quest` white, the accent. A player joining reads `quest.offered` with
their name in that white too.

## Things to try

- Add `positive.accent: "<yellow>"` to `plugins/VersatiaCore/styles.yml` and run
  `/versatia reload`: the reward in `quest.done` and the count in the last line turn yellow, while
  `/quest` in the hint keeps the accent of the top level. No message file was touched, and every
  other plugin using `<positive>` changed with it.
- Add `player: "<gold>"` to that file and reload: the name in `quest.offered` is gold, here and in
  every plugin. A player takes the style named after the type once the server defines one.
- Write `<accent>{quest}</accent>` in `quest.done`: the name looks like the reward. A value that
  is all a style's tag holds has that style; anywhere else a quest takes the one its class names.
- Set `questTag: ""` in the server's file and reload: the messages lose the tag and its space,
  which is part of the style.
- Delete the `quest` line from the server's file and reload: it is back under a new comment,
  because the plugin's messages need it. To change a style, edit its value; the server's value
  always wins over the plugin's default.
- Write `<positiv>` in `quest.done` and run `/versatia reload styles`: the reload fails with
  `message "quest.done" in messages/quests.yml cannot be shown: <positiv> is neither a MiniMessage
  tag nor a style; the styles in plugins/VersatiaCore/styles.yml are: accent, hint, negative,
  neutral, positive, quest, questTag, warning. Write \<positiv> to show it as text`, and
  the previous messages stay in use.

## Building

Requires JDK 25 and VersatiaAPI and
VersatiaCatalog, which supplies every version, in your local Maven repository (`./gradlew publishToMavenLocal` in each checkout; see
[Building the framework](https://versatia.dev/docs/contributing/building));
running it requires a Paper 26.3 server with VersatiaCore installed, and the
[CommandAPI](https://commandapi.jorel.dev) 12.x plugin for `/versatia reload`.

```bash
./gradlew build
```

To have the JAR copied to your server after every build, copy `local.properties.example` to
`local.properties` and set `pluginsDir`. The generated descriptor is in
`build/generated/ksp/main/kotlin`; open it to see exactly what the runtime executes.

The Versatia version is `versatiaVersion` in `gradle.properties`; `settings.gradle.kts` imports
the matching [VersatiaCatalog](https://versatia.dev/docs/getting-started/installation#versatia-catalog),
so the build script writes `libs.versatia.api` and `alias(libs.plugins.ksp)` instead of version
numbers.
