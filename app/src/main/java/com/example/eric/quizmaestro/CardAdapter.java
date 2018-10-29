package com.example.eric.quizmaestro;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private ArrayList<Card> cardsList;
    public static Card selectedCard;

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        protected TextView vQuestion;
        protected ImageButton vEdit;

        public CardViewHolder(CardView v) {
            super(v);
            vQuestion = (TextView) v.findViewById(R.id.txtQuestion);
        }
    }

    public CardAdapter(ArrayList<Card> cardsList) {
        this.cardsList = cardsList;
    }

    @Override
    public CardAdapter.CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        CardViewHolder cvh = new CardViewHolder(cv);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, final int position) {
        Card card = cardsList.get(position);
        holder.vQuestion.setText(card.getQuestion());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Card card = Decks.getDeck(DeckPage.selectedDeck).get(position);
                Log.d("SELECTED CARD", card.toString());

                selectedCard = card;

                Context context = view.getContext();
                Intent intent = new Intent(context, CardFlipActivity.class);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return cardsList.size();
    }
}

