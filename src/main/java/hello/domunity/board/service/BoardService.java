package hello.domunity.board.service;

import hello.domunity.board.domain.Board;
import hello.domunity.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {

    /**
     * 게시물 저장
     * @param member
     */
    void save(Board board, Member member);

    /**
     * 메인화면 게시글 출력
     * @param category
     * @return
     */
    List<Board> HomeViewBoardByCategory(String category);

    /**
     * 카데고리 화면 게시글 출력
     * @param category
     * @return
     */
    Page<Board> viewBoardByCategory(String category, Pageable pageable);

    /**
     * 게시물 보기
     * @param bid
     * @return
     */
    public Board viewBoard(int bid);

    /**
     * 게시글 삭제
     * @param bid
     */
    public void deleteBoard(int bid);

}
