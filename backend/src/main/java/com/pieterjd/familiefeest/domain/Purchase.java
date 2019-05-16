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
public class Purchase {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private EventItem eventItem;
    @Column
    private String beneficiary;
}
