package group.core.scan;

import group.core.AutoAppConfig;
import group.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class AutoAppConfigTest {
    
    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
            AutoAppConfig.class);
    
        MemberService memberService = ac.getBean(MemberService.class);
    
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }

}
