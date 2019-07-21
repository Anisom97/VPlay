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
import java.util.ArrayList;
import java.util.List;

public class teamRegister extends AppCompatActivity {

    String ServerURL = "http://localhost/teamtest1Get_data.php" ;

    String tteamname, tnumberofmembers, tcaptainname, temail, tphoneno, tplace, tsports, tpassword;

    EditText teamname, numberofmembers, captainname, email, phoneno, place, sports, password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_register);

        teamname = (EditText) findViewById(R.id.teamnameEtT);
        numberofmembers = (EditText) findViewById(R.id.membersEtT);
        captainname = (EditText) findViewById(R.id.captainEtT);
        email = (EditText) findViewById(R.id.emailEtT);
        phoneno = (EditText) findViewById(R.id.phoneEtT);
        place = (EditText) findViewById(R.id.placeEtT);
        sports = (EditText) findViewById(R.id.sportsEtT);
        password = (EditText) findViewById(R.id.passwordEtT);

        register = (Button) findViewById(R.id.registerBtT);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetDataTeam();

                InsertDataTeam(tteamname, tnumberofmembers, tcaptainname, temail, tphoneno, tplace, tsports, tpassword);
            }
        });
    }

    public void GetDataTeam() {
        tteamname = teamname.getText().toString();
        tnumberofmembers = numberofmembers.getText().toString();
        tcaptainname = captainname.getText().toString();
        temail = email.getText().toString();
        tphoneno = phoneno.getText().toString();
        tplace = place.getText().toString();
        tsports = sports.getText().toString();
        tpassword = password.getText().toString();
    }

    public void InsertDataTeam(final String teamname, final String numberofmembers, final String captainname, final String email, final String phoneno, final String place, final String sports,
    final String password){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String TeamNameHolder = teamname ;
                String NumberofMembersHolder = numberofmembers ;
                String CaptainNameHolder = captainname ;
                String EmailHolder = email ;
                String PhonenoHolder = phoneno ;
                String PlaceHolder = place ;
                String SportsHolder = sports ;
                String PasswordHolder= password;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("teamname", TeamNameHolder));
                nameValuePairs.add(new BasicNameValuePair("numberofmembers", NumberofMembersHolder));
                nameValuePairs.add(new BasicNameValuePair("captainname", CaptainNameHolder));
                nameValuePairs.add(new BasicNameValuePair("email", EmailHolder));
                nameValuePairs.add(new BasicNameValuePair("phoneno", PhonenoHolder));
                nameValuePairs.add(new BasicNameValuePair("place", PlaceHolder));
                nameValuePairs.add(new BasicNameValuePair("sports", SportsHolder));
                nameValuePairs.add(new BasicNameValuePair("password", PasswordHolder));

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
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(teamRegister.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(teamname, numberofmembers, captainname, email, phoneno, place, sports, password);
    }




}
