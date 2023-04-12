package com.sb.sbadmin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="stocktop")
public class StockTop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstTxt;

    private String secondTxt;

    private String thirdTxt;

    private String gubun;

}
