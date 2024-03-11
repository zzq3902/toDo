package toDoProject.toDo.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toDoProject.toDo.domain.Member;
import toDoProject.toDo.dto.MemberDTO;
import toDoProject.toDo.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public Member findOne(long id) {
        return getMember(id);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional
    public Long create(Member member) {
        Member saveMember = new Member(member.getName(), member.getUsername(),
                passwordEncoder.encode(member.getPassword()));
        memberRepository.save(saveMember);
        return saveMember.getId();
    }

    @Transactional
    public void update(long id, MemberDTO memberDTO) {
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        Member member = getMember(id);
        member.update(memberDTO);
    }
    @Transactional
    public void remove(Long id) {
        Member member = getMember(id);
        memberRepository.delete(member);
    }

    private Member getMember(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + id));
    }

    public void autoLogin(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("==================isAuthenticated " + SecurityContextHolder.getContext().getAuthentication());
        } catch (AuthenticationException e) {
            log.info("error = " + e);
        }
    }

}
