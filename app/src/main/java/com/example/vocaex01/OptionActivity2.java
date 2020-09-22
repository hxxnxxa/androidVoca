package com.example.vocaex01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

public class OptionActivity2 extends AppCompatActivity {
    RadioButton korRb;
    RadioButton engRb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        korRb = findViewById(R.id.kor_rb);
        engRb = findViewById(R.id.eng_rb);

        boolean isEng = getIntent().getBooleanExtra("isEng", false);
        engRb.setChecked(isEng);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result= engRb.isChecked()?0:-1;
                Log.d("hwa","result: "+result);

                setResult(result);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("hwa","onDestroy");
    }
}