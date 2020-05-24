package com.epam.notification.boundary;

import com.epam.notification.control.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {
    private final EmailNotificationService emailNotificationService;

    @PostMapping("/send")
    public ResponseEntity send(@RequestParam String to) {
        emailNotificationService.sendTicket(to);
        return new ResponseEntity(HttpStatus.OK);
    }
}
