package com.bandipo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="loans")
@Data
@NoArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_number")
    private Long loanNumber;

    @JoinColumn(name = "customer_id")
    @ManyToOne
   private Customer customer;


    @Temporal(TemporalType.DATE)
    @Column(name="start_dt")
    private Date startDt = new Date();

    @Column(name = "loan_type")
    private String loanType;

    @Column(name = "total_loan")
    private int totalLoan;

    @Column(name = "amount_paid")
    private int amountPaid;

    @Column(name = "outstanding_amount")
    private int outstandingAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_dt")
    private java.util.Date createDt = new java.util.Date();
}
