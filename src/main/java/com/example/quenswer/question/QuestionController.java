package com.example.quenswer.question;

import com.example.quenswer.answer.AnswerForm;
import com.example.quenswer.member.Member;
import com.example.quenswer.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor    //final이 붙은 속성을 포함하는 생성자를 자동으로 생성
@RequestMapping("/question")
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final QuestionService questionService;
    private final MemberService memberService;
    //@ResponseBody 애너테이션은 URL 요청에 대한 응답으로 문자열을 리턴하라는 의미
    //@ResponseBody 애너테이션을 생략한다면 "index"라는 이름의 템플릿 파일을 찾게 됨
    @GetMapping("/list")
    public String QuestionList(Model model,@RequestParam(value="page", defaultValue="0") int page){
        Page<Question> Pq=questionService.getList(page);
        model.addAttribute("paging",Pq);
        return "question_list";
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/question/list";   //redirect:<URL> - URL로 리다이렉트 (리다이렉트는 완전히 새로운 URL로 요청이 된다.),forward:<URL> - URL로 포워드 (포워드는 기존 요청 값들이 유지된 상태로 URL이 전환된다.)
    }

    @GetMapping(value = "/details/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_details";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String createQuestion(QuestionForm questionForm){
        return "question_create";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String createQuestion(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {

            return "question_create";
        }
        Member member=memberService.getMember(principal.getName());
        this.questionService.createQuestion(questionForm.getSubject(),questionForm.getContent(),member);
        return "redirect:/question/list";
//        try {
//            Member member=memberService.getMember(principal.getName());
//            this.questionService.createQuestion(questionForm.getSubject(),questionForm.getContent(),member);
//        }catch (Exception e){
//            e.printStackTrace();
//            {
//                if (questionForm.getSubject().isEmpty()){
//                    bindingResult.rejectValue("subject","subjectreject","제목 필수");
//                }
//            }
//        }
    }

}
