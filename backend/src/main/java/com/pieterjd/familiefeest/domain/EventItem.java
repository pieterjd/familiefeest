package com.pieterjd.familiefeest.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;


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
    @JsonIgnore
    private EventItemType type;
    @OneToMany(mappedBy = "eventItem")
    @JsonProperty
    @JsonManagedReference
    private List<Purchase> purchases;

}
