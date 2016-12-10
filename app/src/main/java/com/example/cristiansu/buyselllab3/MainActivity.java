package com.example.cristiansu.buyselllab3;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonSubmit();
            }
        });

        Button buttonGoToListing = (Button) findViewById(R.id.buttonGoToList);
        buttonGoToListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonGoToListing();
            }
        });
    }

    protected void onClickButtonSubmit() {
        EditText inputName = (EditText) this.findViewById(R.id.inputNameLogin);
        EditText inputPassword = (EditText) findViewById(R.id.inputPasswordLogin);
        EditText inputEmail = (EditText) findViewById(R.id.inputEmail);
        EditText inputPhoneNumber = (EditText) findViewById(R.id.inputPhoneNumber);
        EditText inputPostalAddress = (EditText) findViewById(R.id.inputPostalAddress);

        HashMap<String, String> inputValues = new HashMap<>();
        inputValues.put("Name: ", String.valueOf(inputName.getText()));
        inputValues.put("Password: ", String.valueOf(inputPassword.getText()));
        inputValues.put("Email: ", String.valueOf(inputEmail.getText()));
        inputValues.put("Phone number: ", String.valueOf(inputPhoneNumber.getText()));
        inputValues.put("Postal address: ", String.valueOf(inputPostalAddress.getText()));

        String textBody = BuildString(inputValues);

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto:",String.valueOf(inputEmail.getText()), null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Letting you know");
        emailIntent.putExtra(Intent.EXTRA_TEXT, textBody);
        emailIntent.setType("message/rfc822");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onClickButtonGoToListing() {
        Intent goToListingIntent = new Intent(MainActivity.this, ListingActivity.class);
        startActivity(goToListingIntent);
    }

    @NonNull
    private String BuildString(HashMap<String, String> inputValues){
        StringBuilder stringBuilder = new StringBuilder();
        for(String key : inputValues.keySet()){
            stringBuilder.append(key);
            stringBuilder.append(inputValues.get(key));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
