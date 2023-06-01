package hello.domunity.board.service;

import hello.domunity.board.dao.BoardDao;
import hello.domunity.board.domain.Board;
import hello.domunity.board.repository.BoardRepository;
import hello.domunity.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
//    private final BoardDao boardDao;

    @Override
    public void save(Board board, Member member) {
        board.setBoardCount(0);
        board.setMid(member);
        boardRepository.save(board);
    }

    @Override
    public List<Board> viewBoardByCategory(String category) {
        return boardRepository.findTop6ByBoardCategoryOrderByBidDesc(category);
    }

}
