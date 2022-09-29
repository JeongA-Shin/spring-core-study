package group.core.order;

import group.core.member.Grade;
import group.core.member.Member;
import group.core.member.MemberService;
import group.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    
    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        
        Order order = orderService.createOrder(memberId,"itemA",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
