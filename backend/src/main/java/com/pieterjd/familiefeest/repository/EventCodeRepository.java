package com.pieterjd.familiefeest.repository;

import com.pieterjd.familiefeest.domain.EventCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventCodeRepository extends JpaRepository<EventCode,Long> {
    Optional<EventCode> findByCodeEquals(String code);
}
