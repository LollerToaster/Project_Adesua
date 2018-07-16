package com.project.adrianangub.project_adesua;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
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
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity{

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

        //ASKING FOR PERMISSIONS
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_NOTIFICATION_POLICY
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();


        //CountDownTimer / Notification Block ======================================================
        new CountDownTimer(5000, 1000) {

            public int testes = 0; //test counter

            public void onTick(long millisUntilFinished) {
                //every second or every countDownInterval happens on onTick
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                //PUT API HERE

                //PUT API HERE

                //REMOVE ON REAL STUFF
                testes++;
                //REMOVE ON REAL STUFF

                if (testes == 2 || testes == 4) {
                    sendNotification(testes);
                    testes++;
                }
                //loops the whole countdown
                this.start();
            }
        }.start();
        //==========================================================================================

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
    //Asking for Permissions Block

    //CountDownTimer / Notification Block ==========================================================
    public void sendNotification(int testes) {

        //Get an instance of NotificationManager//
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("My notification")
                .setContentText("Hello ! loop counter: " + testes)
                //.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(NotificationManager.IMPORTANCE_HIGH);

        //Plays a sound on Notification
        /*
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        // Gets an instance of the NotificationManager service//
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // When you issue multiple notifications about the same type of event,
        // it’s best practice for your app to try to update an existing notification
        // with this new information, rather than immediately creating a new notification.
        // If you want to update this notification at a later date, you need to assign it an ID.
        // You can then use this ID whenever you issue a subsequent notification.
        // If the previous notification is still visible, the system will update this existing notification,
        // rather than create a new one. In this example, the notification’s ID is 001

        //NotificationManager.notify().

        mNotificationManager.notify(1, mBuilder.build());
    }
    //==============================================================================================

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
                        //Toast.makeText(getApplicationContext(), response.getMessage(), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), "volley borkken ;(", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Failed to Login. Check your Internet Connection.", Toast.LENGTH_LONG).show();
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

