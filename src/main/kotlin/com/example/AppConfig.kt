package com.example

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CommonsRequestLoggingFilter
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

/**
 * Application configuration
 */
@Configuration
class AppConfig {
    
    /**
     * Configure request logging
     */
    @Bean
    fun requestLoggingFilter(): CommonsRequestLoggingFilter {
        val loggingFilter = CommonsRequestLoggingFilter()
        loggingFilter.setIncludeClientInfo(true)
        loggingFilter.setIncludeQueryString(true)
        loggingFilter.setIncludePayload(true)
        loggingFilter.setMaxPayloadLength(10000)
        loggingFilter.setIncludeHeaders(false)
        loggingFilter.setAfterMessagePrefix("REQUEST DATA: ")
        return loggingFilter
    }
    
    /**
     * Configure scheduled executor service for background tasks
     */
    @Bean
    fun scheduledExecutorService(): ScheduledExecutorService {
        return Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors())
    }
}
