package kr.ac.kopo.sang.hw2;




import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    MaterialButton btnStart;
    MaterialButton btnRanking;
    MaterialButton btnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnRanking = findViewById(R.id.btnRanking);
        btnInfo = findViewById(R.id.btnInfo);

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
    }
}