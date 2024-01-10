package com.example.quenswer.answer;

import com.example.quenswer.member.Member;
import com.example.quenswer.member.MemberService;
import com.example.quenswer.question.Question;
import com.example.quenswer.question.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final MemberService memberService;
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String CreateAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm,BindingResult bindingResult, Principal principal){
        Question question=questionService.getQuestion(id);
        Member member=this.memberService.getMember(principal.getName());
        if (bindingResult.hasErrors()){
            model.addAttribute("question",question);
            return "question_details";
        }
        answerService.createAnswer(question,answerForm.getContent(),member);
        return String.format("redirect:/question/details/%s", id);
    }

}
