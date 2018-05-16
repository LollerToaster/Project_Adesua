package com.project.adrianangub.project_adesua;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialMultiAutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // MultiAutoCompleteTextView Essentials=========================================================
    //MultiAutoCompleteTextView MultipleValuesholdGenre;
    //String[] Genres = { "Action","Math","English","Wololo" };

    //MultiAutoCompleteTextView MultipleValuesholdTitle;
    //String[] Titles = { "Your mom fat","Gay","Brother","Ecks d" };

    //MultiAutoCompleteTextView MultipleValuesholdAuthor;
    //String[] Authors = { "Adrian","Jeff","Pyrocynical","Hahahaha" };
    // MultiAutoCompleteTextView Essentials=========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Go to Search Results
        Button btn = (Button)findViewById(R.id.Button1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, SearchResults.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //DANGER ===================================================================================

        // MultiAutoCompleteTextView Essential

        //Genres
        //MultipleValuesholdGenre = (MaterialMultiAutoCompleteTextView)findViewById(R.id.genre);
        //ArrayAdapter<String> Genre = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Genres);
        //MultipleValuesholdGenre.setAdapter(Genre);
        //MultipleValuesholdGenre.setThreshold(2);
        //MultipleValuesholdGenre.setTokenizer(new MaterialMultiAutoCompleteTextView.CommaTokenizer());

        //Title
        //MultipleValuesholdTitle = (MaterialMultiAutoCompleteTextView)findViewById(R.id.genre);
        //ArrayAdapter<String> Title = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, Titles);
        //MultipleValuesholdTitle.setAdapter(Title);
        //MultipleValuesholdTitle.setThreshold(2);
        //MultipleValuesholdTitle.setTokenizer(new MaterialMultiAutoCompleteTextView.CommaTokenizer());

        //Authors
        //MultipleValuesholdAuthor = (MaterialMultiAutoCompleteTextView)findViewById(R.id.author);
        //ArrayAdapter<String> Author = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Authors);
        //MultipleValuesholdAuthor.setAdapter(Author);
        //MultipleValuesholdAuthor.setThreshold(2);
        //MultipleValuesholdAuthor.setTokenizer(new MaterialMultiAutoCompleteTextView.CommaTokenizer());

        //DANGER ===================================================================================
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the action_bar_menu/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.search_button) {

            startActivity(new Intent(SearchActivity.this, SearchActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            //Snackbar.make(findViewById(R.id.placeSnackBar), "Snackbar worked as intended, brah", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();;
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(SearchActivity.this, home.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //Snackbar.make(findViewById(R.id.placeSnackBar), "Returning to home worked", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();;
            return true;
        } else if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(SearchActivity.this, profileSettingsActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_search) {
            startActivity(new Intent(SearchActivity.this, SearchActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            //Snackbar.make(findViewById(R.id.placeSnackBar), "Intent to search worked", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
