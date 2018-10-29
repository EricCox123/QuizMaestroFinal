package com.example.eric.quizmaestro;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class CreateCard extends Fragment {

    private String mCurrentDeck;
    private View mView;
    private Button mCreateCard;
    private EditText mEditQuestion;
    private EditText mEditAnswer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentDeck = DeckPage.selectedDeck;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.create_card, container, false);

        mEditQuestion = mView.findViewById(R.id.create_card_question);
        mEditAnswer = mView.findViewById(R.id.create_card_answer);
        mCreateCard = mView.findViewById(R.id.create_card);

        mCreateCard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    // Once button has been released
                    case MotionEvent.ACTION_UP:
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        String question = mEditQuestion.getText().toString();
                        String answer = mEditAnswer.getText().toString();

                        Card card = new Card(question, answer);
                        Decks.addCardToDeck(mCurrentDeck, card);
                        Snackbar.make(view, "Created card in deck: " + DeckPage.selectedDeck, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        StudyPage studyPage = new StudyPage();
                        fragmentTransaction.replace(R.id.content_frame, studyPage, "createDeck")
                                .addToBackStack(null)
                                .commit();
                }
                return false;
            }
        });

        return mView;
    }
}
