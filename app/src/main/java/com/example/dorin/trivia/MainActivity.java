package com.example.dorin.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGameClicked (View view) {

        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        EditText input_name = findViewById(R.id.name);
        name = input_name.getText().toString();
        Log.i("string", "name is 1234: " + name);
        intent.putExtra("name", name);

        startActivity(intent);
    }

    public void highscoresClicked (View view) {

        Intent intent = new Intent(MainActivity.this, HighscoresActivity.class);
        startActivity(intent);
    }

}
