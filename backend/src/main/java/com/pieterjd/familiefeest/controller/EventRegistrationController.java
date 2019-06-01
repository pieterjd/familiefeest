package com.pieterjd.familiefeest.controller;

import com.pieterjd.familiefeest.domain.*;
import com.pieterjd.familiefeest.repository.*;
import com.pieterjd.familiefeest.service.EventRegistrationService;
import com.pieterjd.familiefeest.service.MailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventregistration")
@Log4j2
public class EventRegistrationController {
    private final EventRegistrationService eventRegistrationService;
    private final EventRegistrationRepository eventRegistrationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final MailService mailService;

    @Value("${secret.token}")
    private String expectedSecretToken;

    @Autowired
    public EventRegistrationController(EventRegistrationService eventRegistrationService, EventRegistrationRepository eventRegistrationRepository, EventRepository eventRepository, UserRepository userRepository, MailService mailService) {
        this.eventRegistrationService = eventRegistrationService;
        this.eventRegistrationRepository = eventRegistrationRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;

        this.mailService = mailService;
    }

    @GetMapping("/{eventCode}")
    public EventRegistration getEventCode(@PathVariable String eventCode){
        return eventRegistrationRepository.findByCodeEquals(eventCode)
                .orElseThrow(() -> new RuntimeException("No eventcode found!"));
    }

    @PostMapping("/upload/{eventId}")
    public List<EventRegistration> upload(
            @PathVariable Long eventId,
            @RequestBody List<User> users,
            @RequestHeader(name = "secret-token") String secretToken){
        log.info("received postman token: "+secretToken);
        if(!expectedSecretToken.equals(secretToken)){
            throw new RuntimeException("Access denied");
        }
        Event e = eventRepository.findById(eventId).orElseThrow(()->new RuntimeException("invalid eventid"));
        List<EventRegistration> result = new ArrayList<>();
        for(User u: users){
            userRepository.save(u);
            EventRegistration er = EventRegistration.builder()
                    .event(e)
                    .user(u)
                    .code(eventRegistrationService.generateCode())
                    .payments(new ArrayList<>())
                    .purchasedItems(new ArrayList<>())
                    .build();
            eventRegistrationRepository.save(er);
            result.add(er);
            //mailService.sendInvitationMail(er);

        }
        return result;
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

    @GetMapping("/event/{eventId}")
    public List<EventRegistration>getByEvent(@PathVariable Long eventId){
        Event e = eventRepository.findById(eventId).orElseThrow(()->new RuntimeException("invalid event id"));
        return eventRegistrationRepository.findByEventEquals(e);
    }
}
