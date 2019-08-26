package com.example.vplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
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
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class playerRegister extends AppCompatActivity {

    String ServerURL = "http://192.168.43.181/playertest2Get_data.php";

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

            class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
                @Override
                protected String doInBackground(String... params){

                    String nameHolder = name;
                    String phoneHolder = phone;
                    String emailHolder = email;
                    String passwordHolder = password;
                    String placeHolder = place;
                    String sportsHolder = sports;
                    String experienceHolder = experience;

                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                    nameValuePairs.add(new BasicNameValuePair("name", nameHolder));
                    nameValuePairs.add(new BasicNameValuePair("phone", phoneHolder));
                    nameValuePairs.add(new BasicNameValuePair("email", emailHolder));
                    nameValuePairs.add(new BasicNameValuePair("password", passwordHolder));
                    nameValuePairs.add(new BasicNameValuePair("place", placeHolder));
                    nameValuePairs.add(new BasicNameValuePair("sports", sportsHolder));
                    nameValuePairs.add(new BasicNameValuePair("experience", experienceHolder));

                    try {

                        HttpClient httpClient = new DefaultHttpClient();

                        HttpPost httpPost = new HttpPost(ServerURL);

                        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                        HttpResponse httpResponse = httpClient.execute(httpPost);

                        HttpEntity httpEntity = httpResponse.getEntity();


                    } catch (ClientProtocolException e) {

                    } catch (IOException e) {

                    }
                    return "Data Inserted Successfully";
                }
                @Override
                protected void onPostExecute(String result){

                    super.onPostExecute(result);

                    Toast.makeText(playerRegister.this,"Data Submitted Successfully", Toast.LENGTH_LONG).show();
                }
            }

            SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

            sendPostReqAsyncTask.execute(name,phone,email,password,place,sports,experience);

        }

    }

