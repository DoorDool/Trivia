package com.example.dorin.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class HighscoresActivity extends AppCompatActivity implements HighscoresHelper.Callback {

    ArrayList<Score> highscoresList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        Intent intent = getIntent();
        HighscoresHelper helper = new HighscoresHelper(this);
        helper.getScore(this);

    }

    @Override
    public void gotScore(ArrayList<Score> highscoresList) {
        this.highscoresList = highscoresList;
        ListView highscores = findViewById(R.id.scores);
        HighscoresAdapter adapter = new HighscoresAdapter(this, highscoresList);
        highscores.setAdapter(adapter);
    }


    @Override
    public void gotScoreError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(HighscoresActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
