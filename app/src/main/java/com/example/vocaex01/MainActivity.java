package com.example.vocaex01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnEdit;
    Button btnQuiz;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEdit = findViewById(R.id.btnEdit);
        btnQuiz = findViewById(R.id.btnQuiz);
        btnExit = findViewById(R.id.btnExit);

        btnEdit.setOnClickListener(this);
        btnQuiz.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnEdit) {
            startActivity(new Intent(this, com.example.vocaex01.EditActivity.class));
        } else if(view.getId()==R.id.btnQuiz) {
            startActivity(new Intent(this, com.example.vocaex01.QuizActivity.class));
        } else if(view.getId()==R.id.btnExit) {
            finish();
        }
    }
}