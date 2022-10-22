package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean oderService -> new MemoryMemberRepository()
    // => 싱글톤 깨짐? => 테스트 코드로 실행 ㄱ

    @Bean
    public MemberService memberService() {
        /* 스프링은 빈을 생성하고, 의존관계를 주입하는 단계가 나누어져 있다.
         * 그런데 이렇게 자바 코드로 스프링 빈을 등록하면
         * 생성자를 호출하면서 의존관계 주입도 한번에 처리된다.
         * => 순서를 무시하게 됨 PDF: 4- 3p*/
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        /* 클라이언트 코드인 OrderServiceImpl 를 포함해서
         * 사용 영역의 어떤 코드도 변경할 필요가 없다
         */
        System.out.println("AppConfig.discountPolicy");
        return new RateDiscountPolicy();
    }

}
