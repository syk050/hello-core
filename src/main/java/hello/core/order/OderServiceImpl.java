package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OderServiceImpl implements OderService {

//    private final MemberRepository repository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 구현체를 지워 인터페이스에만 의존하도록 설계
    // => DIP 를 지킴
    // 하지만 구현체가 없어 널포인트익셉션 발생
    // 누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현 객체를
    // 대신 생성하고 주입해주어야 한다 => AppConfig
    private final MemberRepository repository;
    private DiscountPolicy discountPolicy;

    public OderServiceImpl(MemberRepository repository, DiscountPolicy discountPolicy) {
        this.repository = repository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Oder createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = repository.findById(memberId);
        // OCP 적용
        // OderService 는 할인이 얼마인지 결과만 받음
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Oder(memberId, itemName, itemPrice, discountPrice);
    }
}
