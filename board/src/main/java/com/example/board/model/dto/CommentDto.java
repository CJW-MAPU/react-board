package com.example.board.model.dto;

import com.example.board.model.entity.Comment;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentDto {

    private Integer id;
    private String content;
    private String writer; /* NOTE : 댓글 주인 */
    private Integer board_id; /* NOTE : 게시글 번호 */


    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .build();
    }
}
