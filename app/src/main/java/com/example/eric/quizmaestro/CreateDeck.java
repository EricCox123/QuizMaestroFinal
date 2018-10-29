package com.example.eric.quizmaestro;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class CreateDeck extends Fragment {

    View mView;
    Button mCreateDeck;
    EditText mEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.create_deck, container, false);

        mCreateDeck = mView.findViewById(R.id.add_deck_button);
        mEditText = mView.findViewById(R.id.add_deck_text_input);

        mCreateDeck.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    // Once button has been released
                    case MotionEvent.ACTION_UP:
                        String deckName = mEditText.getText().toString();
                        Log.d("ADD DECK PRESSED", deckName);

                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        if (deckName.length() < 100) {

                            if (!Decks.decks.containsKey(deckName)) {
                                Decks.decks.put(deckName, new ArrayList<Card>());

                                // Go back to deck list
                                DeckPage deckPage = new DeckPage();
                                fragmentTransaction.replace(R.id.content_frame, deckPage);
                                fragmentTransaction.addToBackStack(null);

                                // Commit the transaction
                                fragmentTransaction.commit();
                                Snackbar.make(mView, "Deck: " + deckName + " Created", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                            else {
                                Snackbar.make(mView, "A deck with this name already exists", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            }

                        }
                        else {
                            Snackbar.make(mView, "Please use a shorter deck name (less than 100 characters)", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                }
                return false;
            }
        });

        return mView;
    }
}
