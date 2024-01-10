package com.example.quenswer.question;

import com.example.quenswer.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    @SneakyThrows
    public Question getQuestion(Integer id) {
        Optional<Question> oq=questionRepository.findById(id);
        if (oq.isPresent()){
            return oq.get();
        }else{
            throw new DataNotFoundException("data not found");
        }
    }

    public void delete(Question question){
        questionRepository.delete(question);
    }

    public void createQuestion(String subject, String content, Member member){
        Question qs=new Question();
        qs.setSubject(subject);
        qs.setContent(content);
        qs.setAuthor(member);
        qs.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(qs);
    }

    public Page<Question> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 6,Sort.by(sorts));
        return this.questionRepository.findAll(pageable);   //pageable객체가 Page<Question>타입임
    }

}
