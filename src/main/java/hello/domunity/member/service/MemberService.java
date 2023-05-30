package hello.domunity.member.service;

import hello.domunity.member.domain.Member;

public interface MemberService {

    void save(Member member);

    void updateMember(Member member);

    Member findMember(String memberId);

    Member findName(String memberName);

}
