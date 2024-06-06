package com.colak.springtutorial.controller;


import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDeletedEvent;
import org.springframework.session.events.SessionExpiredEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
@Slf4j
public class SessionController {

    // http://localhost:8080/api/session/check
    @GetMapping("/check")
    public Integer index(HttpSession httpSession) {
        Integer hits = (Integer) httpSession.getAttribute("hits");
        if (hits == null) {
            hits = 0;
        }
        httpSession.setAttribute("hits", ++hits);

        return hits;
    }

    @EventListener(SessionCreatedEvent.class)
    public void onSessionCreatedEvent(SessionCreatedEvent sessionCreatedEvent) {
        log.info("Session created {}", sessionCreatedEvent.getSessionId());
    }

    @EventListener
    public void onSessionExpiredEvent(SessionExpiredEvent sessionExpiredEvent) {
        log.info("Session expired {}", sessionExpiredEvent.getSessionId());
    }

    @EventListener
    public void onSessionDeletedEvent(SessionDeletedEvent sessionDeletedEvent) {
        log.info("Session deleted {}", sessionDeletedEvent.getSessionId());
    }
}
