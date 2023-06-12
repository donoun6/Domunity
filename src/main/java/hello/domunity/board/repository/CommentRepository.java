package hello.domunity.board.repository;

import hello.domunity.board.domain.Board;
import hello.domunity.board.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Long countAllByBid(Board bid);
}
