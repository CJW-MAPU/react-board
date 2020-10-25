package com.example.board.service;

import com.example.board.model.dto.BoardDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CommentRepository commentRepository;

    public ExpansionFiled<List<BoardDto>> getBoardList() {
        List<Board> boards = boardRepository.findAll();

        if (boards == null) {
            return ExpansionFiled.error(302, "There are no posts on the bulletin board.");
        } else {
            return ExpansionFiled.ok(toBoardDtoList(boards), 301);
        }
    }

    public ExpansionFiled<BoardDto> getBoard(Integer id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        List<Comment> comments = commentRepository.findByBoard_id(id);

        Board board;

        if (optionalBoard.isPresent()) {
            board = optionalBoard.get();

            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getUser().getUsername())
                    .comments(toCommentDtoList(comments))
                    .build();

            return ExpansionFiled.ok(boardDto, 501);
        } else {
            return ExpansionFiled.error(502, "The post could not be loaded.");
        }
    }

    public ExpansionFiled<BoardDto> create(Board board, String writer) {
        User user = userRepository.findByUsername(writer);

        board.setUser(user);

        Board boardCheck = boardRepository.save(board);

        BoardDto ret = BoardDto.builder()
                .id(boardCheck.getId())
                .title(boardCheck.getTitle())
                .content(boardCheck.getContent())
                .writer(boardCheck.getUser().getUsername())
                .build();

        return ExpansionFiled.ok(ret, 401);
    }

    private static List<CommentDto> toCommentDtoList(List<Comment> comments) {
        List<CommentDto> ret = new ArrayList<>();

        for (Comment item : comments) {
            CommentDto temp = CommentDto.builder()
                    .id(item.getId())
                    .content(item.getContent())
                    .writer(item.getUser().getUsername())
                    .build();

            ret.add(temp);
        }

        return ret;
    }


    private static List<BoardDto> toBoardDtoList(List<Board> boards) {
        List<BoardDto> ret = new ArrayList<>();

        for (Board item : boards) {
            BoardDto temp = BoardDto.builder()
                    .id(item.getId())
                    .title(item.getTitle())
                    .writer(item.getUser().getUsername())
                    .build();

            ret.add(temp);
        }

        return ret;
    }
}
