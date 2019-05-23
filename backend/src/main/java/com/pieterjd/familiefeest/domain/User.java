package com.pieterjd.familiefeest.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
//user cannot be used as table name in postgres
@Table(name = "user_tbl")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String firstName;
    private String lastName;
    @Column(name = "name")
    private String fullName;
    @Column
    private String email;
}
