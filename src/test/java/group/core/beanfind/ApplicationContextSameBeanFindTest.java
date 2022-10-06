package group.core.beanfind;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import group.core.member.MemberRepository;
import group.core.member.MemoryMemberRepository;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanFindTest {
    
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);
    
    
    // 중복 타입 빈이 생기도록 여기시 임시로 AppConfig를 하나 새로 만듦
    //즉 여기 테스트만을 위한 임시 AppConfig임 - configuration 임
    //내부 클래스에 static을 씀 == 해당 부모 클래스에서만 이 내부 클래스에 접근 가능하게 하겠다
    //다른 클래스들은 얘 접근 못하게 해줌
    //클래스 단위에서의 static 키워드는 위와 같은 의미임, 일반적인 변수 단위에서의ㅣ static 의미와는 다름
    @Configuration
    static class SameBeanConfig{
        
        @Bean
        public MemberRepository memberRepository(){
            return new MemoryMemberRepository();
        }
    
        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    
    }
    
    /**
     * 당연히 스프링 입장에서는 찾는 타입을 반환하는 게 여러 개가 있으면 어떤 애를 리턴해줘야 할 지 헷갈림
     * - 같은 역할에 대해 다른 구현체를 반환하는 걸로 여러개 있는 걸 찾는 다면 ApplicationContextExtendsFindTest로 가보기
     * - 이거는 부모 타입으로 조회하는 경우에 해당되므로
     * org.springframework.beans.factory.NoUniqueBeanDefinitionException 발생
     */
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생함")
    void findBeanByTypeDuplicate(){
        //MemberRepository bean = ac.getBean(MemberRepository.class);
        //assertThrrows : 파라미터의 람다 식을 돌렸을 때, 첫 번째 파라미터로 준 예외가 터져야 성공이라는 거임
        //예외가 발생하지 않으면 그게 실패임
        assertThrows(NoUniqueBeanDefinitionException.class, ()->{
            ac.getBean(MemberRepository.class);
        });
    }
    
    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정해주면 오류 안 남")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository2",MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }
    
    @Test
    @DisplayName("특정 타입을 반환하는 빈들을 모두 조회해보고 싶을 때")
    void findAllBeanByType(){
        //변수 할당 - alt+ enter
        //이렇게 맵으로 리턴됨
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
    
        //반복문 단축구문 : iter
        for (String s : beansOfType.keySet()) {
            System.out.println("key = "+s +" value : "+beansOfType.get(s));
        }
        
        assertThat(beansOfType.size()).isEqualTo(2);
    }
    
}
