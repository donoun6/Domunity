package hello.domunity.board.domain;

import hello.domunity.member.domain.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int bid;

    @ManyToOne // Many = Board, Member = One 한명의 멤버는 여러개의 게시글을 쓸수 있다는 뜻
    @JoinColumn(name="mid")
    private Member mid; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @Column(nullable = false, length = 100)
    private String boardTitle;

    @Lob //대용량 데이터
    private String boardContent; //섬머노트 라이브러리 html 태그가 섞여 디자인되므로 대용량

    @Lob
    private String boardImage;

    @Column(nullable = false, length = 10)
    private String boardCategory;

    private int boardCount; //조회수

    @CreationTimestamp
    private Timestamp regDate;
}
