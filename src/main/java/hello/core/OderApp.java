package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Oder;
import hello.core.order.OderService;
import hello.core.order.OderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OderApp {

    public static void main(String[] args) {
/*        AppConfig appConfig = new AppConfig();

//        MemberService memberService = new MemberServiceImpl();
//        OderService oderService = new OderServiceImpl();
        MemberService memberService = appConfig.memberService();
        OderService oderService = appConfig.oderService();*/

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = context.getBean("memberService", MemberService.class);
        OderService oderService = context.getBean("oderService", OderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Oder oder = oderService.createOrder(memberId, "itemA", 10000);

        System.out.println(oder);
    }
}
