package com.pieterjd.familiefeest.repository;

import com.pieterjd.familiefeest.domain.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    Event getFirstByDateAfterOrderByDate(Date cutoff);
}
