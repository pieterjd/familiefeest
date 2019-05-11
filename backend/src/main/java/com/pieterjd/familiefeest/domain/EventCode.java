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
public class EventCode {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String code;
    @OneToOne
    private User user;
    @OneToOne
    private Event event;
}
