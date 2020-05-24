package com.epam.notification.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor
public class TicketSentEvent {
    private final UUID eventId = UUID.randomUUID();
    private final LocalDateTime creationTime = LocalDateTime.now();
    private final Long orderId;
}
