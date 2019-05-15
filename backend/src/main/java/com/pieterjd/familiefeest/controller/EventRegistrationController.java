package com.pieterjd.familiefeest.controller;

import com.pieterjd.familiefeest.domain.Event;
import com.pieterjd.familiefeest.domain.EventRegistration;
import com.pieterjd.familiefeest.domain.User;
import com.pieterjd.familiefeest.repository.EventRegistrationRepository;
import com.pieterjd.familiefeest.repository.EventRepository;
import com.pieterjd.familiefeest.repository.UserRepository;
import com.pieterjd.familiefeest.service.EventRegistrationService;
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
public class EventRegistrationController {
    private final EventRegistrationService eventRegistrationService;
    private final EventRegistrationRepository eventRegistrationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public EventRegistrationController(EventRegistrationService eventRegistrationService, EventRegistrationRepository eventRegistrationRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.eventRegistrationService = eventRegistrationService;
        this.eventRegistrationRepository = eventRegistrationRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{eventCode}")
    public EventRegistration getEventCode(@PathVariable String eventCode){
        return eventRegistrationRepository.findByCodeEquals(eventCode)
                .orElseThrow(() -> new RuntimeException("No eventcode found!"));
    }


    @GetMapping("/generate/{eventId}/{userId}")
    public EventRegistration generate(@PathVariable Long eventId, @PathVariable Long userId) {
        EventRegistration result = null;
        log.debug(String.format("eventid %d and user id %d", eventId, userId));
        Optional<Event> e = eventRepository.findById(eventId);
        Optional<User> u = userRepository.findById(userId);
        if (e.isPresent() && u.isPresent()) {
            result = eventRegistrationService.generateCodeForUser(e.get(), u.get());
            eventRegistrationRepository.save(result);

        }
        return result;
    }
}
