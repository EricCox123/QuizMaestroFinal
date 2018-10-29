package com.example.eric.quizmaestro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import java.util.ArrayList;

public class StudyPage extends Fragment {

    public static ArrayAdapter<Card> arrayAdapter;
    public RecyclerView mRecyclerView;
    public static RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_studypage, container, false);

        // Set data to display in list (Cards)
        arrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.item, R.id.helloText,
                    Decks.getDeck(DeckPage.selectedDeck));

        mRecyclerView = view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Set view to display/use the selected deck
        mAdapter = new CardAdapter(Decks.getDeck(DeckPage.selectedDeck));
        mRecyclerView.setAdapter(mAdapter);

        FloatingActionButton fab = view.findViewById(R.id.card_fab);
        fab.setImageResource(android.R.drawable.ic_input_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateCard createCard = new CreateCard();
                getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, createCard, "createDeck")
                    .addToBackStack(null)
                    .commit();
            }
        });

        return view;
    }
}