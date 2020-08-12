package ir.madeinlobby.milmail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view) {
        EditText password = findViewById(R.id.inputPassword);
        EditText username = findViewById(R.id.inputUsername);
        String passwordText = password.getText().toString();
        String usernameText = username.getText().toString();
        Gonnect.getData("http://spneshaei.com/mil/getEmails.php?username=" + usernameText + "&password=" + passwordText, this, new Gonnect.ResponseSuccessListener() {
            @Override
            public void responseRecieved(final String response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(response.equals("invalid-user")){
                            Toast.makeText(MainActivity.this, "invalid user", Toast.LENGTH_LONG).show();
                        }else{
                            ArrayList<Email> emails = new Gson().fromJson(response,new TypeToken<ArrayList<Email>>(){}.getType());
                            Email.getAllEmails().clear();
                            Email.getAllEmails().addAll(emails);

                            Intent intent = new Intent(MainActivity.this, MailBoxActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        }, new Gonnect.ResponseFailureListener() {
            @Override
            public void responseFailed(IOException exception) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "error in loading", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public void compose(View view) {
        Intent intent = new Intent(this, ComposeActivity.class);
        startActivity(intent);
    }
}