package group.core.order;

import group.core.discount.DiscountPolicy;
import group.core.member.Member;
import group.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    
    //이전에 App Config(공연 기획자)가 없어서 생성자 주입 없이 역할과 구현을 직접 연결해줘야 했던 코드
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//   // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    
    
    //이제 AppConfig가 있으므로 생성자 주입이 가능해짐
    //인터페이스(역할)에만 의존 가능해짐
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
    
    //only for test
    public MemberRepository getMemberRepository(){
        return this.memberRepository;
    }
}
