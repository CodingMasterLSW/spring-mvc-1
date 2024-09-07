package hello.servlet.domain;

import static org.assertj.core.api.Assertions.*;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MemberRepositoryTest {

    // 싱글톤이기 때문에 새로운 객체 x
    MemberRepository memberRepository = MemberRepository.getInstance();

    // 테스트 끝나면 초기화
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원_저장_테스트(){
        //given
        Member member = new Member("Lee", 25);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(member.getId()).isSameAs(findMember.getId());
        assertThat(savedMember.getId()).isSameAs(1L);

    }
    @Test
    void 모든_회원_검색_테스트(){
        Member member1 = new Member("Lee", 25);
        Member member2 = new Member("Sohn", 28);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> allMember = memberRepository.findAll();

        assertThat(allMember.size()).isSameAs(2);
        assertThat(allMember).contains(member1, member2);
    }


}
