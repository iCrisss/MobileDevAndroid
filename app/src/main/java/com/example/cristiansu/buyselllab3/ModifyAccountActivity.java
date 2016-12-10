package com.example.cristiansu.buyselllab3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cristiansu.buyselllab3.Repository.DAO.UsersDataSource;
import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.User;

public class ModifyAccountActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_account);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            user = (User) bundle.get("user");
        }

        Button updateAccount = (Button) findViewById(R.id.buttonUpdateAccountInfo);
        updateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpdateAccountButton();
            }
        });

        Button deleteAccount = (Button) findViewById(R.id.buttonDeleteAccount);
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDeleteAccountButton();
            }
        });

        Button backToMyAccount = (Button) findViewById(R.id.buttonBackToMyAccount);
        backToMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBackToMyAccountButton();
            }
        });
    }

    protected void onClickUpdateAccountButton() {
        UsersDataSource usersDataSource = new UsersDataSource(this);
        EditText newName = (EditText) findViewById(R.id.inputNameMyAccount);
        EditText newPassword = (EditText) findViewById(R.id.inputNewPassword);

        try{
            usersDataSource.openReadable();
            user = usersDataSource.updateUser(user, newName.getText().toString(), newPassword.getText().toString());
            usersDataSource.close();
            Intent intent = new Intent(ModifyAccountActivity.this, MyAccountActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }
        catch (Exception e) {
            //TODO
        }
    }

    protected void onClickDeleteAccountButton() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ModifyAccountActivity.this);

        builder.setMessage("Are you sure you want to delete your account?")
                .setTitle("Delete account");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                UsersDataSource usersDataSource = new UsersDataSource(ModifyAccountActivity.this);

                try{
                    usersDataSource.openReadable();
                    usersDataSource.deleteUser(user);
                    usersDataSource.close();

                    Intent intent = new Intent(ModifyAccountActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                catch (Exception e) {

                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    protected void onClickBackToMyAccountButton() {
        Intent intent = new Intent(ModifyAccountActivity.this, MyAccountActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}
