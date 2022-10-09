package group.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//구현 == 구현체, 각 구현들은 모두 쉽게 대체 가능하다
@Component
public class MemberServiceImpl implements MemberService {
    
    //MemberRepository 역할(인터페이스)에 대한 구현체 선택해주기
    //private final MemberRepository memberRepository= new MemoryMemberRepository();
    
    //위에처럼 클라이언트가 직접 구현체를 선택하는 문제가 생김
    // 따라서 공연 기획자(App Config)를 도입함.
    //그리고 App Config가 있어서 아래와 같은 의존성 주입이 가능해짐
    // 즉, 생성자를 통해서 해당 역할에 어떤 구현체를 넣을지 결정하게 됨
    // 그리고 이걸 가르켜서 "생성자 주입"이라고 함
    private final MemberRepository memberRepository;
    @Autowired //자동 의존 관계 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }
    
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
    
   //only for test
    public MemberRepository getMemberRepository(){
        return this.memberRepository;
    }
    
}
