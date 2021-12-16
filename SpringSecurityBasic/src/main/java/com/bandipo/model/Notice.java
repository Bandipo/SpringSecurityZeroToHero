package com.bandipo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "notice_details")
@Data
@NoArgsConstructor
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notice_id")
    private Long noticeId;

    @Column(name = "notice_summary")
    private String noticeSummary;

    @Column(name = "notice_details")
    private String noticeDetails;

    @Temporal(TemporalType.DATE)
    @Column(name = "notic_beg_dt")
    private Date noticBegDt = new Date() ;

    @Temporal(TemporalType.DATE)
    @Column(name = "notic_end_dt")
    private Date noticEndDt = new Date();

    @Temporal(TemporalType.DATE)
    @Column(name = "create_dt")
    private Date createDt = new Date();


    @Temporal(TemporalType.DATE)
    @Column(name = "update_dt")
    private Date updateDt = new Date();
}
