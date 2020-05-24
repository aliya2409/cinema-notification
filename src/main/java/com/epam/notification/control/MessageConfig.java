package com.epam.notification.control;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean
    Queue ticketSentQueue() {
        return new Queue("ticket-sent", false);
    }
}
