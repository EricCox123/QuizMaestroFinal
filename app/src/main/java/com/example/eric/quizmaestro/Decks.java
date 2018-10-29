package com.example.eric.quizmaestro;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Decks {

    // Demo decks
    public static final String scienceDeck = "Science!";
    public static final String mathDeck = "Math";
    public static final String girlfriendDeck = "Girlfriend";

    public static Map<String, ArrayList<Card>> decks = new HashMap<>();

    public static void initalizeDecks() {
        decks.clear();
        decks.put(scienceDeck, new ArrayList<Card>());
        decks.put(mathDeck, new ArrayList<Card>());
        decks.put(girlfriendDeck, new ArrayList<Card>());
    }


    public static ArrayList<Card> getDeck(String deckName) {
        ArrayList<Card> deck = null;
        if (decks.containsKey(deckName)) {
            deck = decks.get(deckName);
        }
        return deck;
    }

    public static void addDeck(String deckName) {
        if (!decks.containsKey(deckName)) {
            decks.put(deckName, new ArrayList<Card>());
        }
    }

    public static void removeDeck(String deckName) {
        if (decks.containsKey(deckName)) {
            decks.remove(deckName);
        }
    }

    public static void addCardToDeck(String deckName, Card card) {
        if (decks.containsKey(deckName)) {
            ArrayList<Card> deck = decks.get(deckName);
            for (Card deckCard: deck) {
                if (deckCard.getQuestion().equals(card.getQuestion())){
                    Log.d("Error:","Card is already in deck");
                    return;
                }
            }
            deck.add(card);
        }
    }

    public static void removeCardFromDeck(String deckName, Card card) {
        if (decks.containsKey(deckName)) {
            ArrayList<Card> deck = decks.get(deckName);
            deck.remove(card);
        }
    }

    public static void updateCard(String deckName, Card card, String question, String answer) {
        if (decks.containsKey(deckName)) {
            ArrayList<Card> deck = decks.get(deckName);
            int index = -1;
            for (Card deckCard : deck) {
                if (deckCard.getQuestion().equals(card.getQuestion())) {
                    index = deck.indexOf(deckCard);
                    Log.d("Got Update", "Goit index: " + String.valueOf(index));
                    break;
                }
            }
            if (index != -1) {

                Log.d("UPDATE CARD", "Updated card " + card.toString()
                        + " to: Q. " + question
                        + "A. " + answer);

                Card cardToUpdate = deck.get(index);
                cardToUpdate.setQuestion(question);
                cardToUpdate.setAnswer(answer);
                CardAdapter.selectedCard = cardToUpdate;

            }
        }
    }

    public static Card createCard(String question, String answer) {
        Card card = new Card(question, answer);
        return card;
    }
}
