package com.group76.portal.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "system")
class SystemProperties {
    var idService: String = ""
    val sns: SnsConfiguration = SnsConfiguration()

    class SnsConfiguration{
        var order: String = ""
        var orderCancelled: String = ""
    }
}

