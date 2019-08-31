package com.example.vplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.vplay.network.NetworkQueryTask;
import com.example.vplay.network.interfaces.OnNetworkQueryCompletionListener;
import com.example.vplay.network.models.NetworkResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class TeamMainPage extends AppCompatActivity {

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
    }
}
