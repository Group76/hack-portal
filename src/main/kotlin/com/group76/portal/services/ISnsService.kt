package com.group76.portal.services

import software.amazon.awssdk.http.SdkHttpResponse

interface ISnsService {
    fun publishMessage(
        topicArn: String,
        message: Any,
        subject: String,
        id: String
    ): SdkHttpResponse

    fun getTopicArnByName(
        topicName: String
    ): String?
}