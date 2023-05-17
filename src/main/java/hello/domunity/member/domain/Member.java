package hello.domunity.member.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    @NotEmpty
    @Column(length = 20, nullable = false)
    private String memberId;

    @NotEmpty
    @Column(length = 100, nullable = false)
    private String memberPw;

    @NotEmpty
    @Column(length = 20, nullable = false)
    private String memberName;

    @CreationTimestamp
    private Timestamp regDate;
}
