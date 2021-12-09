package com.solxana.discord.reminder.bot

import com.jagrosh.jdautilities.command.CommandClient
import com.jagrosh.jdautilities.command.CommandClientBuilder
import com.solxana.discord.reminder.config.Configuration
import com.solxana.discord.reminder.config.ConfigurationProvider
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class ReminderBOT {
    private val jda: JDA

    init {
        val configuration: Configuration = ConfigurationProvider.getConfiguration()

        val commandClient: CommandClient = CommandClientBuilder().also {
            it.setActivity(Activity.playing("Reminder"))
            it.setOwnerId(configuration.discordbot.ownerId)
            it.setEmojis(
                configuration.discordbot.emojis.success,
                configuration.discordbot.emojis.warning,
                configuration.discordbot.emojis.error
            )
            it.setPrefixFunction(this::getPrefix)
        }.build()

        this.jda = JDABuilder.createDefault(configuration.discordbot.token).also {
            it.setStatus(OnlineStatus.DO_NOT_DISTURB)
            it.setActivity(Activity.playing("loading..."))
            it.addEventListeners(commandClient)
        }.build()
    }

    fun shutdown() {
        this.jda.shutdown()
    }

    private fun getPrefix(event: MessageReceivedEvent): String {
        return "!"
    }
}
