package com.pieterjd.familiefeest.controller;

import com.pieterjd.familiefeest.domain.EventItem;
import com.pieterjd.familiefeest.domain.EventRegistration;
import com.pieterjd.familiefeest.repository.EventItemRepository;
import com.pieterjd.familiefeest.repository.EventRegistrationRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/eventitem")
@Log4j2
public class EventItemController {
    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;
    @Autowired
    private EventItemRepository eventItemRepository;
    @GetMapping
    public List<EventItem> getAll(){
        return eventItemRepository.findAll();
    }

    @GetMapping("/{eventCode}")
    public List<EventItem> getItems(@PathVariable String eventCode) {
        EventRegistration er = eventRegistrationRepository.findByCodeEquals(eventCode).orElseThrow(() -> new RuntimeException("Invalid eventcode"));
        return er.getEvent().getEventItems();
    }
}
