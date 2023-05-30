package hello.domunity.member.service;

import hello.domunity.member.domain.Member;

public interface MemberService {

    /**
     * 회원 저장
     * @param member
     */
    void save(Member member);

    /**
     * 회원 정보 변경 (Only Nick Name)
     * @param member
     */
    void updateMember(Member member);

    /**
     * 회원 정보 id로 찾기
     * @param memberId
     * @return
     */
    Member findMember(String memberId);

    /**
     * 회원 정보 name으로 찾기
     * @param memberName
     * @return
     */
    Member findName(String memberName);

}
