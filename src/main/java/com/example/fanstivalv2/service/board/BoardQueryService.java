package com.example.fanstivalv2.service.board;

import com.example.fanstivalv2.domain.Board;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface BoardQueryService {

    Board getBoard(HttpServletRequest httpServletRequest, Long id);
    List<Board> getBoardList(HttpServletRequest httpServletRequest);
}
