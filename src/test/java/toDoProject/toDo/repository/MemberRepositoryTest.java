package toDoProject.toDo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toDoProject.toDo.domain.Member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void findByUsername() {
        Member member = new Member("kim", "zzq", "3902");
        memberRepository.save(member);

        assertThat(memberRepository.findByUsername("zzq").getUsername()).isEqualTo(member.getUsername());
    }
}