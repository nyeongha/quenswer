package com.example.sbb;

import com.example.sbb.answer.Answer;
import com.example.sbb.answer.AnswerRepository;
import com.example.sbb.question.Question;
import com.example.sbb.question.QuestionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SbbApplicationTests {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void create() {
        Question q2=new Question();
        q2.setSubject("2000번 문제 해결 팁 좀 알려주세요");
        q2.setContent("코드 로직에 무슨 문제가 있는지 확인해주세요,,,문제를 잘모르겠어요");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);

        Question q3=new Question();
        q3.setSubject("4500번 문제 해결해주세요");
        q3.setContent("코드 로직에 무슨 문제가 있는지 확인해주세요,,,문제를 잘모르겠습니다.");
        q3.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q3);

    }

    @Test
    void delete(){
        Optional<Question> oq=questionRepository.findById(2);
        oq.ifPresent(question -> questionRepository.delete((question)));
        Long Lq=questionRepository.count();
        assertEquals(1,Lq);

    }
    @Test
    void search(){
        List<Question> Lq=questionRepository.findBySubjectLike("%2000번%");
        assertEquals(2,Lq.size());
    }
    @Test
    void answerCreate(){
        Optional<Question> oq=questionRepository.findById(3);
        oq.ifPresent(a->{
            Answer a1=new Answer();
            a1.setContent("100 90을 입력하면 16이나와야하는데 17이 나옵니다.수정해보세요");
            a1.setCreateDate(LocalDateTime.now());
            a1.setQuestion(a);
            answerRepository.save(a1);

            Answer a2=new Answer();
            a2.setContent("1000 9099을 입력하면 206이나와야하는데 107이 나옵니다.수정해보세요");
            a2.setCreateDate(LocalDateTime.now());
            a2.setQuestion(a);
            answerRepository.save(a2);

        });

    }
    @Test
    @Transactional
    void answerRead(){
        Optional<Question> oq=questionRepository.findById(3);
        oq.ifPresent(a->{
            List<Answer> La=answerRepository.findByQuestion(a);
            assertEquals(2,La.size());
            List<Answer> La2=a.getAnswerList();
            assertEquals(2,La2.size());
                });
    }
}
