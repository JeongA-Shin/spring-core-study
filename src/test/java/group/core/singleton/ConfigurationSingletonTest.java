package group.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import group.core.AppConfig;
import group.core.member.MemberRepository;
import group.core.member.MemberService;
import group.core.member.MemberServiceImpl;
import group.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ConfigurationSingletonTest {
    
    @Test
    void configurationTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
            AppConfig.class);
    
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository",MemberRepository.class);
    
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
    
        System.out.println("memberRepositoy1"+ memberRepository1);
        System.out.println("memberRepository2"+memberRepository2);
        System.out.println("memberRepository bean"+memberRepository);
    
        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
            AppConfig.class);
    
        //AppConfig 자체도 빈으로 등록됨
        AppConfig bean = ac.getBean(AppConfig.class);
    
        //타입이 뭔지 확인
        System.out.println("bean = "+bean.getClass());
    }
}
