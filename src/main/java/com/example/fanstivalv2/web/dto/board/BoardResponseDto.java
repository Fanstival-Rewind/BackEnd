package com.example.fanstivalv2.web.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateBoardResultDTO{
        Long boardId;
        String title;
        String contents;
        LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetBoardResultDTO{
        Long boardId;
        Long userId;
        String nickName;//어떤 유저가 작성했는지 닉네임과 아이디가 필요
        String title;
        String contents;
        LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteBoardResultDTO{
        Long boardId;
        LocalDateTime deletedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetBoardListResultDTO{
        List<GetBoardResultDTO> boardList;
    }
}
