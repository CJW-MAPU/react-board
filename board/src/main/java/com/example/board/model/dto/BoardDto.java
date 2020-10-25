package com.example.board.model.dto;

import com.example.board.model.entity.Board;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardDto {

    private Integer id;
    private String title;
    private String content;
    private String writer; /* NOTE : 게시글 주인 < UserDto - username >*/

    List<CommentDto> comments; /* NOTE : 게시글에 달린 댓글 목록 */

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }
}
