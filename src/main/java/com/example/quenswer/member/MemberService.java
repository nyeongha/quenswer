package com.example.quenswer.member;


import com.example.quenswer.question.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member createMember(String userId,String password,String email){
        Member member=new Member();
        member.setEmail(email);
        member.setUserId(userId);
        member.setPassword(passwordEncoder.encode(password));
        this.memberRepository.save(member);
        return member;
    }
    public Member findUserId(String userId){
        Optional<Member> om=memberRepository.findByUserId(userId);
        if(om.isPresent()){
            return om.get();
        }else{
            throw new DataNotFoundException("data not found");
        }
    }
    public Boolean isUserId(String userId){
        Optional<Member> om=memberRepository.findByUserId(userId);
        if (om.isPresent()){
            return true;
        }else{
            return false;
        }
    }

    public Boolean isEmail(String email){
        Optional<Member> om=memberRepository.findByEmail(email);
        if (om.isPresent()){
            return true;
        }else{
            return false;
        }
    }


}
