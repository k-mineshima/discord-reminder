package com.solxana.discord.reminder.config

import com.solxana.discord.reminder.config.property.Environments

data class Configuration(
    val environment: Environments,
    val discordbot: DiscordBot,
    val database: Database
) {
    data class DiscordBot(
        val token: String,
        val defaultPrefix: String,
        val ownerId: String,
        val emojis: Emojis
    ) {
        data class Emojis(
            val success: String,
            val warning: String,
            val error: String
        )
    }

    data class Database(
        val host: String,
        val databaseName: String,
        val user: String,
        val password: String,
        val poolSize: Int
    )
}
