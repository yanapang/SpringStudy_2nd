package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //AppConfig 안에있는 환경 설정 정보를 가지고 spring container에 생성해 저장해놓는다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //어떤 메서드를 가지고 올지 정함, 두번째 파라미터는 타입.

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = "+ member.getName());
        System.out.println("findMember = "+ findMember.getName());
    }
}
