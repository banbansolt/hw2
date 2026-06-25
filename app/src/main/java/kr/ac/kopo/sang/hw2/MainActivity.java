package kr.ac.kopo.sang.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    Button btnRanking;
    Button btnInfo;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnRanking = findViewById(R.id.btnRanking);
        btnInfo = findViewById(R.id.btnInfo);
        btnExit = findViewById(R.id.btnExit);

        btnStart.setOnClickListener(v -> {
            Intent intent =
                    new Intent(MainActivity.this,
                            NicknameActivity.class);
            startActivity(intent);
        });

        btnRanking.setOnClickListener(v -> {
            Intent intent =
                    new Intent(MainActivity.this,
                            RankingActivity.class);
            startActivity(intent);
        });

        btnInfo.setOnClickListener(v -> {
            Intent intent =
                    new Intent(MainActivity.this,
                            InfoActivity.class);
            startActivity(intent);
        });

        btnExit.setOnClickListener(v -> {
            finish();
        });
    }
}