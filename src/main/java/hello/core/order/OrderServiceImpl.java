package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // 면 final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다
public class OrderServiceImpl implements OrderService {

    // final: 생상자나 초기값을 통해서만 수정이 가능하다
    // 초기에 값이 세팅이 되지않으면 오류가 발생한다
//    private final MemberRepository repository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 구현체를 지워 인터페이스에만 의존하도록 설계
    // => DIP 를 지킴
    // 하지만 구현체가 없어 널포인트익셉션 발생
    // 누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현 객체를
    // 대신 생성하고 주입해주어야 한다 => AppConfig
    private final MemberRepository repository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository repository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        // 생성자 주입 이점 PDF: 7- 7p
        this.repository = repository;
        // 완전히 똑같은 타입의 스프링 빈이 2개 있을 때
        // 해결 방안 PDF: 7- 14p
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = repository.findById(memberId);
        // OCP 적용
        // OrderService 는 할인이 얼마인지 결과만 받음
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getRepository() {
        return repository;
    }
}
