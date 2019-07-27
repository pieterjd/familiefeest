package com.pieterjd.familiefeest.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {
    private int numberOfAttendees;
    private long daysToEvent;

}
