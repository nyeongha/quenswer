package com.example.quenswer;

import com.example.quenswer.question.Question;
import com.example.quenswer.question.QuestionRepository;
import com.example.quenswer.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor    //final이 붙은 속성을 포함하는 생성자를 자동으로 생성
@RequestMapping("/question")
public class MainController {

    private final QuestionRepository questionRepository;
    private final QuestionService questionService;
    //@ResponseBody 애너테이션은 URL 요청에 대한 응답으로 문자열을 리턴하라는 의미
    //@ResponseBody 애너테이션을 생략한다면 "index"라는 이름의 템플릿 파일을 찾게 됨
    @GetMapping("/list")
    public String QuestionList(Model model){
        List<Question> Lq=questionService.getList();
        model.addAttribute("questionList",Lq);
        return "question_list";
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/question/list";   //redirect:<URL> - URL로 리다이렉트 (리다이렉트는 완전히 새로운 URL로 요청이 된다.),forward:<URL> - URL로 포워드 (포워드는 기존 요청 값들이 유지된 상태로 URL이 전환된다.)
    }

    @GetMapping(value = "/details/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_details";
    }


}
