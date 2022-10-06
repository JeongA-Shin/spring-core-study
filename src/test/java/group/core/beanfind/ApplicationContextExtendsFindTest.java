package group.core.beanfind;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import group.core.discount.DiscountPolicy;
import group.core.discount.FixDiscountPolicy;
import group.core.discount.RateDiscountPolicy;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class ApplicationContextExtendsFindTest {
    
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    
    // 중복 타입 빈이 생기도록 여기시 임시로 AppConfig를 하나 새로 만듦
    //즉 여기 테스트만을 위한 임시 AppConfig임 - configuration 임
    //내부 클래스에 static을 씀 == 해당 부모 클래스에서만 이 내부 클래스에 접근 가능하게 하겠다
    //다른 클래스들은 얘 접근 못하게 해줌
    //클래스 단위에서의 static 키워드는 위와 같은 의미임, 일반적인 변수 단위에서의ㅣ static 의미와는 다름
    @Configuration
    static class TestConfig{
        
        @Bean
        public DiscountPolicy rateDisCountPolicy(){
            return new RateDiscountPolicy();
        }
    
        @Bean
        public DiscountPolicy fixDisCountPolicy(){
            return new FixDiscountPolicy();
        }
        
    }
    
    @Test
    @DisplayName("부모 타입으로 조회-모든 자식들이 끌려나옴. 자식이 둘 이상 있으면 중복 오류 발생")
    public void findBeanByDuplicate(){
        //org.springframework.beans.factory.NoUniqueBeanDefinitionException
        //DiscountPolicy bean = ac.getBean(DiscountPolicy.class); //당연,,,DiscountPolicy를 리턴하는 두 개가 있으니까
        //assertThorws : 다른 테스트 코드 가 보면 설명 나와 있음
        assertThrows(NoUniqueBeanDefinitionException.class, ()->{
            ac.getBean(DiscountPolicy.class);
        });
    }
    
    @Test
    @DisplayName("부모 타입으로 조회시 자식이 둘 이상 있으면 빈 이름을 지정해주면 됨")
    public void findBeanByDuplicateBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy",DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }
    
    @Test
    @DisplayName("특정 하위 타입으로 조회도 가능 - 물론 안 좋은 방법")
    public void findBeanBySubType(){
        DiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }
    
    @Test
    @DisplayName("부모 타입으로 전부 조회해보기")
    public void findAllByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
    
        for (String s : beansOfType.keySet()) {
            System.out.println("key:"+s);
            System.out.println("bean: "+beansOfType.get(s));
        }
        
    }
    
    @Test
    @DisplayName("최고 부모인 Object 타입으로 전부 조회해보기")
    public void findAllBeansByObject(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        
        for (String s : beansOfType.keySet()) {
            System.out.println("key:"+s);
            System.out.println("bean: "+beansOfType.get(s));
        }
        
    }
    
    
    
    
}
