package com.example.coursework.service;

import com.example.coursework.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);
}
