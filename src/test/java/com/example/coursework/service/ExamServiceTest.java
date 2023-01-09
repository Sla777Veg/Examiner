package com.example.coursework.service;

import com.example.coursework.exception.UnSufficientQuestionsException;
import com.example.coursework.model.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExamServiceTest {
    @Mock
    QuestionService questionService;

    @InjectMocks
    ExaminerServiceImpl examinerService;

    @Test
    void whenAmountOfQuestionIsSufficientThenGetQuestionReturnExactAmount() {
        List<Question> questionList = List.of(
                new Question("q1", "a1"),
                new Question("q2", "a2"),
                new Question("q3", "a3")
        );
        when(questionService.getAll()).thenReturn(questionList);
        when(questionService.getRandomQuestion()).thenReturn(questionList.get(0), questionList.get(1));
        Assertions.assertThat(examinerService.getQuestions(2))
                .hasSize(2).containsOnly(questionList.get(0), questionList.get(1));
    }

    @Test
    void whenAmountOfQuestionIsInSufficientThenMethodThrowsException() {
        when(questionService.getAll()).thenReturn(Collections.emptyList());
        Assertions.assertThatThrownBy(()->examinerService.getQuestions(100))
                .isInstanceOf(UnSufficientQuestionsException.class);
    }
}
