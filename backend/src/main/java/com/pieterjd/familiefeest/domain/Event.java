package com.pieterjd.familiefeest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @OneToMany
    @JoinColumn(name="event_id")
    private Set<EventItem> eventItems;
}
