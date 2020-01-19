package com.example.language;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    String polish = "polish";
    String japanese = "japanese";
    String selection = "You selected ";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);
        sharedPreferences = this.getSharedPreferences("com.example.language", Context.MODE_PRIVATE);
        String langSelected = sharedPreferences.getString("language", "Error");

        if(langSelected.equals("Error")) {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_btn_speak_now)
                    .setTitle("Select language")
                    .setMessage("Please select your langauage")
                    .setPositiveButton(polish, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            LanguageSelected(polish);
                            Toast.makeText(MainActivity.this, selection + polish, Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton(japanese, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            LanguageSelected(japanese);
                            Toast.makeText(MainActivity.this, selection + japanese, Toast.LENGTH_LONG).show();
                        }
                    }).show();
        }
        else{
            textView.setText(langSelected);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.language_selector,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.polish:

                LanguageSelected(polish);
                return true;
            case R.id.japanese:

                LanguageSelected(japanese);
                return true;
            default:
                return true;
        }
    }
    public void LanguageSelected(String lang){
        textView.setText(lang);
        //sharedPreferences = this.getSharedPreferences("com.example.language", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("language", lang).apply();
    }
}