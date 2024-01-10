package com.example.quenswer.answer;

import com.example.quenswer.member.Member;
import com.example.quenswer.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public void createAnswer(Question question, String content, Member author){
        Answer answer1=new Answer();
        answer1.setQuestion(question);
        answer1.setCreateDate(LocalDateTime.now());
        answer1.setContent(content);
        answer1.setAuthor((author));
        answerRepository.save(answer1);

    }
}
