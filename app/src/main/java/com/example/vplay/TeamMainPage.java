package com.example.vplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.vplay.network.NetworkQueryTask;
import com.example.vplay.network.interfaces.OnNetworkQueryCompletionListener;
import com.example.vplay.network.models.NetworkResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class TeamMainPage extends AppCompatActivity {

    Button bookground, challenegethrowaccept, playerselect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teammainpage);

        List<NameValuePair> l = new ArrayList<>();
        l.add(new BasicNameValuePair("query", "select * from teamtest;"));

        NetworkQueryTask networkQueryTask = new NetworkQueryTask(Constants.TEST_URL + "/" + "dbQuery.php", "POST", new OnNetworkQueryCompletionListener() {
            @Override
            public void onNetworkQueryCompleted(@NonNull NetworkResponse networkResponse) {
                Log.d("VPlay", networkResponse.getResponseMessage().toString());
            }
        }, l);

        networkQueryTask.execute();

        bookground=(Button)findViewById(R.id.bookagroundAMP);
        challenegethrowaccept= (Button)findViewById(R.id.challengeAMP);
        playerselect= (Button)findViewById(R.id.playerlistAMP);

        bookground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bookgoundpagenavigate();
            }
        });

        challenegethrowaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                challengethrowacceptpagenavigate();
            }
        });

        playerselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerselectpagenavigate();
            }
        });

    }
    public void bookgoundpagenavigate(){
        startActivity(new Intent(this, bookaground.class));
    }

    public void challengethrowacceptpagenavigate(){
        startActivity(new Intent(this, challengefragments.class));
    }

    public void playerselectpagenavigate(){
        startActivity(new Intent(this, .class)); //to be added class name
    }
}
