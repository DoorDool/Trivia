package com.example.dorin.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuestionActivity extends AppCompatActivity implements QuestionHelper.Callback {

    TextView text;
    Button answer_1, answer_2, answer_3, answer_4;
    int points;
    int question_number;
    String name;
    ArrayList<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent intentOld = getIntent();
        String name_intent = intentOld.getStringExtra("name");
        name = name_intent;
        Log.i("name", "1234" + name);


        text = findViewById(R.id.question);
        answer_1 = findViewById(R.id.answer1);
        answer_2 = findViewById(R.id.answer2);
        answer_3 = findViewById(R.id.answer3);
        answer_4 = findViewById(R.id.answer4);

        // method onClick on each button
        answer_1.setOnClickListener(new Clicked());
        answer_2.setOnClickListener(new Clicked());
        answer_3.setOnClickListener(new Clicked());
        answer_4.setOnClickListener(new Clicked());

        // make new questionHelper
        QuestionHelper questionHelper = new QuestionHelper(this);
        // get menu of a category
        questionHelper.getQuestion(this);
    }

    @Override
    public void gotQuestion(ArrayList<Question> questionList) {

        this.questionList = questionList;
        text.setText(questionList.get(0).getQuestion());

        ArrayList<String> answerGuess = new ArrayList<String>(Arrays.asList(questionList.get(0).getIncorrect().get(0),
                questionList.get(0).getIncorrect().get(1), questionList.get(0).getIncorrect().get(2), questionList.get(0).getAnswer()));
        Collections.shuffle(answerGuess);

        answer_1.setText(answerGuess.get(0));
        answer_2.setText(answerGuess.get(1));
        answer_3.setText(answerGuess.get(2));
        answer_4.setText(answerGuess.get(3));
    }

    // catch error and give message to user
    @Override
    public void gotQuestionError(String message) {
        Toast.makeText(this, "gotQuestionError", Toast.LENGTH_LONG).show();
    }


    private class Clicked implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            String correct_answer = questionList.get(0).getAnswer();
            TextView answer_guess = (TextView) v;
            if (answer_guess.getText() == correct_answer) {
                points += 1;
                question_number += 1;
                String message = "Right";
                Toast.makeText(QuestionActivity.this, message, Toast.LENGTH_LONG).show();
            }
            else {
                question_number += 1;
                String message = "Wrong";
                Toast.makeText(QuestionActivity.this, message, Toast.LENGTH_LONG).show();
            }

            if (question_number == 10) {


                //Score score = new Score(name, String.valueOf(points));
                String score = String.valueOf(points);

                HighscoresPost post = new HighscoresPost(QuestionActivity.this);
                post.postHighscores(QuestionActivity.this, score, name);

                Intent intent = new Intent(QuestionActivity.this, HighscoresActivity.class);
                startActivity(intent);
            }
            else {
                QuestionHelper helper = new QuestionHelper(getApplicationContext());
                helper.getQuestion(QuestionActivity.this);
            }

        }
    }



}
