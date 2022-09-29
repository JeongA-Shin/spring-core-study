package group.core.discount;

import group.core.member.Grade;
import group.core.member.Member;

/**
 * DiscountPolicy라는 역할(배역)의 구현체(배우) 만들기
 */
public class FixDiscountPolicy implements DiscountPolicy  {
    
    private int discountFixAmount = 1000;
    
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
