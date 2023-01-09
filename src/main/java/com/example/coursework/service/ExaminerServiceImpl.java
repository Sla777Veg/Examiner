package com.example.coursework.service;

import com.example.coursework.exception.UnSufficientQuestionsException;
import com.example.coursework.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService{

    private final QuestionService questionService;


    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> questions = new HashSet<>();
        if(questionService.getAll().size() < amount) {
            throw new UnSufficientQuestionsException();
        }
        while (questions.size() < amount) {
            questions.add(questionService.getRandomQuestion());
        }
        return questions;
    }

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }
}
