package com.pieterjd.familiefeest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Date date;
    @Column
    private Double amount;
    @ManyToOne
    private EventRegistration eventRegistration;
}
