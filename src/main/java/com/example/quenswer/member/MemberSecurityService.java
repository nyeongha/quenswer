package com.example.quenswer.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberSecurityService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> om=memberRepository.findByUserId(username);
        if (om.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        Member member=om.get();
        List<GrantedAuthority> authorities=new ArrayList<>();
        if ("admin".equals(username)){
            authorities.add(new SimpleGrantedAuthority(MemberRole.Admin.getValue()));
        }else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.Member.getValue()));
        }
        return new User(member.getUserId(),member.getPassword(),authorities);
    }
}
