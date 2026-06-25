package kr.ac.kopo.sang.hw2;

import android.content.Intent;
import android.content.SharedPreferences; // ⭐️ SharedPreferences 임포트 추가
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    TextView tvQuestion;
    TextView tvTimer;
    TextView tvScore;

    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;

    RadioGroup radioGroup;

    Button btnNext;
    Button btnHint;

    ProgressBar progressBar;

    ArrayList<Question> questions;

    int currentIndex = 0;
    int score = 0;

    CountDownTimer timer;

    String nickname;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        nickname = getIntent().getStringExtra("nickname");
        category = getIntent().getStringExtra("category");

        // ⭐️ [코드 추가] 닉네임이 정상적으로 들어왔다면, 데이터 누락 방지를 위해
        // 디바이스 내 로컬 저장소("QUIZ_DATA")의 "NICKNAME" 키값에 강제로 확실히 박아둡니다.
        if (nickname != null && !nickname.trim().isEmpty()) {
            SharedPreferences sp = getSharedPreferences("QUIZ_DATA", MODE_PRIVATE);
            sp.edit().putString("NICKNAME", nickname).apply();
        }

        tvQuestion = findViewById(R.id.tvQuestion);
        tvTimer = findViewById(R.id.tvTimer);
        tvScore = findViewById(R.id.tvScore);

        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);

        radioGroup = findViewById(R.id.radioGroup);
        btnNext = findViewById(R.id.btnNext);
        btnHint = findViewById(R.id.btnHint);

        progressBar = findViewById(R.id.progressBar);

        loadQuestions();

        if (questions != null && !questions.isEmpty()) {
            progressBar.setMax(questions.size());
            showQuestion();
        }

        btnNext.setOnClickListener(v -> checkAnswer());

        // 힌트 버튼 클릭 시 진짜 힌트 텍스트 노출
        btnHint.setOnClickListener(v -> {
            if (questions != null && currentIndex < questions.size()) {
                Question q = questions.get(currentIndex);
                String hintText = q.getHint();

                if (hintText != null && !hintText.isEmpty()) {
                    Toast.makeText(this, "💡 힌트: " + hintText, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "💡 이 문제에는 등록된 힌트가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadQuestions(){
        if (category == null) category = "GAME";

        switch (category){
            case "AI":
                questions = QuestionRepository.getAIQuestions();
                break;
            case "SCIENCE":
                questions = QuestionRepository.getScienceQuestions();
                break;
            case "HISTORY":
                questions = QuestionRepository.getHistoryQuestions();
                break;
            default:
                questions = QuestionRepository.getGameQuestions();
        }
    }

    private void showQuestion(){
        Question q = questions.get(currentIndex);

        tvQuestion.setText(q.getQuestion());

        rb1.setEnabled(true);
        rb2.setEnabled(true);
        rb3.setEnabled(true);
        rb4.setEnabled(true);

        rb1.setText(q.getOption1());
        rb2.setText(q.getOption2());
        rb3.setText(q.getOption3());
        rb4.setText(q.getOption4());

        tvScore.setText("점수 : " + score);
        progressBar.setProgress(currentIndex + 1);
        radioGroup.clearCheck();

        startTimer();
    }

    private void checkAnswer(){
        if(timer != null)
            timer.cancel();

        int selected = -1;

        if(rb1.isChecked()) selected = 1;
        if(rb2.isChecked()) selected = 2;
        if(rb3.isChecked()) selected = 3;
        if(rb4.isChecked()) selected = 4;

        if(selected == questions.get(currentIndex).getAnswer()){
            score += 20;
        }

        currentIndex++;

        if(currentIndex < questions.size()){
            showQuestion();
        }else {
            // ⭐ [체크 구역 1] 인텐트에 정확히 닉네임 텍스트 변수만 담아 전송
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("nickname", nickname);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    private void startTimer(){
        if (timer != null) {
            timer.cancel();
        }

        timer = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("남은 시간 : " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                currentIndex++;

                if(currentIndex < questions.size()){
                    showQuestion();
                }else {
                    // ⭐ [체크 구역 2] 인텐트에 정확히 닉네임 텍스트 변수만 담아 전송
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("nickname", nickname);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}