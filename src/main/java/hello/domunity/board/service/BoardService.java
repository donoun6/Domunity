package hello.domunity.board.service;

import hello.domunity.board.domain.Board;
import hello.domunity.member.domain.Member;

import java.util.List;

public interface BoardService {

    /**
     * 게시물 저장
     * @param member
     */
    void save(Board board, Member member);

    List<Board> viewBoardByCategory(String category);

}
