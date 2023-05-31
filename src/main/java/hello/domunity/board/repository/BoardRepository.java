package hello.domunity.board.repository;

import hello.domunity.board.domain.Board;
import hello.domunity.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
