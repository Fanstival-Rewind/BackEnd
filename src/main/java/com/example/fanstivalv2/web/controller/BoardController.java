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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
