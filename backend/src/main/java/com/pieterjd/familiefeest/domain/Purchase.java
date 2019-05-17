package com.pieterjd.familiefeest.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private EventItem eventItem;
    @Column
    private String beneficiary;
}
