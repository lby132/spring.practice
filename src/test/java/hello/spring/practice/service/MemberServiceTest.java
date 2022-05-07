package hello.spring.practice.service;

import hello.spring.practice.domin.Member;
import hello.spring.practice.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @Test
    void 회원가입() {
        final Member member = new Member();
        member.setName("spring");
        member.setPassword("sss110");
        member.setAge(12);

        final Long saveId = memberService.join(member);
        final Member findMember = memberService.findOne(saveId).get();

        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 회원_중복_예외() {
        final Member member = new Member();
        member.setName("spring");

        final Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");

    }
}
