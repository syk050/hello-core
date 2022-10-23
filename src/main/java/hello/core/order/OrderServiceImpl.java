package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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
    public OrderServiceImpl(MemberRepository repository, DiscountPolicy discountPolicy) {
        /* 생성자 주입의 이점
         * 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일이 없다.
         * 따라서 불변하게 설계할 수 있다.
         *
         * 생성자 주입을 사용하면 주입 데이터를 누락 했을 때 컴파일 오류가 발생한다
         *
         * final 키워드
         * 생성자 주입을 사용하면 필드에 final 키워드를 사용할 수 있다
         * 생성자에서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아준다
         * 기억하자! 컴파일 오류는 세상에서 가장 빠르고, 좋은 오류다
         * 수정자 주입을 포함한 나머지 주입 방식은 모두 생성자 이후에 호출되므로,
         * 필드에 final 키워드를 사용할 수 없다.
         */
        this.repository = repository;
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
