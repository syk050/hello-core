package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OderServiceImpl implements OderService {

    private final MemberRepository repository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // PDF 3- 4p
    //OderServiceImpl 클라이언트 코드가 수정 => OCP 위반
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Oder createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = repository.findById(memberId);
        // OCP 적용
        // OderService 는 할인이 얼마인지 결과만 받음
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Oder(memberId, itemName, itemPrice, discountPrice);
    }
}
