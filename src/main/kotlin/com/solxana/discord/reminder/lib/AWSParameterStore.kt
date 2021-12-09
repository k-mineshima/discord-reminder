package com.solxana.discord.reminder.lib

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest

class AWSParameterStore(
    private val systemsManagement: AWSSimpleSystemsManagement = AWSSimpleSystemsManagementClientBuilder.defaultClient()
) {
    fun getParameter(parameterName: String): String {
        val request: GetParameterRequest = GetParameterRequest().also {
            it.name = parameterName
            it.withDecryption = true
        }

        return this.systemsManagement.getParameter(request).parameter.value
    }
}
