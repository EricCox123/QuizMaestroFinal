package com.example.eric.quizmaestro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CardFlipActivity extends FragmentActivity {

    private Boolean mShowingBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_card_container);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new CardFrontFragment())
                    .commit();
        }
    }

    /**
     * A fragment representing the front of the card.
     */
    public static class CardFrontFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.selected_card_front, container, false);

            // Set the question on the front of a card
            TextView question = v.findViewById(R.id.question);
            question.setText(CardAdapter.selectedCard.getQuestion());
            Log.d("CardFlipActivity", "Setting question to: "
                    + CardAdapter.selectedCard.getQuestion());
            return v;
        }
    }

    /**
     * A fragment representing the back of the card.
     */
    public static class CardBackFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.selected_card_back, container, false);

            TextView answer = v.findViewById(R.id.answer);
            answer.setText(CardAdapter.selectedCard.getAnswer());
            Log.d("CardFlipActivity", "Setting answer to: "
                    + CardAdapter.selectedCard.getAnswer());
            return v;
        }
    }

    public void flipCard(View view) {
        if (mShowingBack) {
            getSupportFragmentManager().popBackStack();
            mShowingBack = false;
            return;
        }

        // Flip to the back.
        mShowingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        getSupportFragmentManager()
                .beginTransaction()

                // Replace the default fragment animations with animator resources
                // representing rotations when switching to the back of the card, as
                // well as animator resources representing rotations when flipping
                // back to the front (e.g. when the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)

                // Replace any fragments currently in the container view with a
                // fragment representing the next page (indicated by the
                // just-incremented currentPage variable).
                .replace(R.id.container, new CardBackFragment())

                // Add this transaction to the back stack, allowing users to press
                // Back to get to the front of the card.
                .addToBackStack(null)

                // Commit the transaction.
                .commit();
    }

    public void editCard(View view) {
        finish();
        Context context = view.getContext();
        Intent intent = new Intent(context, EditCard.class);
        context.startActivity(intent);
    }

    public void rateCardEasy(View view) {
        finish();
    }

    public void rateCardMedium(View view) {
        finish();
    }

    public void rateCardHard(View view) {
        finish();
    }
}
