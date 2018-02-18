package com.project.adrianangub.project_adesua;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;


public class mainMenu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        // Toolbar Related =========================================================================

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null) {
            myToolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2B3642")));
            //getSupportActionBar().setTitle("Test [Proj Adesua]");
            getSupportActionBar().setTitle(Html.fromHtml("<font color='#25B18A'>[ Project Adesua ]</font>"));

        }
        // Toolbar Related =========================================================================

    }


    // Settings Button Related =====================================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.item1:
                //your action
                break;
            case R.id.item2:
                //your action
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
    // Settings Button Related =====================================================================

}
