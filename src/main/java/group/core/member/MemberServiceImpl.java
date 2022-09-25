package group.core.member;

//구현 == 구현체, 각 구현들은 모두 쉽게 대체 가능하다
public class MemberServiceImpl implements MemberService {
    
    //MemberRepository 역할(인터페이스)에 대한 구현체 선택해주기
    private final MemberRepository memberRepository= new MemoryMemberRepository();
    
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }
    
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
