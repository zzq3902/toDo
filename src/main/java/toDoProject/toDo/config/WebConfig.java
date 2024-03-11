package toDoProject.toDo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import toDoProject.toDo.details.MemberUserDetails;
import toDoProject.toDo.repository.MemberRepository;
import toDoProject.toDo.service.CustomUserDetailService;

@Configuration
public class WebConfig {

    @Autowired
    MemberRepository memberRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService(memberRepository);
    }

    @Bean
    public UserDetails userDetails() {
        return new MemberUserDetails();
    }
}
