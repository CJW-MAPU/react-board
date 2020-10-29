package com.example.board.model.network;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@SuppressWarnings("unchecked")
public class ExpansionFiled<T> {
    private LocalDateTime transaction_time;
    private Integer result_code;
    private String description;
    private T data;

    public static <T> ExpansionFiled<T> ok(Integer result_code, String description) {
        return (ExpansionFiled<T>) ExpansionFiled.builder()
                .transaction_time(LocalDateTime.now())
                .result_code(result_code)
                .description(description)
                .build();
    }

    public static <T> ExpansionFiled<T> ok(T data, Integer result_code) {
        return (ExpansionFiled<T>) ExpansionFiled.builder()
                .data(data)
                .transaction_time(LocalDateTime.now())
                .result_code(result_code)
                .build();
    }


    public static <T> ExpansionFiled<T> error(Integer result_code, String description) {
        return (ExpansionFiled<T>) ExpansionFiled.builder()
                .transaction_time(LocalDateTime.now())
                .result_code(result_code)
                .description(description)
                .build();
    }
}
