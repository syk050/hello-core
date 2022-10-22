package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 원래 구현체를 직접 쓰는거 안 좋음
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl oderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getRepository();
        MemberRepository memberRepository2 = oderService.getRepository();

        // 세개 다 똑같음
        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getRepository()).isSameAs(memberRepository);
        Assertions.assertThat(oderService.getRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        // 순수한 클래스라면 다음과 같이 출력되어야 한다.
        // class hello.core.AppConfig
        // 실제 출력: 클래스 명에 xxxCGLIB가 붙으면서 상당히 복잡해짐

        // 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서
        // AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고,
        // 그 다른 클래스를 스프링 빈으로 등록한 것이다

        // AppConfig 의 @Configuration 을 적용하지 않고, @Bean 만 적용하면 어떻게 될까?
        // @Configuration 을 붙이면 바이트코드를 조작하는 CGLIB 기술을 사용해서 싱글톤을 보장하지만,
        // 만약 @Bean만 적용하면 어떻게 될까?
        // => MemberRepository가 3번 호출 됨
        System.out.println("bean = " + bean.getClass());

        // @Bean만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다
    }
}
