package com.epam.notification.control;

import com.epam.notification.entity.TicketSentEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TicketSentEventSender {
    private final RabbitTemplate template;
    private final Queue ticketSentQueue;
    private final ObjectMapper objectMapper;

    public void send(TicketSentEvent event) {
        try {
            val message = objectMapper.writeValueAsString(event);
            this.template.convertAndSend(ticketSentQueue.getName(), message);
            log.debug(" [x] Sent '" + message + "'");
        }
        catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
