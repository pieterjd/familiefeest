package com.pieterjd.familiefeest.domain;

import javax.persistence.*;

@Entity
public enum EventItemType {
    FOOD("food"),
    DRINKS("drinks"),
    ENTRANCE_FEE("Entrance Fee");

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String description;

    EventItemType(String description){
        this.description = description;
    }
}
