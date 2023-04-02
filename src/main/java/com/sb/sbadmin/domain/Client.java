package com.sb.sbadmin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name="PHONENUMBER")
    private String phoneNumber;
    @Column(name="name")
    private String name;
    @Column(name="APPLYTIME")
    private String applyTime;

}
