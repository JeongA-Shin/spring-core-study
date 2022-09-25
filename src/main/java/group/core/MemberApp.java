package group.core;

import group.core.member.Grade;
import group.core.member.Member;
import group.core.member.MemberService;
import group.core.member.MemberServiceImpl;

public class MemberApp {
    
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);
        
        Member findMember = memberService.findMember(1L);
        
        //가입한 멤버와 find 한 멤버가 같으면 원하는 대로 동작하고 있는 것
        System.out.println("new member = "+findMember.getName());
        System.out.println("find member = "+member.getName());
    }
}
