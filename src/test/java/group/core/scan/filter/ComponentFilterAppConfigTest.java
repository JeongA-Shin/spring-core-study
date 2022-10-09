package group.core.scan.filter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


public class ComponentFilterAppConfigTest {
    
    
    @Configuration
    @ComponentScan(
        //필터를 어노테이션으로 걸어줌, @MyIncludeComponent 어노테이션이 있으면 컴포넌트 스캔 대상에 넣음 ==  @MyIncludeComponent를 단 객체는 컨테이너에 빈으로 등록
        includeFilters = @Filter(type = FilterType.ANNOTATION,classes = MyIncludeComponent.class),
        //필터를 어노테이션으로 걸어줌, @MyExcludeComponent 어노테이션이 있으면 컴포넌트 스캔 대상에서 제외 == @MyExcludeComponent를 단 객체는 컨테이너에 빈으로 등록되지 않음
        excludeFilters = @Filter(type = FilterType.ANNOTATION,classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{
    
    }
    
    @Test
    void filterScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
            ComponentFilterAppConfig.class);
    
//        @MyIncludeComponent를 단 객체는 컨테이너에 빈으로 등록됨
        BeanA beanA = ac.getBean("beanA",BeanA.class);
        assertThat(beanA).isNotNull();
    
//        @MyExcludeComponent를 단 객체는 컨테이너에 빈으로 등록되지 않음
        //BeanB beanB = ac.getBean("beanB",BeanB.class);
        //org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'beanB' available 발생
        //따라서 테스트도 assertThrows 로 해줌
        assertThrows(NoSuchBeanDefinitionException.class,()->{
            ac.getBean("beanB",BeanB.class);
        });
        
    }
    
  

}
