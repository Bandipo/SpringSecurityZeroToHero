package com.bandipo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "contact_messages")
@NoArgsConstructor
@Data
public class Contact {

    @Id
    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_email")
    private String contactEmail;

    private String subject;

    private String message;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_dt")
    private java.util.Date createDt = new Date();
}
