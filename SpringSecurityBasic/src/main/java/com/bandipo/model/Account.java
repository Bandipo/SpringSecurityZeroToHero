package com.bandipo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "accounts")
@NoArgsConstructor
@Data
public class Account {

    @Id
    @Column(name="account_number")
    private Long accountNumber;

    @Column(name="account_type")
    private String accountType;
    @Column(name = "branch_address")
    private String branchAddress;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_dt")
    private Date createDt = new Date();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
