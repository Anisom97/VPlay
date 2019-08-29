package com.example.vplay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Arrays;
import java.util.List;

public class SignInPage extends AppCompatActivity {
    EditText emailid, passwordL;
    Button loginButton;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        emailid=(EditText)findViewById(R.id.EnterIdSignIn);
        passwordL= (EditText)findViewById(R.id.PasswordSignIn);

        loginButton=(Button)findViewById(R.id.loginButton);

        final List<Integer> radioButtonList = Arrays.asList(
                R.id.admin,
                R.id.team,
                R.id.player
        );

        rg = findViewById(R.id.signinType);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username= emailid.getText().toString();
                String password= passwordL.getText().toString();

                String type = "login";
                BackgroundWorker backgroundWorker = new BackgroundWorker(SignInPage.this);
                backgroundWorker.execute(type, username, password, "" + radioButtonList.indexOf(rg.getCheckedRadioButtonId()));

            }
        });
    }

}
