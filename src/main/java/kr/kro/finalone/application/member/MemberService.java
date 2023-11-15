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
import org.springframework.security.core.userdetails.User;

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
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberName(memberName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with memberName: " + memberName));

        return new User(member.getMemberName(), member.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public void authenticate(String memberName, String password) throws Exception {
        UserDetails userDetails = loadUserByUsername(memberName);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new Exception("INVALID_CREDENTIALS");
        }
    }

}
