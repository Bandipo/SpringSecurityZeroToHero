package com.bandipo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
@SequenceGenerator(name =Customer.SEQUENCE_NAME, sequenceName = Customer.SEQUENCE_NAME,
                  initialValue = 1, allocationSize = 1)
@Data
@NoArgsConstructor
public class Customer {
    public final static String SEQUENCE_NAME = "SEQUENCE_NAME";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @Setter(AccessLevel.NONE)
    private Long id;


    private String name;

    @Column(unique = true, nullable = false, length = 120)
    private String email;

    @JsonIgnore
    @Column(nullable = false, length = 250)
    private String password;

    @Column(nullable = false, length = 45)
    private String role;




    public Customer(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Customer(Long id) {
        this.id = id;
    }
}
