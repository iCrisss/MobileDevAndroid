package com.example.cristiansu.buyselllab3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cristiansu.buyselllab3.Repository.DAO.ItemsDataSource;
import com.example.cristiansu.buyselllab3.Repository.DatabaseEntities.User;

public class AddItemActivity extends AppCompatActivity {


    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            user = (User) bundle.get("user");
        }

        final EditText itemName = (EditText) findViewById(R.id.inputAddItemName);
        final EditText itemPrice = (EditText) findViewById(R.id.inputAddItemPrice);

        Button confirmButton = (Button) findViewById(R.id.buttonAddItemConfirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemsDataSource itemsDataSource = new ItemsDataSource(v.getContext());
                try {
                    itemsDataSource.openWriteable();
                    itemsDataSource.createItem(user.getId(), itemName.getText().toString(), Double.parseDouble(itemPrice.getText().toString()));
                    itemsDataSource.close();
                    Intent intent = new Intent(v.getContext(), MyItemsActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
                catch (Exception e){
                    //TODO
                    Intent intent = new Intent(v.getContext(), MyItemsActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            }
        });

        Button cancelButton = (Button) findViewById(R.id.buttonAddItemCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MyItemsActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

    }
}
