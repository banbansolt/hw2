package kr.ac.kopo.sang.hw2;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RankingActivity extends AppCompatActivity {

    TextView tvBestScore;
    TextView tvPlayCount;

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        tvBestScore = findViewById(R.id.tvBestScore);
        tvPlayCount = findViewById(R.id.tvPlayCount);
        btnBack = findViewById(R.id.btnBack);

        SharedPreferences sp =
                getSharedPreferences(
                        "QUIZ_DATA",
                        MODE_PRIVATE);

        int bestScore =
                sp.getInt(
                        "BEST_SCORE",
                        0);

        int playCount =
                sp.getInt(
                        "PLAY_COUNT",
                        0);

        tvBestScore.setText(
                "최고 점수 : " + bestScore);

        tvPlayCount.setText(
                "플레이 횟수 : " + playCount);

        btnBack.setOnClickListener(v -> finish());
    }
}