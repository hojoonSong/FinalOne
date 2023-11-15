package kr.kro.finalone.application.member;

import kr.kro.finalone.domain.member.Member;
import kr.kro.finalone.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class MemberService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public Member registerNewUser(MemberRegistrationDto registrationDto) {
        Member member = registrationDto.toMember(passwordEncoder);
        return memberRepository.save(member);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return new org.springframework.security.core.userdetails.User(member.getMemberName(), member.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}
