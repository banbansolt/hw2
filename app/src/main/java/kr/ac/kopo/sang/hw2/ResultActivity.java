package kr.ac.kopo.sang.hw2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView tvResultScore;
    TextView tvGrade;

    Button btnRetry;
    Button btnRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResultScore = findViewById(R.id.tvResultScore);
        tvGrade = findViewById(R.id.tvGrade);
        btnRetry = findViewById(R.id.btnRetry);
        btnRanking = findViewById(R.id.btnRanking);

        int score = getIntent().getIntExtra("score", 0);
        String nickname = getIntent().getStringExtra("nickname");

        if (nickname == null || nickname.trim().isEmpty()) {
            SharedPreferences tempSp = getSharedPreferences("QUIZ_DATA", MODE_PRIVATE);
            nickname = tempSp.getString("NICKNAME", "플레이어");
        }

        tvResultScore.setText("점수 : " + score);

        // ===== 등급 계산 =====
        String grade;
        if (score >= 90) grade = "S";
        else if (score >= 80) grade = "A";
        else if (score >= 70) grade = "B";
        else if (score >= 60) grade = "C";
        else grade = "F";

        tvGrade.setText("등급 : " + grade);

        // ===== SharedPreferences 저장소 열기 =====
        SharedPreferences sp = getSharedPreferences("QUIZ_DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        // 🚨 [강제 청소 안전장치]
        // 기존 랭킹 데이터에 자바 코드 기밀 문서가 들어가 꼬여있다면 강제로 리셋시킵니다.
        String oldRank = sp.getString("RANK_LIST", "");
        if (oldRank.contains("package") || oldRank.contains("import") || oldRank.contains("Activity")) {
            oldRank = ""; // 과거의 꼬인 쓰레기 데이터를 깨끗하게 비웁니다.
        }

        // ===== 새 랭킹 데이터 안전하게 누적 추가 =====
        String newRank = nickname + ":" + score;
        String updatedRank;

        if (oldRank == null || oldRank.isEmpty()) {
            updatedRank = newRank;
        } else {
            updatedRank = oldRank + "," + newRank;
        }

        // ===== 데이터 저장 반영 =====
        int best = sp.getInt("BEST_SCORE", 0);
        int count = sp.getInt("PLAY_COUNT", 0);

        if (score > best) {
            best = score;
        }

        editor.putInt("BEST_SCORE", best);
        editor.putInt("PLAY_COUNT", count + 1);
        editor.putString("RANK_LIST", updatedRank); // 깨끗해진 데이터 저장!
        editor.apply();

        // ===== 버튼 이벤트 설정 =====
        btnRetry.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        btnRanking.setOnClickListener(v -> {
            Intent intent = new Intent(this, RankingActivity.class);
            startActivity(intent);
        });
    }
}