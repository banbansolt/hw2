package kr.ac.kopo.sang.hw2;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CategoryActivity extends AppCompatActivity {

    Button btnAI;
    Button btnScience;
    Button btnHistory;
    Button btnGame;

    String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        nickname =
                getIntent().getStringExtra("nickname");

        btnAI = findViewById(R.id.btnAI);
        btnScience = findViewById(R.id.btnScience);
        btnHistory = findViewById(R.id.btnHistory);
        btnGame = findViewById(R.id.btnGame);

        btnAI.setOnClickListener(v ->
                startQuiz("AI"));

        btnScience.setOnClickListener(v ->
                startQuiz("SCIENCE"));

        btnHistory.setOnClickListener(v ->
                startQuiz("HISTORY"));

        btnGame.setOnClickListener(v ->
                startQuiz("GAME"));
    }

    private void startQuiz(String category){

        Intent intent =
                new Intent(this,
                        QuizActivity.class);

        intent.putExtra("nickname", nickname);
        intent.putExtra("category", category);

        startActivity(intent);
    }
}