package com.Quiz_App.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class QuizQuestionRequest {

    @Schema(example = "What is JVM?")
    private String question;

    @Schema(example = "Java Virtual Machine")
    private String optionA;

    @Schema(example = "Java Variable Method")
    private String optionB;

    @Schema(example = "Java Vendor Machine")
    private String optionC;

    @Schema(example = "None")
    private String optionD;

    @Schema(example = "A", description = "Must be A, B, C, or D")
    private String correctOption;

    // getters & setters
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }

    public String getOptionA() { return optionA; }
    public void setOptionA(String optionA) { this.optionA = optionA; }

    public String getOptionB() { return optionB; }
    public void setOptionB(String optionB) { this.optionB = optionB; }

    public String getOptionC() { return optionC; }
    public void setOptionC(String optionC) { this.optionC = optionC; }

    public String getOptionD() { return optionD; }
    public void setOptionD(String optionD) { this.optionD = optionD; }

    public String getCorrectOption() { return correctOption; }
    public void setCorrectOption(String correctOption) { this.correctOption = correctOption; }
}
