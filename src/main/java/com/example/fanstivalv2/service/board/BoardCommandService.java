package com.example.fanstivalv2.service.board;

import com.example.fanstivalv2.domain.Board;
import com.example.fanstivalv2.web.dto.board.BoardRequestDto;
import com.example.fanstivalv2.web.dto.board.BoardResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface BoardCommandService {

    Board createBoard(HttpServletRequest httpServletRequest, BoardRequestDto.CreateBoardDto request);
    Board updateBoard(HttpServletRequest httpServletRequest, Long id, BoardRequestDto.UpdateBoardDto request);

    BoardResponseDto.DeleteBoardResultDTO deleteBoard(HttpServletRequest httpServletRequest, Long id);
}
