package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
    * @Qualifier 사용해 DiscountPolicy 구현체 지정
    * 만약 @Qualifier값을 못찾으면 동명의 스프링빈을 추가로 찾는다.
    * But, @Qualifier는 @Qualifier를 찾을때만 사용하는 것이 좋음.
    * 해당 Qualifier 없을 경우 -> Bean 검색 -> 그도 없을 경우 NoSuchBeanDefinitionException 반환.
    * */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용도
    public MemberRepository  getMemberRepository() {
        return memberRepository;
    }
}
