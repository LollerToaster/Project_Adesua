package com.project.adrianangub.project_adesua;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    //ProgressBar progressBar;
    private static final String URL_LOGIN = "https://adesuaapi.spottyus.com/student/login?username=user&password=pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        editTextUsername = (EditText) findViewById(R.id.usernameLogin);
        editTextPassword = (EditText) findViewById(R.id.passwordLogin);

        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        //If The user was already logged in and didnt logged out.
        if (SharedPrefManager.getInstance(this).isLoggedIn())
        {
            finish();
            startActivity(new Intent(this, HomeActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    private void userLogin() {
        //first getting the values
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        //progressBar.setVisibility(View.GONE);
                        Log.d("Response", response);

                        try {
                            //getting the user from the response
                            JSONObject userJson = new JSONObject(response);
                            JSONObject schoolJson = userJson.getJSONObject("school");

                            Log.d(response, "onResponse: ");
                            //creating a new user object
                            Users user = new Users(
                                    //userJson.getInt("id"),
                                    userJson.getString("uid"),
                                    userJson.getString("desc"),
                                    userJson.getString("meta"),
                                    userJson.getString("stat"),
                                    schoolJson.getString("schoolID"),
                                    schoolJson.getString("schoolName"),
                                    schoolJson.getString("schoolAddress"),
                                    schoolJson.getString("schoolAcryn"),
                                    schoolJson.getString("schoolNum"),
                                    userJson.getString("fullname")
                            );
                            //storing the user in shared preferences
                            SharedPrefManager.getInstance(getApplicationContext()).sharedResponse(user);

                            //starting the profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError response)
                    {
                        // error
                        Toast.makeText(getApplicationContext(), "volley borkken ;(", Toast.LENGTH_SHORT).show();
                    }
                }
        )

        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {
                Map<String, String> headers = new HashMap<>();
                String credentials = "admin:12345";
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                headers.put("Authorization", auth);
                return headers;
            }
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}

