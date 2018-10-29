package com.example.eric.quizmaestro;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Spinner spinner;
    private DrawerLayout mDrawerLayout;
    public static ArrayList<String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Decks.initalizeDecks();
        mDrawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.mipmap.ic_mymenu5);

        try {
            NavigationView navigationView = findViewById(R.id.nav_view);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            HomeFragment homeFragment = new HomeFragment();
            fragmentTransaction.replace(R.id.content_frame, homeFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {

                            // set item as selected to persist highlight
                            menuItem.setChecked(true);

                            // close drawer when item is tapped
                            mDrawerLayout.closeDrawers();

                            // Add code here to update the UI based on the item selected
                            // For example, swap UI fragments here
                            Log.d("Selected", menuItem.getTitle().toString());

                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                            if (menuItem.getTitle().equals("Home")) {
                                HomeFragment homeFragment = new HomeFragment();

                                fragmentTransaction.replace(R.id.content_frame, homeFragment);
                                fragmentTransaction.addToBackStack(null);

                                // Commit the transaction
                                fragmentTransaction.commit();
                            }

                            if (menuItem.getTitle().equals("Decks")) {
                                DeckPage deckPage = new DeckPage();

                                fragmentTransaction.replace(R.id.content_frame, deckPage);
                                fragmentTransaction.addToBackStack(null);

                                fragmentTransaction.commit();
                            }

                            if (menuItem.getTitle().equals("Help")) {
                                HelpPage helpPage = new HelpPage();

                                fragmentTransaction.replace(R.id.content_frame, helpPage);
                                fragmentTransaction.addToBackStack(null);

                                fragmentTransaction.commit();
                            }

                            return true;
                        }
                    });
            // Set 'home' as selected
            navigationView.getMenu().getItem(0).setChecked(true);
        }
        catch(Exception e) {
            Log.d("ERROR ANT ERROR", e.getMessage());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
