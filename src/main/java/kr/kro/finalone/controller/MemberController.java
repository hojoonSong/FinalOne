package kr.kro.finalone.controller;

import kr.kro.finalone.application.member.JwtRequest;
import kr.kro.finalone.application.member.JwtResponse;
import kr.kro.finalone.application.member.MemberRegistrationDto;
import kr.kro.finalone.application.member.MemberService;
import kr.kro.finalone.common.util.JwtTokenUtil;
import kr.kro.finalone.domain.member.Member;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<Member> registerUser(@RequestBody MemberRegistrationDto registrationDto) {
        Member registeredUser = memberService.registerNewUser(registrationDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getMemberName(), authenticationRequest.getPassword());
        final String token = jwtTokenUtil.generateToken(authenticationRequest.getMemberName());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            String username = authentication.getName();
            return ResponseEntity.ok("Hello, " + username + "! (Logged in user)");
        } else {
            return ResponseEntity.ok("Hello, World! (Guest)");
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}