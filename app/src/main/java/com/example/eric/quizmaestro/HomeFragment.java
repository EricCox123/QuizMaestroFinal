package com.example.eric.quizmaestro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.home, container, false);

        final Button gotoDecks = v.findViewById(R.id.gotoDecks);

        gotoDecks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoDecks(view);
            }
        });

        return v;
    }

    public void gotoDecks(View view) {
        DeckPage deckPage = new DeckPage();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, deckPage, "decks")
                .addToBackStack(null)
                .commit();
    }
}
