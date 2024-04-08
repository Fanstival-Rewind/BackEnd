package com.example.fanstivalv2.web.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class BoardResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateBoardResultDTO{
        Long boardId;
        String title;
        String contents;
        LocalDateTime createdAt;
    }
}
