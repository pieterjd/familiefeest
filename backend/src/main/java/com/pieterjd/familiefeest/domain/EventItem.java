package com.pieterjd.familiefeest.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
    @JsonValue
    private EventItemType type;
    @OneToMany(mappedBy = "eventItem")
    private Set<Purchase> purchases;

}
