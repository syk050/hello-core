package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // 모든 객체들을 관리해 줌 @Bean 객체들
        // AppConfig 에 있는 환경설정 정보를 가지고 스프링 컨테이너에 넣어 관리해 준다
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // memberService라는 이름을 찾고
        // 반환 타입은 MemberService.class
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member: " + member.getName());
        System.out.println("findMember: " + findMember.getName());
    }
}
