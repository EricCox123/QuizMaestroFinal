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
    private Button mDeleteCard;
    private EditText mEditQuestion;
    private EditText mEditAnswer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_card);

        mEditQuestion = findViewById(R.id.edit_card_question);
        mEditAnswer = findViewById(R.id.edit_card_answer);
        mEditCard = findViewById(R.id.edit_card_button);
        mDeleteCard = findViewById(R.id.delete_card_button);

        mEditQuestion.setText(CardAdapter.selectedCard.getQuestion());
        mEditAnswer.setText(CardAdapter.selectedCard.getAnswer());

        mEditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = mEditQuestion.getText().toString();
                String answer = mEditAnswer.getText().toString();

                Decks.updateCard(DeckPage.selectedDeck, CardAdapter.selectedCard, question, answer);

                StudyPage.mAdapter.notifyDataSetChanged();

                Context context = view.getContext();
                Intent intent = new Intent(context, CardFlipActivity.class);
                context.startActivity(intent);

                finish();
            }
        });

        mDeleteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Decks.deleteCard(DeckPage.selectedDeck, CardAdapter.selectedCard);

                CardAdapter.selectedCard = null;

                StudyPage.mAdapter.notifyDataSetChanged();

                finish();
            }
        });
    }
}
