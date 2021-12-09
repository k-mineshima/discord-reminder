package com.solxana.discord.reminder.config.property

sealed class Environments(
    val name: String
) {
    companion object {
        fun get(value: String): Environments {
            return when {
                "develop|development".toRegex(RegexOption.IGNORE_CASE).matches(value) -> DEVELOPMENT
                "staging".toRegex(RegexOption.IGNORE_CASE).matches(value) -> STAGING
                "prod|production".toRegex(RegexOption.IGNORE_CASE).matches(value) -> PRODUCTION
                else -> CUSTOM(value)
            }
        }
    }

    object DEVELOPMENT: Environments("development")
    object STAGING: Environments("staging")
    object PRODUCTION: Environments("production")
    class CUSTOM(value: String): Environments(value)
}
