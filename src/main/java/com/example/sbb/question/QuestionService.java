package com.example.sbb.question;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList(){
        return questionRepository.findAll();
    }


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


}
