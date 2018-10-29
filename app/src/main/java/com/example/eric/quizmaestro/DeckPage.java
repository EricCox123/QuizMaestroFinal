package com.example.eric.quizmaestro;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class DeckPage extends Fragment {

    private Button addDeck;
    public ListView deckList;

    public static String selectedDeck;
    public static String mName;

    private ListView listView;
    private String[] values;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Decks.addCardToDeck("Math",
                new Card("What is 6 x 7?", "42"));
        Decks.addCardToDeck("Girlfriend",
                new Card("When is her birthday?", "April 12th"));
        Decks.addCardToDeck("Science!",
                new Card("Scientific Method i:", "stuff"));
        Decks.addCardToDeck("Science!",
                new Card("Scientific Method :", "stuff"));
        Decks.addCardToDeck("Science!",
                new Card("Scientific Method:", "stuff"));
        Decks.addCardToDeck("Science!",
                new Card("Scientific Metho:", "stuff"));
        Decks.addCardToDeck("Science!",
                new Card("Scientific Meth:", "stuff"));

        // Get all the deck names from the Decks container
        values = Decks.decks.keySet().toArray(new String[Decks.decks.keySet().size()]);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_deckpage, container, false);

        listView = view.findViewById(R.id.listViewDecks);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.mylistview, android.R.id.text1, values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        selectedDeck = values[position];

                        // Change to selected deck
                        StudyPage studyPage = new StudyPage();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content_frame, studyPage, "studyDeck")
                                .addToBackStack(null)
                                .commit();
                    }
                }
        );

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setImageResource(android.R.drawable.ic_input_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDeck();
                Snackbar.make(view, "Creating a deck", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        return view;
    }

    private void createDeck() {
        // Change to selected deck
        CreateDeck createDeck = new CreateDeck();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, createDeck, "createDeck")
                .addToBackStack(null)
                .commit();
    }
}

