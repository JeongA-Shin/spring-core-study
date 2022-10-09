package group.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration //설정 정보니까
//@Component가 붙은 애들을 자동으로 스프링 빈으로 등록시킴.
@ComponentScan(
    basePackages = "group.core",
    basePackageClasses = AutoAppConfig.class,
    //지금 현재 이전 강의들에서 썼던 AppConfig 파일을 제외하기 위해서 해당 옵션을 넣어줌
    excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION,classes=Configuration.class)
)
public class AutoAppConfig {

}
