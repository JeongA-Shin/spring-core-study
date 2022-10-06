package group.core.beanfind;

import group.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Test
    @DisplayName("모든 빈 출력하기 - 스프링 자체가 필요해서 등록한 것까지 포함")
    public void findAllBeans(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        
        for(String beanDefinitionName : beanDefinitionNames){
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name: "+ beanDefinitionName);
            System.out.println("bean: "+ bean);
        }
    }
    
    @Test
    @DisplayName("애플리케이션 빈 출력하기- 스프링 자체가 필요해서 등록한 것은 제외")
    public void findApplicationAllBeans(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        
        for(String beanDefinitionName : beanDefinitionNames){
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//스프링 컨테이너.getBeanDefinition : 현재 등록된 빈에 대한 메타 정보
            
            //즉 스프링 자체에서 필요한 빈들 말고 내가 애플리케이션 개발을 위해서 등록한 애들만 보고 싶을 때
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name: "+ beanDefinitionName);
                System.out.println("bean: "+ bean);
            }
    
            // BeanDefinition.ROLE_INFRASTRUCTURE는 스프링 자체에서 필요해서 등록한 빈들
        }
    }
}
