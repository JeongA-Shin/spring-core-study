package group.core;

import group.core.discount.DiscountPolicy;
import group.core.discount.FixDiscountPolicy;
import group.core.discount.RateDiscountPolicy;
import group.core.member.MemberRepository;
import group.core.member.MemberService;
import group.core.member.MemberServiceImpl;
import group.core.member.MemoryMemberRepository;
import group.core.order.OrderService;
import group.core.order.OrderServiceImpl;

public class AppConfig {
    
    private MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    
    
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    
}
