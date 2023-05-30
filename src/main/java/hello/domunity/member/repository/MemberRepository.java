package hello.domunity.member.repository;

import hello.domunity.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String memberId);

    Optional<Member> findByMemberName(String memberName);

}
