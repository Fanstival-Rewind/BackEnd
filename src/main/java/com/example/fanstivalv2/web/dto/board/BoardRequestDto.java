package com.example.fanstivalv2.web.dto.board;

import lombok.Getter;

import java.time.LocalDateTime;

public class BoardRequestDto {

    @Getter
    public static class CreateBoardDto{
        private String title;
        private String contents;
    }


}
