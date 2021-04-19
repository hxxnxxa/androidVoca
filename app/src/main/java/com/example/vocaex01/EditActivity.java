package com.example.vocaex01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText etEng;
    EditText etKor;
    Button btnAdd;
    ListView lvContent;

    int idx = 0;

    boolean isEdit;

    ArrayList<String> arr = new ArrayList<>();

    ArrayAdapter adapter;
    //ArrayList객체를 ListView에 띄우기 위해 ArrayAdapter 선언

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etEng = findViewById(R.id.etEng);
        etKor = findViewById(R.id.etKor);
        btnAdd = findViewById(R.id.btnAdd);
        lvContent = findViewById(R.id.lvContent);

        Storage.vocaArr.add(new Voca("incense", "격분시키다"));
        Storage.vocaArr.add(new Voca("inundate", "범람하다"));
        Storage.vocaArr.add(new Voca("jeopardy", "위험"));
        Storage.vocaArr.add(new Voca("magnify", "확대하다"));
        Storage.vocaArr.add(new Voca("municipal", "시의"));

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr);
        //adapter객체에 ArrayList 연결
        //android.R.layout.simple_list_item_1 : 한 줄에 하나의 아이템(Voca객체)만 보여주는 레이아웃 파일
        //여기서의 this는 데이터가 저장되어있는 ArrayList 객체

        lvContent.setAdapter(adapter);
        //ListView에 adapter객체 연결

        btnAdd.setOnClickListener(this);

        lvContent.setOnItemClickListener(this);
        //ListView 객체의 특정 아이템 클릭 시 이벤트 발생 처리(수정기능 동작)
    }

    private void show() {
        arr.clear();
        for (int i = 0; i <Storage.vocaArr.size() ; i++) {
            Voca  v = Storage.vocaArr.get(i);
            arr.add(v.eng + " : " + v.kor);
        }

        //adapter 객체에 변경 내용을 반영시켜줘야 에러가 발생하지 않는다.
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if(isEdit) {
            String eng = etEng.getText().toString().trim();
            String kor = etKor.getText().toString().trim();
            etEng.setText("");
            etKor.setText("");
            Storage.vocaArr.get(idx).eng = eng;
            Storage.vocaArr.get(idx).kor = kor;

            isEdit = false;
        } else {
            String eng = etEng.getText().toString();
            String kor = etKor.getText().toString();
            Voca v = new Voca(eng, kor);
            Storage.vocaArr.add(v);
            etEng.setText("");
            etKor.setText("");
        }
        show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int idx, long l) {
        this.idx = idx;
        final Voca temp = Storage.vocaArr.get(idx);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("단어 선택");
        builder.setMessage(temp.eng + " : " + temp.kor);
        builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Storage.vocaArr.remove(idx);
                show();
            }
        });
        builder.setNegativeButton("수정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                etEng.setText(temp.eng);
                etKor.setText(temp.kor);
                btnAdd.setText("수정");
                isEdit = true;
            }
        });
        builder.setCancelable(true);
        builder.create().show();
    }
}
