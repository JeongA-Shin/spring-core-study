package group.core.member;

//역할 == 인터페이스
public interface MemberService {

    void join(Member member);
    Member findMember(Long memberId);
}
