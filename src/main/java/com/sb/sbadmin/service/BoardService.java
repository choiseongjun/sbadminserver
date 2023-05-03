package com.sb.sbadmin.service;

import com.sb.sbadmin.domain.Board;
import com.sb.sbadmin.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    public void boardPost(Board board) {
        boardRepository.save(board);
    }

    public List<Board> boardGet(String gubun) {
        return boardRepository.findAllByGubunOrderByIdDesc(gubun);
    }

    public Page<Board> boardAllGet(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Board boardGetDetail(long id) {
        return boardRepository.findById(id).get();
    }

    public void boardPut(Board board) {
        Board rtnBoard = boardRepository.findById(board.getId()).get();
        rtnBoard.setTitle(board.getTitle());
        rtnBoard.setContent(board.getContent());
        rtnBoard.setGubun(board.getGubun());
    }

    public List<Board> boardGetTop(String gubun) {
        return boardRepository.findAllByGubun(gubun);
    }

    public void boardDelete(long id) {
        boardRepository.deleteById(id);
    }
}
