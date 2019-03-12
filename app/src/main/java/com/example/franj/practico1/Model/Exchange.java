package com.example.franj.practico1.Model;

import android.widget.CheckBox;

import java.util.ArrayList;

public class Exchange {

    private int score,charge;

    public Exchange(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Exchange setScore(int score) {
        this.score = score;
        return this;
    }

    public int getCharge(ArrayList<CheckBox> products) {
        for (int i = 0;i<products.size();i++){
            if (i==0&&products.get(i).isChecked()==true){

            }
            if (i==1&&products.get(i).isChecked()==true){

            }
            if (i==2&&products.get(i).isChecked()==true){

            }
            if (i==3&&products.get(i).isChecked()==true){

            }
            if (i==4&&products.get(i).isChecked()==true){

            }
        }

        return charge;
    }

    public Exchange setCharge(int charge) {
        this.charge = charge;
        return this;
    }



}
