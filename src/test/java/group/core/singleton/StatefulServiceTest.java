package group.core.singleton;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
    
    static class TestConfig{
        
        @Bean //스프링 컨테이너는 빈 객체들을 알아서 자동으로 싱글톤으로 관리한다
        public StatefulService statefulService(){
            return new StatefulService();
        }
    
    }
    
    @Test
    void statefulServiceSingleton(){
        //스프링 컨테이너는 빈 객체들을 알아서 자동으로 싱글톤으로 관리한다
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
            TestConfig.class);
    
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
    
        //Thread A : 사용자 A가 10000원을 주문
        statefulService1.order("userA",10000);
    
        //Thread B : 사용자 B가 20000원을 주문 
        //즉 A가 주문하고 해당 주문을 조회하는 사이에 B의 주문이 끼어든 것
        statefulService1.order("userB",20000);
    
        //Thread A : 사용자 A가 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println(price); //20000이 나옴,,,;;
        
        //이유: statefulService1이든 2이든 이름만 다르고 사실은 똑같은 (싱글톤이니까) statefulService 객체를 참조하는 것이므로
        //이유 검증
        Assertions.assertThat(statefulService1).isSameAs(statefulService2);
    }
    

}