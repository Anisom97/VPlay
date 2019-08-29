package com.example.vplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerRegister extends AppCompatActivity {

    String ServerURL = Constants.TEST_URL + "/playertest2Get_data.php";

    String playernameR,phoneR,emailR,passwordR,placeR,sportsR,experienceR;
    EditText name,phone,email,password,place,sports,experience;
    Button playerregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_register);

        name = (EditText) findViewById(R.id.playernameEtP);
        phone = (EditText) findViewById(R.id.phoneEtP);
        email = (EditText) findViewById(R.id.emailEtP);
        password = (EditText) findViewById(R.id.passwordEtP);
        place = (EditText) findViewById(R.id.placeEtP);
        sports = (EditText) findViewById(R.id.sportsEtP);
        experience = (EditText) findViewById(R.id.experienceEtP);

        playerregister = (Button) findViewById(R.id.registerBtP);

        playerregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getdataplayer();
                insertdataplayer(playernameR,phoneR,emailR,passwordR,placeR,sportsR,experienceR);
            }
        });
    }

    public void getdataplayer() {
        playernameR=name.getText().toString();
        phoneR=phone.getText().toString();
        emailR=email.getText().toString();
        passwordR=password.getText().toString();
        placeR=place.getText().toString();
        sportsR=sports.getText().toString();
        experienceR=experience.getText().toString();
    }

    public void insertdataplayer(final String name, final String phone, final String email,
                                  final String password, final String place, final String sports, final String experience) {

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name,phone,email,password,place,sports,experience);
    }

    class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params){

            String[] keys= {
                    "name", "phone", "email", "password", "place", "sports", "experience"
            };

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

            int i = 0;
            for (String key: keys)
                nameValuePairs.add(new BasicNameValuePair(key, params[i++]));

            try {

                HttpClient httpClient = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost(ServerURL);

                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse httpResponse = httpClient.execute(httpPost);

                HttpEntity httpEntity = httpResponse.getEntity();
                Scanner sc = new Scanner(httpEntity.getContent());
                while(sc.hasNext())
                    Log.d("VPlay", sc.nextLine());

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            return "Data Inserted Successfully";
        }
        @Override
        protected void onPostExecute(String result){

            super.onPostExecute(result);

            Toast.makeText(PlayerRegister.this,"Data Submitted Successfully", Toast.LENGTH_LONG).show();
        }
    }
}

