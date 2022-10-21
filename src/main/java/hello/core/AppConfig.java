package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OderService;
import hello.core.order.OderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        /* 스프링은 빈을 생성하고, 의존관계를 주입하는 단계가 나누어져 있다.
         * 그런데 이렇게 자바 코드로 스프링 빈을 등록하면
         * 생성자를 호출하면서 의존관계 주입도 한번에 처리된다.
         * => 순서를 무시하게 됨 PDF: 4- 3p*/
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OderService oderService() {
        return new OderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        /* 클라이언트 코드인 OrderServiceImpl 를 포함해서
         * 사용 영역의 어떤 코드도 변경할 필요가 없다
         */
        return new RateDiscountPolicy();
    }

}
