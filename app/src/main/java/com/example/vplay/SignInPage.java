package com.example.vplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.vplay.network.NetworkQueryTask;
import com.example.vplay.network.interfaces.OnNetworkQueryCompletionListener;
import com.example.vplay.network.models.NetworkResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignInPage extends AppCompatActivity {
    EditText emailid, passwordL;
    Button loginButton;
    RadioGroup rg;

    final String[] tableNames = {"admintest", "teamtest", "playertest"};
    final Class[] activities = {PlayerMainPage.class, TeamMainPage.class, AdminMainPage.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        emailid = findViewById(R.id.EnterIdSignIn);
        passwordL = findViewById(R.id.PasswordSignIn);

        loginButton= findViewById(R.id.loginButton);

        final List<Integer> radioButtonList = Arrays.asList(
                R.id.admin,
                R.id.team,
                R.id.player
        );

        rg = findViewById(R.id.signinType);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = emailid.getText().toString();
                String password = passwordL.getText().toString();

                final int selection = radioButtonList.indexOf(rg.getCheckedRadioButtonId());

                List<NameValuePair> entity = new ArrayList<>();

                entity.add(new BasicNameValuePair("user_name", username));
                entity.add(new BasicNameValuePair("password", password));
                entity.add(new BasicNameValuePair("table", tableNames[selection]));

                NetworkQueryTask networkQueryTask = new NetworkQueryTask(Constants.TEST_URL + "/" + "signin.php", "POST", new OnNetworkQueryCompletionListener() {
                    @Override
                    public void onNetworkQueryCompleted(@NonNull NetworkResponse networkResponse) {
                        if (networkResponse.isError())
                            Toast.makeText(SignInPage.this, networkResponse.getErrorMessage(), Toast.LENGTH_LONG).show();
                        else {
                            if ("1".equals(networkResponse.getResponseMessage().get(0))) {
                                startActivity(new Intent(SignInPage.this, activities[selection]));
                            } else {
                                Toast.makeText(SignInPage.this, "Invalid username/password", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }, entity);
                networkQueryTask.execute();
            }
        });
    }
}
