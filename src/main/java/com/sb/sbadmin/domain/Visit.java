package com.sb.sbadmin.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="visit")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name="LOGIN_DATE")
    private String logindate;
    @Column(name="OS_TYPE")
    private String ostype;
    @Column(name="ACCESS_NAME")
    private String accessname;
    @Column(name="ACCESS_PATH")
    private String accesspath;
    @Column(name="IPADDRESS")
    private String ipaddress;
    @Column(name="SESSION_LASTACCESS")
    private String sessionlastaccess;
    @Column(name="SESSION_ID")
    private String sessionId;
    @Column(name="BROWSER")
    private String browser;
    @Column(name="REFERER")
    private String referer;

    @Transient
    private int countToday;
}
