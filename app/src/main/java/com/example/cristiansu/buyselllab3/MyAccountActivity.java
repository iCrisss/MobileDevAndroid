package com.example.cristiansu.buyselllab3;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.User;

public class MyAccountActivity extends AppCompatActivity {

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            user = (User) bundle.get("user");
        }

        Button modifyAccount = (Button) findViewById(R.id.buttonModifyAccount);
        modifyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickModifyAccount();
            }
        });

        Button myItems = (Button) findViewById(R.id.buttonViewMyItems);
        myItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickMyItems();
            }
        });

        Button browseItems = (Button) findViewById(R.id.buttonBrowseItems);
        browseItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBrowseItems();
            }
        });

        //TODO Add dialog
        Button logOut = (Button) findViewById(R.id.buttonLogOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogOut();
            }
        });
    }


    protected void onClickModifyAccount(){
        Intent intent = new Intent(MyAccountActivity.this, ModifyAccountActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    protected void onClickMyItems() {
        Intent intent = new Intent(MyAccountActivity.this, MyItemsActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    protected void onClickBrowseItems() {
        Intent intent = new Intent(MyAccountActivity.this, ListingActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    protected void onClickLogOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyAccountActivity.this);

        builder.setMessage("Are you sure you wish to leave?")
                .setTitle("Log Out");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(MyAccountActivity.this, LoginActivity.class);
                startActivity(intent);
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
}
