package com.example.fanstivalv2.converter;

import com.example.fanstivalv2.domain.Board;
import com.example.fanstivalv2.web.dto.board.BoardRequestDto;
import com.example.fanstivalv2.web.dto.board.BoardResponseDto;

import java.time.LocalDateTime;

public class BoardConverter {

    public static BoardResponseDto.CreateBoardResultDTO toCreateResultDTO(Board board){
        return BoardResponseDto.CreateBoardResultDTO.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .contents(board.getContent())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Board toBoard(BoardRequestDto.CreateBoardDto request){
        return Board.builder()
                .title(request.getTitle())
                .content(request.getContents())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
