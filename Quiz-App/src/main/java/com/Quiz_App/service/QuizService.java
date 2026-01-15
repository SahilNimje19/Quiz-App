package com.Quiz_App.service;

import com.Quiz_App.dto.QuizQuestionRequest;
import com.Quiz_App.model.QuizQuestion;
import com.Quiz_App.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }
    public List<Map<String, Object>> getQuizQuestion() {
        List<QuizQuestion> questions = quizRepository.findAll();
        List<Map<String, Object>> response = new ArrayList<>();
        for(QuizQuestion q : questions) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", q.getId());
            map.put("question", q.getQuestion());
            map.put("options", List.of(
                q.getQuestion(),
                    q.getOptionA(),
                    q.getOptionB(),
                    q.getOptionC(),
                    q.getOptionD()
            ));
            response.add(map);
        }
        return response;
    }
    public Map<String, Integer> submitQuiz(Map<Integer, String> answer) {
        int score = 0;
        int total = answer.size();
        for(Map.Entry<Integer, String> entry: answer.entrySet()) {
            QuizQuestion q = quizRepository.findById(entry.getKey());
            if(q.getCorrectOption() == entry.getValue().charAt(0)) {
                score++;
            }
        }
        Map<String, Integer> result = new HashMap<>();
        result.put("score", score);
        result.put("total", total);
        return result;
    }
    public void addQuestion(QuizQuestionRequest request) {
        if (request.getCorrectOption() == null ||
                request.getCorrectOption().length() != 1) {
            throw new IllegalArgumentException("Correct option must be A/B/C/D");
        }

        // ✅ DTO → ENTITY CONVERSION
        QuizQuestion q = new QuizQuestion();
        q.setQuestion(request.getQuestion());
        q.setOptionA(request.getOptionA());
        q.setOptionB(request.getOptionB());
        q.setOptionC(request.getOptionC());
        q.setOptionD(request.getOptionD());
        q.setCorrectOption(request.getCorrectOption().charAt(0));

        quizRepository.save(q);
    }
}
