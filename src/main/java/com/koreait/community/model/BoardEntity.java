package com.koreait.community.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardEntity {
    private int iboard;
    private int icategory;
    private String title;
    private String ctnt;
    private int iuser;
    private int isdel;
    private int hits;
    private String rdt;
    private String mdt;
    private String lastip;
}
