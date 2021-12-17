package com.bandipo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Data
@NoArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    private String username; // this is only needed if you want to use Spring Security JdbcAuthentication

    private String authority;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
