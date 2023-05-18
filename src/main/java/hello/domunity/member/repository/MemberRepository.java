package hello.domunity.member.repository;

import hello.domunity.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String memberId);

//    Member findByMemberIdAndMemberPw(String memberId, String memberPw);
}
