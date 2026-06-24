package kr.ac.kopo.sang.hw2;




import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

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

    MaterialButton btnNext;

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

        nickname =
                getIntent().getStringExtra("nickname");

        category =
                getIntent().getStringExtra("category");

        tvQuestion = findViewById(R.id.tvQuestion);
        tvTimer = findViewById(R.id.tvTimer);
        tvScore = findViewById(R.id.tvScore);

        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);

        radioGroup =
                findViewById(R.id.radioGroup);

        btnNext = findViewById(R.id.btnNext);

        progressBar =
                findViewById(R.id.progressBar);

        loadQuestions();

        progressBar.setMax(questions.size());

        showQuestion();

        btnNext.setOnClickListener(v ->
                checkAnswer());
    }

    private void loadQuestions(){

        switch (category){

            case "AI":
                questions =
                        QuestionRepository.getAIQuestions();
                break;

            case "SCIENCE":
                questions =
                        QuestionRepository.getScienceQuestions();
                break;

            case "HISTORY":
                questions =
                        QuestionRepository.getHistoryQuestions();
                break;

            default:
                questions =
                        QuestionRepository.getGameQuestions();
        }
    }

    private void showQuestion(){

        Question q =
                questions.get(currentIndex);

        tvQuestion.setText(q.getQuestion());

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

        if(selected ==
                questions.get(currentIndex).getAnswer()){

            score += 20;
        }

        currentIndex++;

        if(currentIndex < questions.size()){

            showQuestion();

        }else{

            Intent intent =
                    new Intent(this,
                            ResultActivity.class);

            intent.putExtra("nickname",
                    nickname);

            intent.putExtra("score",
                    score);

            startActivity(intent);

            finish();
        }
    }

    private void startTimer(){

        timer =
                new CountDownTimer(
                        15000,
                        1000) {

                    @Override
                    public void onTick(
                            long millisUntilFinished) {

                        tvTimer.setText(
                                "남은 시간 : "
                                        + millisUntilFinished / 1000);
                    }

                    @Override
                    public void onFinish() {

                        currentIndex++;

                        if(currentIndex <
                                questions.size()){

                            showQuestion();

                        }else{

                            Intent intent =
                                    new Intent(
                                            QuizActivity.this,
                                            ResultActivity.class);

                            intent.putExtra(
                                    "nickname",
                                    nickname);

                            intent.putExtra(
                                    "score",
                                    score);

                            startActivity(intent);

                            finish();
                        }
                    }
                };

        timer.start();
    }
}