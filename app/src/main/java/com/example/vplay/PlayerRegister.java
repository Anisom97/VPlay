package com.example.vplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vplay.network.NetworkQueryTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class PlayerRegister extends AppCompatActivity {

    private final String SERVER_URL = Constants.TEST_URL + "/playertest2Get_data.php";

    String playernameR,phoneR,emailR,passwordR,placeR,sportsR,experienceR;
    EditText name,phone,email,password,place,sports,experience;
    Button playerregister;

    private final String[] schema = {
            "name", "phone", "email", "password", "place", "sports", "experience"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_register);

        name = findViewById(R.id.playernameEtP);
        phone = findViewById(R.id.phoneEtP);
        email = findViewById(R.id.emailEtP);
        password = findViewById(R.id.passwordEtP);
        place = findViewById(R.id.placeEtP);
        sports = findViewById(R.id.sportsEtP);
        experience = findViewById(R.id.experienceEtP);
        playerregister = findViewById(R.id.registerBtP);

        playerregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDataPlayer();
                insertDataForPlayer(playernameR,phoneR,emailR,passwordR,placeR,sportsR,experienceR);
            }
        });
    }

    public void getDataPlayer() {
        playernameR = name.getText().toString();
        phoneR = phone.getText().toString();
        emailR = email.getText().toString();
        passwordR = password.getText().toString();
        placeR = place.getText().toString();
        sportsR = sports.getText().toString();
        experienceR = experience.getText().toString();
    }

    public void insertDataForPlayer(String... values) {
        List<NameValuePair> entity = new ArrayList<>();
        int i = 0;
        for (String v: values) entity.add(new BasicNameValuePair(schema[i++], v));

        NetworkQueryTask networkQueryTask = new NetworkQueryTask(SERVER_URL, "POST", entity);
        networkQueryTask.execute();
    }
}

