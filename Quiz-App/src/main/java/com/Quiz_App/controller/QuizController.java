package com.Quiz_App.controller;

import com.Quiz_App.dto.QuizQuestionRequest;
import com.Quiz_App.model.QuizQuestion;
import com.Quiz_App.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/questions")
    public  ResponseEntity<?> getQuestions() {
        return ResponseEntity.ok(quizService.getQuizQuestion());
    }
    @PostMapping("/submit")
    public ResponseEntity<?> submitQuiz(@RequestBody Map<String , Map<Integer, String>> body) {
        if(!body.containsKey("answer")) {
            return ResponseEntity.badRequest().body("Answer missing");
        }
        return ResponseEntity.ok(quizService.submitQuiz(body.get("answer")));
    }

    @PostMapping("/question")
    public ResponseEntity<?> addQuestion(@RequestBody QuizQuestionRequest request) {
        quizService.addQuestion(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
