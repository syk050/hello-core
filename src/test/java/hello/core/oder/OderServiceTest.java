package hello.core.oder;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Oder;
import hello.core.order.OderService;
import hello.core.order.OderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OderServiceTest {

    MemberService memberService;
    OderService oderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();

        memberService = appConfig.memberService();
        oderService = appConfig.oderService();
    }

    @Test
    void createOder () {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Oder oder = oderService.createOrder(memberId, "itemA", 10000);

        Assertions.assertThat(oder.getDiscountPrice()).isEqualTo(1000);
    }
}
