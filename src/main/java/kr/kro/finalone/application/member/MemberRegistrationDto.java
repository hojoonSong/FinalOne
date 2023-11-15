package kr.kro.finalone.application.member;

import kr.kro.finalone.domain.member.Member;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberRegistrationDto {
    private String memberName;
    private String password;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Member toMember(PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setMemberName(memberName);
        member.setPassword(passwordEncoder.encode(password));
        return member;
    }
}
