package kr.ac.kopo.sang.hw2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class NicknameActivity extends AppCompatActivity {

    TextInputEditText etNickname;
    MaterialButton btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);

        etNickname = findViewById(R.id.etNickname);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {

            String nickname =
                    etNickname.getText().toString().trim();

            if (nickname.isEmpty()) {
                Toast.makeText(
                        this,
                        "닉네임을 입력하세요",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // 닉네임 저장
            SharedPreferences sp =
                    getSharedPreferences(
                            "QUIZ_DATA",
                            MODE_PRIVATE);

            SharedPreferences.Editor editor =
                    sp.edit();

            editor.putString(
                    "NICKNAME",
                    nickname);

            editor.apply();

            // 카테고리 화면으로 이동
            Intent intent =
                    new Intent(
                            NicknameActivity.this,
                            CategoryActivity.class);

            intent.putExtra(
                    "nickname",
                    nickname);

            startActivity(intent);
        });
    }
}