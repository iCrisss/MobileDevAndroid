package com.example.cristiansu.buyselllab3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cristiansu.buyselllab3.Repository.DAO.UsersDataSource;
import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.User;

public class LoginActivity extends AppCompatActivity {

    private UsersDataSource usersDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.buttonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonLogin();
            }
        });

        Button registerButton = (Button) findViewById(R.id.buttonRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonRegister();
            }
        });
    }

    protected void onClickButtonLogin() {
        usersDataSource = new UsersDataSource(this);

        EditText name = (EditText) findViewById(R.id.inputName);
        EditText password = (EditText) findViewById(R.id.inputPassword);

        try {
            usersDataSource.openReadable();
            User user = usersDataSource.getUser(name.getText().toString(), password.getText().toString());
            usersDataSource.close();
            if (user == null) {
                //show dialog
                return;
            }
            Intent intent = new Intent(LoginActivity.this, MyAccountActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }
        catch (Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

            builder.setMessage("Press Yes for registering a new account or Cancel for trying again.")
                    .setTitle("Incorrect name or password");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    onClickButtonRegister();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }
    }

    protected void onClickButtonRegister() {
        //Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        //startActivity(intent);

        usersDataSource = new UsersDataSource(this);

        EditText name = (EditText) findViewById(R.id.inputName);
        EditText password = (EditText) findViewById(R.id.inputPassword);

        try {
            usersDataSource.openWriteable();
            User user = usersDataSource.createUser(name.getText().toString(), password.getText().toString());
            usersDataSource.close();


            if (user == null) {
                return;
            }

            Intent intent = new Intent(LoginActivity.this, MyAccountActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }
        catch (Exception e) {

        }
    }


}
