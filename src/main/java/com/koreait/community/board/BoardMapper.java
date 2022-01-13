package com.koreait.community.board;

import com.koreait.community.model.BoardDTO;
import com.koreait.community.model.BoardEntity;
import com.koreait.community.model.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity entity);
    List<BoardVO> selBoardList(BoardDTO dto);
    BoardVO selBoardDetail(BoardDTO dto);
    int addHits(BoardDTO dto);
    int upDateBoard(BoardEntity entity);
}