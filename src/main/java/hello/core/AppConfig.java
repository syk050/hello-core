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

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OderService oderService() {
        return new OderServiceImpl(memberRepository(), discountPolicy());
    }


    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        /* 클라이언트 코드인 OrderServiceImpl 를 포함해서
         * 사용 영역의 어떤 코드도 변경할 필요가 없다
         */
        return new RateDiscountPolicy();
    }
    
}
