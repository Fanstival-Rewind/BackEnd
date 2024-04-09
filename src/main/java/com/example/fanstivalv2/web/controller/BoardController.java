package com.example.fanstivalv2.web.controller;

import com.example.fanstivalv2.apiPayload.ApiResponse;
import com.example.fanstivalv2.apiPayload.code.status.SuccessStatus;
import com.example.fanstivalv2.converter.BoardConverter;
import com.example.fanstivalv2.domain.Board;
import com.example.fanstivalv2.service.board.BoardCommandService;
import com.example.fanstivalv2.web.dto.board.BoardRequestDto;
import com.example.fanstivalv2.web.dto.board.BoardResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardCommandService boardCommandService;

    @PostMapping
    public ApiResponse<BoardResponseDto.CreateBoardResultDTO> createBoard(HttpServletRequest httpServletRequest, @RequestBody BoardRequestDto.CreateBoardDto request){

        Board board = boardCommandService.createBoard(httpServletRequest, request);

        return ApiResponse.of(SuccessStatus.BOARD_CREATED, BoardConverter.toCreateResultDTO(board));
    }

    @PatchMapping("/{boardId}")
    public ApiResponse<BoardResponseDto.UpdateBoardResultDTO> updateBoard(HttpServletRequest httpServletRequest, @PathVariable(name = "boardId") Long id, @RequestBody BoardRequestDto.UpdateBoardDto request){

        Board board = boardCommandService.updateBoard(httpServletRequest, id, request);
        return ApiResponse.of(SuccessStatus.BOARD_UPDATED, BoardConverter.toUpdateResultDTO(board));
    }
}
