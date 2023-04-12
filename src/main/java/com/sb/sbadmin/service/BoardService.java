package com.sb.sbadmin.service;

import com.sb.sbadmin.domain.Board;
import com.sb.sbadmin.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    public void boardPost(Board board) {
        boardRepository.save(board);
    }

    public List<Board> boardGet(String gubun) {
        return boardRepository.findAllByGubun(gubun);
    }

    public Page<Board> boardAllGet(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    public Board boardGetDetail(long id) {
        return boardRepository.findById(id).get();
    }
}
