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

        tvResultScore = findViewById(R.id.tvResultScore);
        tvGrade = findViewById(R.id.tvGrade);
        btnRetry = findViewById(R.id.btnRetry);
        btnRanking = findViewById(R.id.btnRanking);

        int score = getIntent().getIntExtra("score", 0);

        tvResultScore.setText("점수 : " + score);

        // ===== 등급 계산 =====
        String grade;
        if (score >= 90) grade = "S";
        else if (score >= 80) grade = "A";
        else if (score >= 70) grade = "B";
        else if (score >= 60) grade = "C";
        else grade = "F";

        tvGrade.setText("등급 : " + grade);

        // ===== SharedPreferences =====
        SharedPreferences sp =
                getSharedPreferences("QUIZ_DATA", MODE_PRIVATE);

        String nickname = sp.getString("NICKNAME", "플레이어");

        int best = sp.getInt("BEST_SCORE", 0);
        int count = sp.getInt("PLAY_COUNT", 0);

        // ===== 최고점 갱신 =====
        if (score > best) {
            best = score;
        }

        // ===== 기존 RANK_LIST 가져오기 =====
        String oldRank = sp.getString("RANK_LIST", "");

        // ===== 새 랭킹 데이터 추가 =====
        String newRank = nickname + ":" + score + ",";

        String updatedRank = oldRank + newRank;

        // ===== 저장 =====
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("BEST_SCORE", best);
        editor.putInt("PLAY_COUNT", count + 1);
        editor.putString("RANK_LIST", updatedRank); // ⭐ 핵심 추가
        editor.apply();

        // ===== 버튼 =====
        btnRetry.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        btnRanking.setOnClickListener(v -> {
            Intent intent = new Intent(this, RankingActivity.class);
            startActivity(intent);
        });
    }
}