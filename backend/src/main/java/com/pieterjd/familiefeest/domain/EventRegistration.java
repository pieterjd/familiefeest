package com.pieterjd.familiefeest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class EventRegistration {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String code;
    @OneToOne
    private User user;
    @OneToOne
    private Event event;
    @OneToMany
    private List<Purchase> purchasedItems;

    @OneToMany
    private List<Payment> payments;

    @JsonProperty
    /**
     * Returns the total value of all purchased items
     */
    public Double purchaseTotal(){
        return purchasedItems.stream()
                .collect(
                        Collectors.summingDouble(
                                pi -> pi.getEventItem().getPrice()
                        )
                );
    }

    @JsonProperty
    /**
     * returns the total amount already paid
     */
    public Double amountAlreadyPaid(){
        return payments.stream()
                .collect(
                        Collectors.summingDouble( p -> p.getAmount())
                );
    }

    @JsonProperty
    public Double openAmount(){
        return purchaseTotal() - amountAlreadyPaid();
    }



}
