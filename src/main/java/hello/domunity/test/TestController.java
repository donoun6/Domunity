package hello.domunity.test;

import hello.domunity.board.domain.Board;
import hello.domunity.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final BoardRepository boardRepository;

    /**
     * @JsonIgnoreProperties({"bid"}) //board에서 comment를 호출하고 comment에서 또 board를 호출한다.
     * 무한참조를 보기 위한 테스트
     */
    @GetMapping("/test/{id}")
    public Board testBoard(@PathVariable int id) {
        return boardRepository.findById(id).get();
    }
}
