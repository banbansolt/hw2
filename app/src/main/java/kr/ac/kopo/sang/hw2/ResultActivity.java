package kr.ac.kopo.sang.hw2;





import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class ResultActivity extends AppCompatActivity {

    TextView tvResultScore;
    TextView tvGrade;

    MaterialButton btnRetry;
    MaterialButton btnRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResultScore =
                findViewById(R.id.tvResultScore);

        tvGrade =
                findViewById(R.id.tvGrade);

        btnRetry =
                findViewById(R.id.btnRetry);

        btnRanking =
                findViewById(R.id.btnRanking);

        int score =
                getIntent().getIntExtra(
                        "score",
                        0);

        tvResultScore.setText(
                "점수 : " + score);

        String grade;

        if(score >= 90)
            grade = "S";
        else if(score >= 80)
            grade = "A";
        else if(score >= 70)
            grade = "B";
        else if(score >= 60)
            grade = "C";
        else
            grade = "F";

        tvGrade.setText(
                "등급 : " + grade);

        SharedPreferences sp =
                getSharedPreferences(
                        "QUIZ_DATA",
                        MODE_PRIVATE);

        int best =
                sp.getInt(
                        "BEST_SCORE",
                        0);

        if(score > best){

            sp.edit()
                    .putInt(
                            "BEST_SCORE",
                            score)
                    .apply();
        }

        int count =
                sp.getInt(
                        "PLAY_COUNT",
                        0);

        sp.edit()
                .putInt(
                        "PLAY_COUNT",
                        count + 1)
                .apply();

        btnRetry.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            this,
                            MainActivity.class);

            startActivity(intent);

            finish();
        });

        btnRanking.setOnClickListener(v -> {

            startActivity(
                    new Intent(
                            this,
                            RankingActivity.class));
        });
    }
}