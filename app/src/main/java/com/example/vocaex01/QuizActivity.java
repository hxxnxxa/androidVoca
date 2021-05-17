package com.example.vocaex01;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    //문제 변수
    int idx;

    //점수 변수
    int score;

    //Swap 사용을 위해 boolean 연산자 선언
    boolean isEng;

    //RadioButton 체크 여부 확인을 위해 boolean 연산자 선언
    boolean isChk;

    //알림 글
    TextView tvNotice;

    //실행화면
    RelativeLayout layoutQUiz;
    TextView tvScore;
    ProgressBar barIdx;
    TextView tvIdx;
    TextView tvTime;
    Button btnSwap;
    TextView tvQuiz;
    EditText etAnswer;
    Button btnSubmit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //저장된 단어가 없는경우 알림창
        tvNotice = findViewById(R.id.tvNotice);

        //정상 실행 화면
        layoutQUiz = findViewById(R.id.layoutQuiz);
        tvScore = findViewById(R.id.tvScore);
        barIdx = findViewById(R.id.barIdx);
        tvIdx = findViewById(R.id.tvIdx);
        btnSwap = findViewById(R.id.btnSwap);
        tvQuiz = findViewById(R.id.tvQuiz);
        etAnswer = findViewById(R.id.etAnswer);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvTime = findViewById(R.id.tvTime);

        //초기에는 문제가 eng로 나와야하니까 true
        isEng = true;

        //저장된 화면이 없을 경우 알림창 띄우기
        if (Storage.vocaArr.size() < 1) {
            tvNotice.setVisibility(View.VISIBLE);
            layoutQUiz.setVisibility(View.INVISIBLE);
        } else {
            init();
            tvNotice.setVisibility(View.INVISIBLE);
            layoutQUiz.setVisibility(View.VISIBLE);

            barIdx.setMax(Storage.vocaArr.size());

            btnSwap.setOnClickListener(this);
            btnSubmit.setOnClickListener(this);
            //findViewById(R.id.btnAct).setOnClickListener(this);

            time = 10;
            tvTime.setText(String.valueOf(time));
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    }

    private void init() {
        idx = 0;
        score = 0;
        showQuiz();
    }

    int time = 5;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            time--;
            if (time > 0) {
                tvTime.setText(String.valueOf(time));
                handler.sendEmptyMessageDelayed(0, 1000);
            } else {
                nextQuiz();
            }
        }
    };

    private void showQuiz() {
        Voca voca = Storage.vocaArr.get(idx);

        if (isEng) {
            tvQuiz.setText(voca.eng);
        } else {
            tvQuiz.setText(voca.kor);
        }

        tvScore.setText(score + "점");
        tvIdx.setText((idx + 1) + "/" + Storage.vocaArr.size());
        barIdx.setProgress(idx + 1);
    }

    private void nextQuiz() {
        idx++;

        if (idx == Storage.vocaArr.size()) {
            //모든 문제를 풀었을 경우
            tvScore.setText(score + "점");
            Toast.makeText(this, "완료", Toast.LENGTH_SHORT).show();
        } else {
            showQuiz();
            time = 10;
            tvTime.setText(String.valueOf(time));
            handler.removeMessages(0);
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Log.d("heu","result22: "+resultCode);
            if (resultCode == 0) {
                isEng = true;
            } else {
                isEng = false;
            }
            init();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("heu","onResume");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSubmit) {
            Voca voca = Storage.vocaArr.get(idx);
            String input = etAnswer.getText().toString().trim();
            etAnswer.setText("");
            String answer = "";
            answer = isEng ? voca.kor : voca.eng;
            if (input.equals(answer)) {
                score += 20;
                nextQuiz();
            } else {
                //오답
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("오답");
                builder.setMessage("다음 문제로 넘어가시겠습니까?");
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nextQuiz();
                    }
                });
                builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showQuiz();
                    }
                });
                builder.setCancelable(false);
                builder.create().show();
            }
        } else if (view.getId() == R.id.btnSwap) {

            /** 내가 원하는 레이아웃 적용 **/
            LayoutInflater lnf = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = lnf.inflate(R.layout.item, null, false);
            RadioButton korRb = v.findViewById(R.id.kor_rb);
            final RadioButton engRb = v.findViewById(R.id.eng_rb);
            engRb.setChecked(isEng);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("한영 모드 전환!");
            builder.setView(v);
            builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(isEng != engRb.isChecked()) {
                        isEng = engRb.isChecked();
                        init();
                    }
                }
            });
            builder.create().show();
        }
        /* else if(view.getId()==R.id.btnAct) {
            Intent intent = new Intent(this, com.example.vocaex01.OptionActivity2.class);
            intent.putExtra("isEng", isEng);
            startActivityForResult(intent, 1000);
            }
         */
    }
}