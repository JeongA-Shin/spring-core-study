package group.core.order;

import group.core.discount.DiscountPolicy;
import group.core.discount.FixDiscountPolicy;
import group.core.member.Member;
import group.core.member.MemberRepository;
import group.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
