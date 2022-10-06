package group.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;

import group.core.AppConfig;
import group.core.member.MemberService;
import group.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextBasicFindTest {
    
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Test
    @DisplayName("빈 이름과 빈 타입으로 조회하기")
    public void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService: "+memberService); //group.core.member.MemberServiceImpl  //해당 역할의 "구현체"가 담김
        System.out.println("memberService.getClass(): "+memberService.getClass()); //class group.core.member.MemberServiceImpl
    
        //검증
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    
    }
    
    
    @Test
    @DisplayName("이름 없이 빈 타입으로만 조회하기")
    public void findBeanByType(){
        //얘의 경우 같은 타입이 여러 개이몀ㄴ 좀 곤란해질 수도 있음
        // 빈 메서드의 리턴 타입이 겹치는 게 많으면
        //즉 여러 역할들이 한 구현체만 사용하게 되면 곤란해질 수도 있음
        
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService: "+memberService); //group.core.member.MemberServiceImpl  //해당 역할의 "구현체"가 담김
        
        //검증
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        
    }
    
    @Test
    @DisplayName("빈 이름과 구현체 타입으로 조회하기")
    public void findBeanByName2(){
        
        //즉 빈 타입을 찾을 때, 빈 메서드의 리턴 타입이 아니라 그냥 리턴 객체의(구현체) 타입 자체로 직접적으로 찾을 수 있음
        
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        System.out.println("memberService: "+memberService); //group.core.member.MemberServiceImpl  //해당 역할의 "구현체"가 담김
        System.out.println("memberService.getClass(): "+memberService.getClass()); //class group.core.member.MemberServiceImpl
        
        //검증
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        
    }
    
    /**
    실패용 테스트 만들기 - assertThrows
     */
    @Test
    @DisplayName("조회 대상 스프링 빈이 없음 - 이름으로 조회")
    public void findNoBeanByName(){
        //ac.getBean("xxxx",MemberService.class); //org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'xxxx' available
        
        //assertThrrows : 파라미터의 람다 식을 돌렸을 때, 첫 번째 파라미터로 준 예외가 터져야 성공이라는 거임
        //예외가 발생하지 않으면 그게 실패임
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () ->
            ac.getBean("xxxxx", MemberService.class));
    }
    
}
