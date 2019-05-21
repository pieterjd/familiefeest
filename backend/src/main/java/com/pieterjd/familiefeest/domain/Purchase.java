package com.pieterjd.familiefeest.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@JsonIgnoreProperties(ignoreUnknown = true)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private EventItem eventItem;
    @Column
    private String beneficiary;
}
