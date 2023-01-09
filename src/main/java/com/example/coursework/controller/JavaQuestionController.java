package com.example.coursework.controller;

;
import com.example.coursework.model.Question;
import com.example.coursework.service.JavaQuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {

    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping
    public Collection<Question> getAll() {
        return this.javaQuestionService.getAll();
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam("question") String question,
                                @RequestParam("answer") String answer) {
        return this.javaQuestionService.add(question, answer);
    }

    @GetMapping("remove")
    public Question removeQuestion(@RequestParam("question") String question,
                                   @RequestParam("answer") String answer) {
        Question q = new Question(question, answer);
        return this.javaQuestionService.remove(q);
    }

}
