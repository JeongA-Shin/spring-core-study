package group.core.member;

//역할(배역,대본) == 인터페이스
public interface MemberRepository {

    void save(Member member);
    Member findById(Long memberId);
}
