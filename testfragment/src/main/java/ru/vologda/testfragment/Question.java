package ru.vologda.testfragment;

import java.util.Objects;

public class Question {
    public String text;
    public boolean answer;
    public String answerText;
    Question(String text, boolean answer,String answerText){
        this.text = text;
        this.answer=answer;
        this.answerText = answerText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return answer == question.answer &&
                text.equals(question.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, answer);
    }
}
