package com.example.fanstivalv2.converter;

import com.example.fanstivalv2.domain.Board;
import com.example.fanstivalv2.web.dto.board.BoardRequestDto;
import com.example.fanstivalv2.web.dto.board.BoardResponseDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BoardConverter {

    public static BoardResponseDto.CreateBoardResultDTO toCreateResultDTO(Board board){
        return BoardResponseDto.CreateBoardResultDTO.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .contents(board.getContent())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static BoardResponseDto.UpdateBoardResultDTO toUpdateResultDTO(Board board){
        return BoardResponseDto.UpdateBoardResultDTO.builder()
                .boardId(board.getId())
                .title(board.getTitle())
                .contents(board.getContent())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static BoardResponseDto.GetBoardResultDTO toGetBoardResultDTO(Board board){
        return BoardResponseDto.GetBoardResultDTO.builder()
                .boardId(board.getId())
                .userId(board.getUser().getId())
                .nickName(board.getUser().getNickname())
                .title(board.getTitle())
                .contents(board.getContent())
                .build();
    }

    public static BoardResponseDto.GetBoardListResultDTO toGetBoardListResultDTO(List<Board> boardList){
        List<BoardResponseDto.GetBoardResultDTO> boardResultDTOList = boardList.stream()
                .map(BoardConverter::toGetBoardResultDTO).collect(Collectors.toList());

        return BoardResponseDto.GetBoardListResultDTO.builder()
                .boardList(boardResultDTOList)
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
