package com.pieterjd.familiefeest.service;

import com.pieterjd.familiefeest.domain.Event;
import com.pieterjd.familiefeest.domain.EventRegistration;
import com.pieterjd.familiefeest.domain.Statistics;
import com.pieterjd.familiefeest.repository.EventRegistrationRepository;
import com.pieterjd.familiefeest.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class StatisticsService {
    private EventRepository eventRepository;
    private EventRegistrationRepository eventRegistrationRepository;

    @Autowired
    public StatisticsService(EventRepository eventRepository, EventRegistrationRepository eventRegistrationRepository) {
        this.eventRepository = eventRepository;
        this.eventRegistrationRepository = eventRegistrationRepository;
    }


    public Statistics getStatistics(Event e) {
        List<EventRegistration> ers = eventRegistrationRepository.findByEventEquals(e);
        int attendees = ers.stream()
                .mapToInt(er -> er.getPurchasedItems().size())
                .sum();
        //Convert epoch milliseconds to epoch days
        LocalDate eventDate = LocalDate.ofEpochDay(e.getDate().getTime() / (1000 * 60 * 60 * 24));
        LocalDate now = LocalDate.now();

        long days = ChronoUnit.DAYS.between(now, eventDate);
        return Statistics.builder()
                .numberOfAttendees(attendees)
                .daysToEvent(days)
                .eventDate(e.getDate())
                .build();
    }
}
