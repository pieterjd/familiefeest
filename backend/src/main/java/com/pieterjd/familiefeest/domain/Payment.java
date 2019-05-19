package com.pieterjd.familiefeest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private Date date;
    @Column
    private Double amount;
}
