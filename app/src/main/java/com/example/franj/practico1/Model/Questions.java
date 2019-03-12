package com.example.franj.practico1.Model;

import java.util.ArrayList;
import java.util.Random;

public class Questions {

    private final int ADDITION = 0;
    private final int SUBSTRACTION = 1;
    private final int MULTIPLICATION = 2;
    private final int DIVISION = 3;
    private double numberOne,numberTwo;
    private int operation;
    private String txt_Operation;

    public Questions(int level) {
        txt_Operation="";
        generateQuestion(level);
    }

    public double getNumberOne() {
        return numberOne;
    }

    public Questions setNumberOne(double numberOne) {
        this.numberOne = numberOne;
        return this;
    }

    public double getNumberTwo() {
        return numberTwo;
    }

    public Questions setNumberTwo(double numberTwo) {
        this.numberTwo = numberTwo;
        return this;
    }

    public String getTxt_Operation() {
        return txt_Operation;
    }

    public void setTxt_Operation(String txt_Operation) {
        this.txt_Operation = txt_Operation;
    }

    public void generateQuestion(int level){
        Random random = new Random();
        if (level==0){

            numberOne = Math.round((random.nextDouble()*51)*100)/100d;
            numberTwo = Math.round((random.nextDouble()*51)*100)/100d;
            int operacion = random.nextInt(4);
            if (operacion==3&&numberTwo==0){
                numberTwo = 1 + Math.round((random.nextDouble() * (51.0 - 1.0))*100)/100d;
            }
        }
        else{
            numberOne = Math.round((random.nextDouble()*100)*100)/100d;
            numberTwo = Math.round((random.nextDouble()*100)*100)/100d;
            int operacion = random.nextInt(4);
            if (operacion==3&&numberTwo==0){
                numberTwo = 1 + Math.round((random.nextDouble() * (101.0 - 1.0))*100)/100d;
            }
        }
    }

    public double verifyAnswer(){
        double answer = 0.0;
        txt_Operation += numberOne+" ";
        switch (operation){
            case 0:
                answer = numberOne+numberTwo;
                txt_Operation+= "+ "+numberTwo;
                break;
            case 1:
                answer = numberOne-numberTwo;
                txt_Operation+= "- "+numberTwo;
                break;
            case 2:
                answer = numberOne*numberTwo;
                txt_Operation+= "* "+numberTwo;
                break;
            case 3:
                answer = numberOne/numberTwo;
                txt_Operation+= "/ "+numberTwo;
                break;
        }
        return answer;
    }

    public ArrayList<Double> generateAnswers(){
        double answer = verifyAnswer();
        Random random = new Random();
        ArrayList<Double> answers = new ArrayList<>();
        answers.add(answer);
        for (int a=1;a<5;a++){
            double randomAnswer = Math.round(((random.nextDouble() * answer) + answer)*100)/100;
            while (randomAnswer == answer) {
                randomAnswer = Math.round(((random.nextDouble() * answer) + answer)*100)/100;
            }
            answers.add(randomAnswer);
        }
        return answers;
    }


}
