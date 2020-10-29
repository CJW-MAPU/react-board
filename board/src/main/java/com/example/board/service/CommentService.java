package com.example.board.service;

import com.example.board.model.dto.CommentDto;
import com.example.board.model.entity.Board;
import com.example.board.model.entity.Comment;
import com.example.board.model.entity.User;
import com.example.board.model.network.ExpansionFiled;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CommentRepository commentRepository;

    public ExpansionFiled<CommentDto> create(Comment comment, String writer, Integer board_id) {
        User user = userRepository.findByUsername(writer);
        Optional<Board> optionalBoard = boardRepository.findById(board_id);

        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();

            comment.setUser(user);
            comment.setBoard(board);

            Comment commentCheck = commentRepository.save(comment);

            CommentDto ret = CommentDto.builder()
                    .id(commentCheck.getId())
                    .content(commentCheck.getContent())
                    .writer(commentCheck.getUser().getUsername())
                    .board_id(commentCheck.getBoard().getId())
                    .build();

            return ExpansionFiled.ok(ret, 701);
        } else {
            return ExpansionFiled.error(702, "Failed to write a comment.");
        }
    }
}
