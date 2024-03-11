package toDoProject.toDo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import toDoProject.toDo.domain.Member;
import toDoProject.toDo.dto.LoginDTO;
import toDoProject.toDo.dto.MemberDTO;
import toDoProject.toDo.repository.MemberRepository;
import toDoProject.toDo.service.MemberService;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/new")
    public String joinPage(Model model) {
        Member member = new Member();
        model.addAttribute("member", member);
        return "/member/join";
    }

    @PostMapping("/new")
    public String join(@ModelAttribute MemberDTO memberDTO) {
        memberService.create(memberDTO.getMember());
        memberService.autoLogin(memberDTO.getUsername(), memberDTO.getPassword());
        return "redirect:/task";
    }

    @GetMapping("/info")
    public String read(Model model, HttpSession httpSession) {
        long id = (long) httpSession.getAttribute("id");

        Member member = memberService.findOne(id);
        model.addAttribute("member", member);
        return "member/info";
    }

    @GetMapping("/edit")
    public String editPage(Model model, HttpSession httpSession) {
        long id = (long) httpSession.getAttribute("id");
        Member member = memberService.findOne(id);
        model.addAttribute("member", member);
        return "/member/edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute MemberDTO memberDTO,
                       HttpSession httpSession,
                       Model model) {
        long id = (long) httpSession.getAttribute("id");
        Member member = memberService.findOne(id);
        memberService.update(id, memberDTO);
        model.addAttribute("member", member);
        return "/member/info";
    }

    @DeleteMapping("/delete")
    public String delete(HttpSession httpSession) {
        Long id = (Long) httpSession.getAttribute("id");
        memberService.remove(id);
        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("id");
        return "redirect:/home";
    }

    @GetMapping("login")
    public String loginPage(Model model) {
        model.addAttribute("loginDTO", new LoginDTO());
        return "/member/login";
    }
    @PostMapping("login")
    public String login(@ModelAttribute LoginDTO loginDTO) {
        log.info("name = {}", loginDTO.getUsername());
        return "redirect:/task";
    }

}

