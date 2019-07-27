package com.pieterjd.familiefeest.controller;

import com.pieterjd.familiefeest.domain.Event;
import com.pieterjd.familiefeest.domain.Statistics;
import com.pieterjd.familiefeest.repository.EventRepository;
import com.pieterjd.familiefeest.service.StatisticsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/eventstatistics")
@Log4j2
public class StatisticsController {

    private EventRepository eventRepository;
    private StatisticsService statisticsService;

    @Autowired
    public StatisticsController(EventRepository eventRepository, StatisticsService statisticsService) {
        this.eventRepository = eventRepository;
        this.statisticsService = statisticsService;
    }

    @GetMapping("/{eventId}")
    public Statistics getStatistics(@PathVariable  Long eventId){
        Event e = eventRepository.findById(eventId).orElseThrow(()->new RuntimeException("eventId not found"));
        return statisticsService.getStatistics(e);
    }
}
