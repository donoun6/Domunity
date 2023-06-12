package hello.domunity.board.domain;

import hello.domunity.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략
    private int cid;

    @ManyToOne //하나의 유저는 여러개의 댓글을 달수있다.
    @JoinColumn(name = "mid")
    private Member mid;

    @ManyToOne //여러개의 댓글은 하나의 게시글에 존재할 수 있다. M:1 관계
    @JoinColumn(name = "bid")
    private Board bid;

    @Column(nullable = false, length = 500)
    private String commentDef;

    @CreationTimestamp
    private Timestamp regDate;

}
