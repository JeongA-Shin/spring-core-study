package group.core.discount;

import group.core.member.Member;

public interface DiscountPolicy {
    
    /**
     *
     * @param member
     * @param price
     * @return 할인 대상 금액 e.g 천 원이 할인되었다면 천 원을 리턴
     */
    int discount(Member member, int price);
}
