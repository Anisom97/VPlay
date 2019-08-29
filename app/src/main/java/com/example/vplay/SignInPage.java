package com.example.vplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

                List<NameValuePair> entity = new ArrayList<>();

                entity.add(new BasicNameValuePair("user_name", username));
                entity.add(new BasicNameValuePair("password", password));
                entity.add(new BasicNameValuePair("table", tableNames[radioButtonList.indexOf(rg.getCheckedRadioButtonId())]));

                NetworkQueryTask networkQueryTask = new NetworkQueryTask(Constants.TEST_URL + "/" + "signin.php", "POST", new OnNetworkQueryCompletionListener() {
                    @Override
                    public void onNetworkQueryCompleted(@NonNull NetworkResponse networkResponse) {
                        if (networkResponse.isError())
                            Toast.makeText(SignInPage.this, networkResponse.getErrorMessage(), Toast.LENGTH_LONG).show();
                        else Toast.makeText(SignInPage.this, "Success", Toast.LENGTH_LONG).show();
                    }
                }, entity);
                networkQueryTask.execute();
            }
        });
    }
}
