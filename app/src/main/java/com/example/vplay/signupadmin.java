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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/*public class signupadmin extends AppCompatActivity {

    /*String tname,temail,tphone,tlocation,tlandmark,tdetails,tpassword;

    EditText name, email, phone, location, landmark, details, password;
    Button adminrergister, button;

    public static final String DB_URL = "jdbc:mysql://192.168.43.181/anirban";;
    public static final String USER = "mrsom";
    public static final String PASS= "Anisom97";

    /*static {
        DB_URL = "jdbc:mysql://192.168.43.181/anirban";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupadmin);

        name = (EditText) findViewById(R.id.nameEtA);
        email = (EditText) findViewById(R.id.emailEtA);
        phone = (EditText) findViewById(R.id.phoneEtA);
        location = (EditText) findViewById(R.id.locationEtA);
        landmark = (EditText) findViewById(R.id.landmarkEtA);
        details = (EditText) findViewById(R.id.detailsEtA);
        password = (EditText) findViewById(R.id.passwordEtA);

        adminrergister = (Button) findViewById(R.id.registerBtA);

        adminrergister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Send ObjSend = new Send();
                ObjSend.execute("");

            }
        });
    }

    private class Send extends AsyncTask <String, String, String> {

        String msg="";

        String tname= name.getText().toString();
        String temail= email.getText().toString();
        String tphone= phone.getText().toString();
        String tlocation= location.getText().toString();
        String tlandmark= landmark.getText().toString();
        String tdetails= details.getText().toString();
        String tpassword= password.getText().toString();


        protected String doInBackground(String... strings){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 if(conn == null){
                     Toast.makeText(signupadmin.this, "connection failed", Toast.LENGTH_LONG).show();
                 }
                 else {
                     String query="INSERT into admintest (name,email,phone,location,landmark,details,password) VALUES ('"+tname+"','"+temail+"','"+tphone+"','"+tlocation+"','"+tlandmark+"','"+tdetails+"','"+tpassword+"')";
                     Statement stmt= conn.createStatement();
                     stmt.executeUpdate(query);
                     Toast.makeText(signupadmin.this, "data inserted successfully", Toast.LENGTH_LONG).show();
                 }
                 conn.close();

            } catch (Exception e) {
                Toast.makeText(signupadmin.this, "data not inserted", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            return msg;
        }


    }


}*/


public class signupadmin extends AppCompatActivity {

    String ServerURL = "http://192.168.43.181/admintest1Get_data.php" ;

    String tname,temail,tphone,tlocation,tlandmark,tdetails,tpassword;

    EditText name, email, phone, location, landmark, details, password;
    Button button;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupadmin);

        name=(EditText) findViewById(R.id.nameEtA);
        email=(EditText)findViewById(R.id.emailEtA);
        phone=(EditText) findViewById(R.id.phoneEtA);
        location=(EditText) findViewById(R.id.locationEtA);
        landmark=(EditText)findViewById(R.id.landmarkEtA);
        details=(EditText) findViewById(R.id.detailsEtA);
        password= (EditText)findViewById(R.id.passwordEtA);

        button= (Button) findViewById(R.id.registerBtA);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData();

                InsertData(tname,temail,tphone,tlocation,tlandmark,tdetails,tpassword);
            }
        });
    }

    public void GetData(){
        tname=name.getText().toString();
        temail=email.getText().toString();
        tphone=phone.getText().toString();
        tlocation=location.getText().toString();
        tlandmark=landmark.getText().toString();
        tdetails=details.getText().toString();
        tpassword= password.getText().toString();
    }

    public void InsertData(final String name, final String email, final String phone, final String location, final String landmark, final String details, final String password){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String NameHolder = name ;
                String EmailHolder = email ;
                String PhoneHolder = phone ;
                String LocationHolder = location ;
                String LandmarkHolder = landmark ;
                String DetailsHolder = details ;
                String PasswordHolder = password ;
                //String ServerURL = "http://localhost/admintest1Get_data.php" ; //added once again

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("name", NameHolder));
                nameValuePairs.add(new BasicNameValuePair("email", EmailHolder));
                nameValuePairs.add(new BasicNameValuePair("phone", PhoneHolder));
                nameValuePairs.add(new BasicNameValuePair("location", LocationHolder));
                nameValuePairs.add(new BasicNameValuePair("landmark", LandmarkHolder));
                nameValuePairs.add(new BasicNameValuePair("details", DetailsHolder));
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

                Toast.makeText(signupadmin.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(name, email, phone, location, landmark, details, password);
    }

}
