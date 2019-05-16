package com.pieterjd.familiefeest.controller;

import com.pieterjd.familiefeest.domain.*;
import com.pieterjd.familiefeest.dto.PurchaseDto;
import com.pieterjd.familiefeest.repository.*;
import com.pieterjd.familiefeest.service.EventRegistrationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/eventregistration")
@Log4j2
public class EventRegistrationController {
    private final EventRegistrationService eventRegistrationService;
    private final EventRegistrationRepository eventRegistrationRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventItemRepository eventItemRepository;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public EventRegistrationController(EventRegistrationService eventRegistrationService, EventRegistrationRepository eventRegistrationRepository, EventRepository eventRepository, UserRepository userRepository, EventItemRepository eventItemRepository, PurchaseRepository purchaseRepository) {
        this.eventRegistrationService = eventRegistrationService;
        this.eventRegistrationRepository = eventRegistrationRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventItemRepository = eventItemRepository;
        this.purchaseRepository = purchaseRepository;
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

    @PostMapping("/purchase")
    public void addEventItem(@RequestBody PurchaseDto dto){
        EventRegistration er = eventRegistrationRepository.findByCodeEquals(dto.getEventCode()).orElseThrow(() -> new RuntimeException("Invalid eventcode"));
        EventItem ei = eventItemRepository.findById(dto.getEventItemId()).orElseThrow(() -> new RuntimeException("invalid eventItemId"));
        Purchase p = Purchase.builder()
                .eventItem(ei)
                .beneficiary(dto.getBeneficiary())
                .build();
        purchaseRepository.save(p);
        er.getPurchasedItems().add(p);
        eventRegistrationRepository.save(er);

    }
}
