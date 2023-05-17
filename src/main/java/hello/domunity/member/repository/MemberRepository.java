package hello.domunity.member.repository;

import hello.domunity.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberIdAndMemberPw(String memberId, String memberPw);
}
