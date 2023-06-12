package hello.domunity.board.repository;

import hello.domunity.board.domain.Board;
import hello.domunity.board.domain.Comment;
import hello.domunity.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    /**
     * 메인화면 게시물 카데고리별 6개 출력
     * @param category
     * @return
     */
    List<Board> findTop6ByBoardCategoryOrderByBidDesc(String category);

    /**
     * 카테고리 게시물 출력
     * @param categoty
     * @return
     */
    Page<Board> findByBoardCategory(String categoty, Pageable pageable);

}
