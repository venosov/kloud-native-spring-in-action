package com.polarbookshop.catalogservice.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "polar")
data class PolarProperties (
    var greeting: String = ""
)
