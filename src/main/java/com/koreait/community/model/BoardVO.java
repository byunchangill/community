package com.koreait.community.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO extends BoardEntity{
    private String writerNm;
    private String profileImg;
    private String categoryNm;
}
