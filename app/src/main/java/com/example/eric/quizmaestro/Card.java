package com.example.eric.quizmaestro;

enum Difficulty {
    EASY, MEDIUM, HARD
}

public class Card {
    private String question;
    private String answer;
    private Boolean hasBeenRated;
    private Difficulty rating;
    private String picture;

    public Card(String question, String answer) {
        this.hasBeenRated = false;
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String Q)
    {
        this.question = Q;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String A)
    {
        this.answer = A;
    }

    public Boolean getHasBeenRated() {
        return this.hasBeenRated;
    }

    public void setHasBeenRated(Boolean hasBeenRated) {
        this.hasBeenRated = hasBeenRated;
    }

    public void setRating(Difficulty difficulty) {
        this.rating = difficulty;
    }

    public Difficulty getRating() {
        return this.rating;
    }

    public void setNotification() { }

    @Override
    public String toString() {
        return "Q: " + getQuestion() + ", A: " + getAnswer();
    }
}
