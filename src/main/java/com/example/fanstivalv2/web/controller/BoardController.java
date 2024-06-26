package com.example.fanstivalv2.web.controller;

import com.example.fanstivalv2.apiPayload.ApiResponse;
import com.example.fanstivalv2.apiPayload.code.status.SuccessStatus;
import com.example.fanstivalv2.converter.BoardConverter;
import com.example.fanstivalv2.domain.Board;
import com.example.fanstivalv2.service.board.BoardCommandService;
import com.example.fanstivalv2.service.board.BoardQueryService;
import com.example.fanstivalv2.web.dto.board.BoardRequestDto;
import com.example.fanstivalv2.web.dto.board.BoardResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardCommandService boardCommandService;
    private final BoardQueryService boardQueryService;

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

    @GetMapping("/{boardId}")
    public ApiResponse<BoardResponseDto.GetBoardResultDTO> getBoard(HttpServletRequest httpServletRequest, @PathVariable(name = "boardId") Long id){
        Board board = boardQueryService.getBoard(httpServletRequest, id);
        return ApiResponse.of(SuccessStatus.BOARD_FOUND, BoardConverter.toGetBoardResultDTO(board));
    }

    @DeleteMapping("/{boardId}")
    public ApiResponse<BoardResponseDto.DeleteBoardResultDTO> deleteBoard(HttpServletRequest httpServletRequest, @PathVariable(name = "boardId") Long id){
        return ApiResponse.of(SuccessStatus.BOARD_DELETED, boardCommandService.deleteBoard(httpServletRequest, id));
    }


    @GetMapping("/all")
    public ApiResponse<BoardResponseDto.GetBoardListResultDTO> getBoardList(HttpServletRequest httpServletRequest){
        List<Board> boardList = boardQueryService.getBoardList(httpServletRequest);
        return ApiResponse.of(SuccessStatus.BOARD_FOUND, BoardConverter.toGetBoardListResultDTO(boardList));
    }
}
