package com.pieterjd.familiefeest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class EventItem {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String title;
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name="eventitemtype_id")
    private EventItemType type;

}
