package hello.domunity.board.service;

import hello.domunity.board.domain.Board;
import hello.domunity.board.domain.Comment;
import hello.domunity.board.repository.BoardRepository;
import hello.domunity.board.repository.CommentRepository;
import hello.domunity.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Override
    public void save(Board board, Member member) {
        board.setBoardCount(0);
        board.setMid(member);
        boardRepository.save(board);
    }

    @Override
    public List<Board> HomeViewBoardByCategory(String category) {
        List<Board> boardList = boardRepository.findTop6ByBoardCategoryOrderByBidDesc(category);
        for(int i = 0; i < boardList.size(); i++) {
            Board board = boardList.get(i);
            int count = commentRepository.countAllByBid(board).intValue();
            board.setBoardCount(count);
        }
        return boardList;
    }

    @Override
    public Page<Board> viewBoardByCategory(String category , Pageable pageable) {
        Page<Board> boardList = boardRepository.findByBoardCategory(category, pageable);
        for(int i = 0; i < boardList.toList().size(); i++ ){
            Board board = boardList.toList().get(i);
            int count = commentRepository.countAllByBid(board).intValue();
            board.setBoardCount(count);
        }
        return boardList;
    }

    @Override
    public Board viewBoard(int bid) {
        return boardRepository.findById(bid)
                .orElseThrow(() -> {
            return new IllegalArgumentException("글 상세보기 실패 : 찾을 수 없습니다.");
        });
    }

    @Override
    public void deleteBoard(int bid) {
        boardRepository.deleteById(bid);
    }

    @Override
    public void saveComment(Member member, int bid, Comment comment) {
        Board board = boardRepository.findById(bid).orElseThrow(() -> {
            return new IllegalArgumentException("댓글 작성 실패 : 게시글 id를 찾을 수 없습니다.");
        });//영속화 (엔티티를 영구 저장하는 환경)

        comment.setMid(member);
        comment.setBid(board);

        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(int cid) {
        commentRepository.deleteById(cid);
    }

}
