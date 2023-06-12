package hello.domunity.board.domain;

import hello.domunity.member.domain.Member;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int bid;

    //fetch = FetchType.EAGER = 무조건 가져오겠다
    @ManyToOne(fetch = FetchType.EAGER) // Many = Board, Member = One 한명의 멤버는 여러개의 게시글을 쓸수 있다는 뜻
    @JoinColumn(name="mid")
    private Member mid; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    //하나의 게시글은 여러개의 댓글을 가질 수 있다. fetch = FetchType.LAZY = 필요할때 가져오겠다
    @OneToMany(mappedBy = "bid", fetch = FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다 (난 PK가 아니다) DB에 컬럼을 만들지 않는다. Comment의 bid가 PK가 된다.
    private List<Comment> cid; //1건이 아니기 때문에 collection이 되어야 한다.

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


