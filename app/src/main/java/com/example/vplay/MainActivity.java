package com.example.vplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button RegisterAdmin, RegisterTeam, RegisterPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RegisterAdmin=(Button) findViewById(R.id.registerAdminRBt);
        RegisterTeam= (Button)findViewById(R.id.registerTeamRBt);
        RegisterPlayer=(Button)findViewById(R.id.registerPlayerRBt);

        RegisterAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterAdminNavigate();
            }

        });

        RegisterTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterTeamNavigate();
            }
        });

        RegisterPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterPlayerNavigate();
            }
        });
    }
    public void RegisterAdminNavigate(){
        startActivity(new Intent(this, signupadmin.class));
    }
    public void RegisterTeamNavigate(){
        startActivity(new Intent(this, teamRegister.class));
    }
    public void RegisterPlayerNavigate(){
        startActivity(new Intent(this, playerRegister.class));
    }



}
