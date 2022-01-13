package com.koreait.community;

import com.koreait.community.model.BoardCategoryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonMapper {
    List<BoardCategoryEntity> selMenuCategoryList();
}
