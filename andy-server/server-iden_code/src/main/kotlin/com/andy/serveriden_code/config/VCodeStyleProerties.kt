package com.andy.serveriden_code.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "verification.code.attribute")
class VCodeStyleProerties {


    var height: Int = 220

    var width: Int = 220

    var defaultLength: Int = 4

}