package com.epam.notification.boundary;

import com.epam.notification.control.EmailNotificationService;
import com.epam.notification.control.TicketSentEventSender;
import com.epam.notification.entity.OrderFinishedEvent;
import com.epam.notification.entity.TicketSentEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "order-finished-notification")
@RequiredArgsConstructor
public class OrderFinishedEventHandler {
    private final ObjectMapper mapper;
    private final EmailNotificationService emailService;
    private final TicketSentEventSender ticketSentEventSender;

    @RabbitHandler
    public void receive(String in) {
        try {
            val event = mapper.readValue(in, OrderFinishedEvent.class);
            emailService.sendTicket(event.getEmail());
            ticketSentEventSender.send(new TicketSentEvent(event.getOrderId()));
        } catch (JsonProcessingException e) {
            log.error("Unable to parse line: " + in);
        }
    }
}
