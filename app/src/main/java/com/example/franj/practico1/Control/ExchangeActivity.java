package com.example.franj.practico1.Control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franj.practico1.R;

public class ExchangeActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    private int score,charge;
    private CheckBox cb_Article0, cb_Article1, cb_Article2, cb_Article3, cb_Article4;
    private Button btn_Accept,btn_Cancel;
    private TextView txt_UserScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        intent = this.getIntent();
        score = intent.getExtras().getInt("score");
        txt_UserScore = findViewById(R.id.txt_UserScore);
        txt_UserScore.setText(getString(R.string.txt_UserScore)+score);
        cb_Article0 = findViewById(R.id.cb_Article0);
        cb_Article1 = findViewById(R.id.cb_Article1);
        cb_Article2 = findViewById(R.id.cb_Article2);
        cb_Article3 = findViewById(R.id.cb_Article3);
        cb_Article4 = findViewById(R.id.cb_Article4);
        btn_Accept=findViewById(R.id.btn_Accept);
        btn_Accept.setOnClickListener(this);
        btn_Cancel=findViewById(R.id.btn_Cancel);
        btn_Cancel.setOnClickListener(this);

    }

    public void getCharge(){
        if (cb_Article0.isChecked()){
            charge+=20;
        }
        if (cb_Article1.isChecked()){
            charge+=30;
        }
        if (cb_Article2.isChecked()){
            charge+=40;
        }
        if (cb_Article3.isChecked()){
            charge+=80;
        }
        if (cb_Article4.isChecked()){
            charge+=100;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Accept:
                getCharge();
                if (score<charge){
                    Toast.makeText(ExchangeActivity.this,getString(R.string.notPurchase), Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(ExchangeActivity.this,getString(R.string.purchase), Toast.LENGTH_LONG).show();
                    intent.putExtra("newScore",score-charge);
                    setResult(RESULT_OK,intent);
                    finish();
                }
                break;
            case R.id.btn_Cancel:
                setResult(RESULT_CANCELED,intent);
                finish();
                break;
        }

    }
}
