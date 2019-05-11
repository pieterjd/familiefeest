package com.pieterjd.familiefeest.controller;

import com.pieterjd.familiefeest.domain.Event;
import com.pieterjd.familiefeest.domain.EventCode;
import com.pieterjd.familiefeest.domain.User;
import com.pieterjd.familiefeest.repository.EventCodeRepository;
import com.pieterjd.familiefeest.repository.EventRepository;
import com.pieterjd.familiefeest.repository.UserRepository;
import com.pieterjd.familiefeest.service.EventCodeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/eventcode")
@Log4j2
public class EventCodeController {
    private final EventCodeService eventCodeService;
    private final EventCodeRepository eventCodeRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public EventCodeController(EventCodeService eventCodeService, EventCodeRepository eventCodeRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.eventCodeService = eventCodeService;
        this.eventCodeRepository = eventCodeRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{eventId}/{userId}")
    public EventCode generate(@PathVariable Long eventId, @PathVariable Long userId) {
        EventCode result = null;
        log.debug(String.format("eventid %d and user id %d", eventId, userId));
        Optional<Event> e = eventRepository.findById(eventId);
        Optional<User> u = userRepository.findById(userId);
        if (e.isPresent() && u.isPresent()) {
            result = eventCodeService.generateCodeForUser(e.get(), u.get());
            eventCodeRepository.save(result);

        }
        return result;
    }
}
