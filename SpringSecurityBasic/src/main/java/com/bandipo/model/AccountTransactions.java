package com.bandipo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="account_transactions")
@NoArgsConstructor
@Data
public class AccountTransactions {

    @Id
    @Column(name = "transaction_id")
    private Long transactionId;

//    @Column(name="account_number")
//    private long accountNumber;

//    @Column(name = "customer_id")
//    private int customerId;

    @Column(name="transaction_dt")
    private Date transactionDt;

    @Column(name = "transaction_summary")
    private String transactionSummary;

    @Column(name="transaction_type")
    private String transactionType;

    @Column(name = "transaction_amt")
    private int transactionAmt;

    @Column(name = "closing_balance")
    private int closingBalance;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_dt")
    private java.util.Date createDt = new java.util.Date();


    @ManyToOne
    @JoinColumn(name="account_number")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
