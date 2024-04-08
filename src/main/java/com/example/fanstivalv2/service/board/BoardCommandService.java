package com.example.fanstivalv2.service.board;

import com.example.fanstivalv2.domain.Board;
import com.example.fanstivalv2.web.dto.board.BoardRequestDto;
import jakarta.servlet.http.HttpServletRequest;

public interface BoardCommandService {

    Board createBoard(HttpServletRequest httpServletRequest, BoardRequestDto.CreateBoardDto request);
}
