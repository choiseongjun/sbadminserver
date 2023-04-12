package com.sb.sbadmin.controller;

import com.sb.sbadmin.domain.Board;
import com.sb.sbadmin.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/board")
public class BoardController {
    @Autowired
    private BoardService boardService;
    @PostMapping
    public ResponseEntity<?> boardPost(@RequestBody Board board) {
        boardService.boardPost(board);
        return ResponseEntity.ok("success");
    }
    @PutMapping
    public ResponseEntity<?> boardPut(@RequestBody Board board) {
        boardService.boardPut(board);
        return ResponseEntity.ok("success");
    }
    @GetMapping("/{gubun}")
    public ResponseEntity<?> boardGet(@PathVariable String gubun){
        return ResponseEntity.ok(boardService.boardGet(gubun));
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> boardGetDetail(@PathVariable long id){
        return ResponseEntity.ok(boardService.boardGetDetail(id));
    }
    @GetMapping
    public ResponseEntity<?> boardAllGet(@RequestParam int pageNum,
                                         @RequestParam int page,
                                         Pageable pageable){
        pageable = PageRequest.of(page, pageNum, Sort.Direction.DESC, "id");// 내림차순으로 정렬한다

        return ResponseEntity.ok(boardService.boardAllGet(pageable));
    }


}
