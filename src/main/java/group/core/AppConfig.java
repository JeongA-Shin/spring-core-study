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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
    
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    
    
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    
}
