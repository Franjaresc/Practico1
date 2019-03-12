package com.example.franj.practico1.Control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.franj.practico1.Model.Exchange;
import com.example.franj.practico1.R;

public class ExchangeActivity extends AppCompatActivity {

    private Intent intent;
    private Exchange exchange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        intent = this.getIntent();

        exchange= new Exchange(intent.getExtras().getInt("score"));

    }


}
