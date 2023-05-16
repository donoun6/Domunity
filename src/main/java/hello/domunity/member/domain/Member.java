package hello.domunity.member.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    private Long id;
    private String memberId;
    private String memberPassword;
    private String memberName;
}
