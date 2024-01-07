package com.example.quenswer.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/signup")
    public String signup(MemberCreateForm memberCreateForm){
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid MemberCreateForm memberCreateForm, BindingResult bindingResult){
        try {
            memberService.createMember(memberCreateForm.getUserId(),memberCreateForm.getPassword(),memberCreateForm.getEmail());
        }catch(Exception e) {
            e.printStackTrace();
            if (!memberCreateForm.getPassword().equals(memberCreateForm.getPassword1())) {
                bindingResult.rejectValue("password1", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            }
            if(memberService.isUserId(memberCreateForm.getUserId())){
                bindingResult.rejectValue("userId","signupFailed", "이미 등록된 사용자입니다.");
            }
            if (memberService.isEmail(memberCreateForm.getEmail())){
                bindingResult.rejectValue("email","signupFailed", "이미 등록된 이메일입니다.");
            }
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/question/list";
    }


    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

}


