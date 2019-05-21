package com.pieterjd.familiefeest.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

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
    @JsonProperty
    private String title;
    @Column
    @JsonProperty
    private String description;
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private EventItemType type;
    @OneToMany(mappedBy = "eventItem")
    @JsonIgnore
    private List<Purchase> purchases;
    @Column
    @JsonProperty
    private Double price;


}
