package com.example.board.controller;

import com.example.board.model.dto.BoardDto;
import com.example.board.model.network.ExpansionFiled;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping
    public ExpansionFiled<List<BoardDto>> getBoards() {
        return boardService.getBoardList();
    }

    @GetMapping("/{id}")
    public ExpansionFiled<BoardDto> getBoard(@PathVariable("id") Integer id) {
        return boardService.getBoard(id);
    }

    @PostMapping
    public ExpansionFiled<BoardDto> create(@RequestBody ExpansionFiled<BoardDto> request) {
        return boardService.create(request.getData().toEntity(), request.getData().getWriter());
    }

    @PutMapping
    public ExpansionFiled<BoardDto> update(@RequestBody ExpansionFiled<BoardDto> request) {
        return boardService.update(request.getData().toEntity(), request.getData().getId());
    }

    @DeleteMapping("/{id}")
    public ExpansionFiled<BoardDto> delete(@PathVariable("id") Integer id) {
        return boardService.delete(id);
    }
}
