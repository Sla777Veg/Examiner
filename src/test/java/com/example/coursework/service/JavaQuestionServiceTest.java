package com.example.coursework.service;

import com.example.coursework.exception.UnSufficientQuestionsException;
import com.example.coursework.model.Question;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class JavaQuestionServiceTest {

    private JavaQuestionService javaQuestionService;
    private List<Question> questions = List.of(
            new Question("q1", "a1"),
            new Question("q2", "a2"),
            new Question("q3", "a3"),
            new Question("q4", "a4"),
            new Question("q5", "a5")
    );

    @BeforeEach
    void setYp() {
        this.javaQuestionService = new JavaQuestionService();
        questions.forEach(javaQuestionService::add);

    }

    @Test
    void addQuestionsTest() {
        Question question = new Question("q6", "a6");
        Question actual = this.javaQuestionService.add(question);
        AssertionsForClassTypes.assertThat(actual).isEqualTo(question);
        assertThat(this.javaQuestionService.getAll())
                .hasSize(6)
                .contains(question);
    }

    @Test
    void removeQuestionsTest() {
        Question expected = this.questions.get(0);
        Question actual = this.javaQuestionService.remove(expected);
        AssertionsForClassTypes.assertThat(actual).isEqualTo(expected);
        assertThat(this.javaQuestionService.getAll())
                .hasSize(4).doesNotContain(expected);
    }

    @Test
    void getRandomQuestion() {
        Question actual = this.javaQuestionService.getRandomQuestion();
        AssertionsForClassTypes.assertThat(actual).isIn(questions);
    }

    @Test
    void getAllTest() {
        assertThat(this.javaQuestionService.getAll())
                .hasSize(5).containsAll(this.questions);
    }

    @Test
    void whenListIsEmptyThenGetRandomQuestionReturnException() {
        this.javaQuestionService = new JavaQuestionService();
        assertThatThrownBy(() -> javaQuestionService.getRandomQuestion())
                .isInstanceOf(UnSufficientQuestionsException.class);
    }
}
