package com.pieterjd.familiefeest.repository;

import com.pieterjd.familiefeest.domain.EventItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventItemRepository extends JpaRepository<EventItem, Long> {
}
