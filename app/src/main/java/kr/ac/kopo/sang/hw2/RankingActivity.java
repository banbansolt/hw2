package kr.ac.kopo.sang.hw2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// ⭐️ MaterialButton 타입 불일치 해결을 위해 임포트 추가
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RankingActivity extends AppCompatActivity {

    TextView tvRank1;
    TextView tvRank2;
    TextView tvRank3;
    TextView tvRank4;
    TextView tvRank5;

    // ⭐️ XML의 OutlinedButton 스타일과 호환되도록 MaterialButton으로 타입 수정!
    MaterialButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        tvRank1 = findViewById(R.id.tvRank1);
        tvRank2 = findViewById(R.id.tvRank2);
        tvRank3 = findViewById(R.id.tvRank3);
        tvRank4 = findViewById(R.id.tvRank4);
        tvRank5 = findViewById(R.id.tvRank5);

        btnBack = findViewById(R.id.btnBack);

        SharedPreferences sp =
                getSharedPreferences("QUIZ_DATA", MODE_PRIVATE);

        String data = sp.getString("RANK_LIST", "");

        // ===== 데이터 없을 때 처리 =====
        if (data == null || data.isEmpty()) {
            tvRank1.setText("🥇 1위 없음");
            tvRank2.setText("");
            tvRank3.setText("");
            tvRank4.setText("");
            tvRank5.setText("");

            btnBack.setOnClickListener(v -> finish());
            return;
        }

        // ===== 데이터 파싱 =====
        String[] list = data.split(",");

        ArrayList<String[]> ranks = new ArrayList<>();

        for (String item : list) {
            if (item.contains(":")) {
                String[] split = item.split(":");

                if (split.length == 2) {
                    ranks.add(new String[]{split[0], split[1]});
                }
            }
        }

        // ===== 점수 기준 내림차순 정렬 =====
        Collections.sort(ranks, new Comparator<String[]>() {
            @Override
            public int compare(String[] a, String[] b) {
                try {
                    // 예외 처리 추가: 안전하게 숫자로 변환하여 정렬
                    return Integer.parseInt(b[1].trim()) - Integer.parseInt(a[1].trim());
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
        });

        // ===== 초기화 =====
        tvRank1.setText("");
        tvRank2.setText("");
        tvRank3.setText("");
        tvRank4.setText("");
        tvRank5.setText("");

        // ===== 출력 (TOP5) =====
        for (int i = 0; i < ranks.size() && i < 5; i++) {

            String name = ranks.get(i)[0];
            String score = ranks.get(i)[1];

            String text;

            if (i == 0)
                text = "1위 " + name + " - " + score + "점";
            else if (i == 1)
                text = "2위 " + name + " - " + score + "점";
            else if (i == 2)
                text = "3위 " + name + " - " + score + "점";
            else
                text = (i + 1) + "위 " + name + " - " + score + "점";

            if (i == 0) tvRank1.setText(text);
            if (i == 1) tvRank2.setText(text);
            if (i == 2) tvRank3.setText(text);
            if (i == 3) tvRank4.setText(text);
            if (i == 4) tvRank5.setText(text);
        }

        btnBack.setOnClickListener(v -> finish());
    }
}