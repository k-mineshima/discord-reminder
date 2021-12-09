package com.solxana.discord.reminder.config

import com.solxana.discord.reminder.config.property.Environments
import com.solxana.discord.reminder.exception.EnvironmentNotSpecifiedException
import com.solxana.discord.reminder.lib.AWSParameterStore

//TODO: 例外処理を実装する
//TODO: 定数を切り出す
//TODO: parameter store の key を切り出す
object ConfigurationProvider {
    fun getConfiguration(): Configuration {
        val parameterStore = AWSParameterStore()

        val environmentValue: String = System.getenv("ENVIRONMENT") ?: throw EnvironmentNotSpecifiedException()

        val parameterStoreBasePath = "/reminder/${environmentValue}"

        return Configuration(
            environment = Environments.get(environmentValue),
            discordbot = Configuration.DiscordBot(
                token = parameterStore.getParameter("${parameterStoreBasePath}/discordbot/token"),
                defaultPrefix = parameterStore.getParameter("${parameterStoreBasePath}/discordbot/default-prefix"),
                ownerId = parameterStore.getParameter("${parameterStoreBasePath}/discordbot/owner-id"),
                emojis = Configuration.DiscordBot.Emojis(
                    success = parameterStore.getParameter("${parameterStoreBasePath}/discordbot/emojis/success"),
                    warning = parameterStore.getParameter("${parameterStoreBasePath}/discordbot/emojis/warning"),
                    error = parameterStore.getParameter("${parameterStoreBasePath}/discordbot/emojis/error")
                )
            ),
            database = Configuration.Database(
                host = parameterStore.getParameter("${parameterStoreBasePath}/database/host"),
                databaseName = parameterStore.getParameter("${parameterStoreBasePath}/database/database-name"),
                user = parameterStore.getParameter("${parameterStoreBasePath}/database/user"),
                password = parameterStore.getParameter("${parameterStoreBasePath}/database/password"),
                poolSize = parameterStore.getParameter("${parameterStoreBasePath}/database/pool-size").toInt()
            )
        )
    }
}
