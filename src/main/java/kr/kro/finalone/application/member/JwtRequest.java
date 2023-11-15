package kr.kro.finalone.application.member;

import java.io.Serial;
import java.io.Serializable;

public class JwtRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -5616176897013108345L;

    private String memberName;
    private String password;

    // standard getters and setters
    public String getMemberName() {
        return memberName;
    }

    public void setUsername(String memberName) {
        this.memberName = memberName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
