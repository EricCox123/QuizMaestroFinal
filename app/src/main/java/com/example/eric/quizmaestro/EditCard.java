package com.example.eric.quizmaestro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditCard extends AppCompatActivity {

    private Button mEditCard;
    private EditText mEditQuestion;
    private EditText mEditAnswer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_card);

        mEditQuestion = findViewById(R.id.edit_card_question);
        mEditAnswer = findViewById(R.id.edit_card_answer);
        mEditCard = findViewById(R.id.edit_card_button);

        mEditQuestion.setHint(CardAdapter.selectedCard.getQuestion());
        mEditAnswer.setHint(CardAdapter.selectedCard.getAnswer());

        mEditCard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    // Once button has been released
                    case MotionEvent.ACTION_UP:
                        String question = mEditQuestion.getText().toString();
                        String answer = mEditAnswer.getText().toString();

                        Decks.updateCard(DeckPage.selectedDeck, CardAdapter.selectedCard, question, answer);

                        StudyPage.mAdapter.notifyDataSetChanged();

                        Context context = view.getContext();
                        Intent intent = new Intent(context, CardFlipActivity.class);
                        context.startActivity(intent);

                        finish();
                }
                return false;
            }
        });
    }
}
