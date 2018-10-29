package com.example.eric.quizmaestro;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;



public class EditDeck extends Fragment {

    private EditText curQuestion, editText;
    private EditText curAnswer;

    public static EditText curDeck, curDeckStudy;
    public static String curDeckName, curDeckNameStudy;

    private String question, answer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_editdeck, container, false);


        // Set the deck title
        editText = view.findViewById(R.id.deckName);

//        if (editText.getText().toString().equals(DeckPage.allDecks[3].getName())){
//            editText.setText(DeckPage.mName, TextView.BufferType.EDITABLE);
//        }
//        else {
//            editText.setText(DeckPage.selectedDeck, TextView.BufferType.EDITABLE);
//        }


        return view;
    }


    public void addCard(View view) {

        curDeck = view.findViewById(R.id.deckName);
        curQuestion =  view.findViewById(R.id.carQ);
        curAnswer = view.findViewById(R.id.carA);
        question = curQuestion.getText().toString();
        answer = curAnswer.getText().toString();
        curDeckName = curDeck.getText().toString();

//        if (curDeckName.equals(DeckPage.allDecks[0].getName())) {
//            DeckPage.scienceList.add(question);
//            DeckPage.scienceList.add(answer);
//
//        }
//
//        else if (curDeckName.equals(DeckPage.allDecks[1].getName())) {
//            DeckPage.mathList.add(question);
//            DeckPage.mathList.add(answer);
//        }
//
//        else if (curDeckName.equals(DeckPage.allDecks[2].getName())) {
//            DeckPage.girlfriendList.add(question);
//            DeckPage.girlfriendList.add(answer);
//
//        }
//
//        else if (curDeckName.equals(DeckPage.allDecks[3].getName())){
//            DeckPage.userList.add(question);
//            DeckPage.userList.add(answer);
//        }
//
//        else {
//        }
    }

    public void StudyDeck(View view) {
        curDeckStudy = view.findViewById(R.id.deckName);
        curDeckNameStudy = curDeckStudy.getText().toString();
        return;
    }
}