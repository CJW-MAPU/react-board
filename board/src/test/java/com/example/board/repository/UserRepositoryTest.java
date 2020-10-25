package com.example.board.repository;

import com.example.board.BoardApplicationTests;
import com.example.board.model.entity.Board;
import com.example.board.model.entity.Comment;
import com.example.board.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

class UserRepositoryTest extends BoardApplicationTests {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CommentRepository commentRepository;

    @Test
    void create() {
//        User user = User.builder()
//                .username("admin2")
//                .password("admin2")
//                .email("qwe2@qwe.com")
//                .build();

        User user = userRepository.findByUsername("admin");

//        userRepository.save(user);

        Board board = Board.builder()
                .title("qweqwerqwerasdasd")
                .content("qweqweqwerqwerqwzxczxc")
                .build();

        board.setUser(user);

        boardRepository.save(board);

//        Comment comment = Comment.builder()
//                .content("qweqwe123qweq")
//                .build();
//
//        comment.setBoard(board);
//        comment.setUser(user);
//
//        commentRepository.save(comment);
    }

    @Test
    @Transactional
    void read() {
        Optional<User> optionalUser = userRepository.findById(7);

        List<Board> boards = boardRepository.findAll();
        List<Comment> comments = commentRepository.findAll();

        for (Board item : boards) {
            System.out.println(item.toString());
        }
    }

    @Test
    @Transactional
    void update() {

    }

    @Test
    void delete() {

    }
}