package com.example.board.controller;

import com.example.board.model.dto.CommentDto;
import com.example.board.model.entity.Comment;
import com.example.board.model.network.ExpansionFiled;
import com.example.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping
    public ExpansionFiled<CommentDto> create(@RequestBody ExpansionFiled<CommentDto> request) {
        return commentService.create(request.getData().toEntity(), request.getData().getWriter(), request.getData().getBoard_id());
    }
}
