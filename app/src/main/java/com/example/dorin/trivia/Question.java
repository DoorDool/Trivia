package com.example.dorin.trivia;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {

    String question, answer;
    ArrayList<String> incorrect;

    public Question(String question, String answer, ArrayList<String> incorrect) {
        this.question = question;
        this.answer = answer;
        this.incorrect = incorrect;
    }

    // getters and setters
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {this.question = question;}
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) {this.answer = answer;}
    public ArrayList<String> getIncorrect() { return incorrect; }
    public void setIncorrect(ArrayList<String> incorrect) {this.incorrect = incorrect;}
}
