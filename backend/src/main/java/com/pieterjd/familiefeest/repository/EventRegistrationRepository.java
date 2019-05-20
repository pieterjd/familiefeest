package com.pieterjd.familiefeest.repository;

import com.pieterjd.familiefeest.domain.Event;
import com.pieterjd.familiefeest.domain.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration,Long> {
    Optional<EventRegistration> findByCodeEquals(String code);
    List<EventRegistration> findByEventEquals(Event event);
}
