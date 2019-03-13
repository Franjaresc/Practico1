package com.example.franj.practico1.Control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franj.practico1.Model.Questions;
import com.example.franj.practico1.R;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txt_Operation;
    private Button btn_Accept,btn_Cancel;
    Spinner spinner;
    private Intent intent;
    private int level;
    private Questions questions;
    private double answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        intent=getIntent();
        level=intent.getExtras().getInt("level");
        btn_Accept=findViewById(R.id.btn_Accept);
        btn_Accept.setOnClickListener(this);
        btn_Cancel=findViewById(R.id.btn_Cancel);
        btn_Cancel.setOnClickListener(this);
        questions=new Questions(level);
        txt_Operation = findViewById(R.id.txt_Operation);
        spinner=findViewById(R.id.spinner);
        ArrayList<Double> answers = questions.generateAnswers();
        answer = answers.get(0);
        Collections.shuffle(answers);
        ArrayAdapter<Double> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, answers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        txt_Operation.setText(getString(R.string.txt_Operation) +questions.getTxt_Operation());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Accept:
                if (answer==Double.parseDouble(spinner.getSelectedItem().toString())){
                    Toast.makeText(QuestionsActivity.this,getString(R.string.correct), Toast.LENGTH_LONG).show();
                    if (level==0){
                        intent.putExtra("newScore",5);
                    }
                    else{
                        intent.putExtra("newScore",1);
                    }
                }
                else {
                    Toast.makeText(QuestionsActivity.this,getString(R.string.wrong), Toast.LENGTH_LONG).show();
                    intent.putExtra("newScore",0);
                }
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.btn_Cancel:
                setResult(RESULT_CANCELED,intent);
                finish();
                break;
        }
    }
}
