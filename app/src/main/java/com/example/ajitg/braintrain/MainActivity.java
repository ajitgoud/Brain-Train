package com.example.ajitg.braintrain;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView,challengeTextView,scoreTextView,resultTextView;
    Button button0,button1,button2,button3,playAgainButton,additionButton,substractionButton,multiplicationButton,divisionButton,randomButton,creditsButton,aboutButton;
    RelativeLayout challengeScreen;
    LinearLayout startScreen;
    public CountDownTimer countDownTimer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int selectedChallengeTagNumber;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestion = 0;
    boolean gameIsActive = true;
    boolean flag = true;
    boolean playAgainTimerCorrection = true;
   /* public void updateScores(String tagNumber){
        if(tagNumber.equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");
        }else{
            resultTextView.setText("Wrong!");
        }
        numberOfQuestion++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        generateChallenge(selectedChallengeTagNumber);
    }*/

    public void timeLeft(){
        countDownTimer = new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long l) {
                String timeLeft = String.valueOf(l / 1000) + "s";
                timerTextView.setText(timeLeft);
            }

            @Override
            public void onFinish() {
                gameIsActive = false;
                playAgainButton.setVisibility(0);
                timerTextView.setText("0s");
                resultTextView.setText("Your score is :" + Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
                playAgainTimerCorrection = true;
            }
        }.start();
    }


    public void playAgain(View view){
        score = 0;
        numberOfQuestion = 0;
        gameIsActive = true;
        scoreTextView.setText("0/0");
        timerTextView.setText("30s");
        resultTextView.setText("");
        playAgainButton.setVisibility(4);
        flag = true;
        if(playAgainTimerCorrection){
            generateChallenge(selectedChallengeTagNumber);
        }
    }


    public void chooseAnswer(View view){
        String tagNumber = view.getTag().toString();
        if(tagNumber.equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct!");
        }else{
            resultTextView.setText("Wrong!");
        }
        numberOfQuestion++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));
        generateChallenge(selectedChallengeTagNumber);
    }



    public void generateChallenge(int selectedChallenge){
        if(flag){
            challengeScreen.setVisibility(0);
            timeLeft();
            playAgainTimerCorrection = false;
            playAgain(playAgainButton);
            flag = false;

        }
       if(gameIsActive){
           Random rand = new Random();
           locationOfCorrectAnswer = rand.nextInt(3);
           int a=0,b=0;
           switch(selectedChallenge){
               case 1:
                   a = rand.nextInt(21);
                   b = rand.nextInt(21);
                   challengeTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
                   break;
               case 2:
                   a = rand.nextInt(41);
                   b = rand.nextInt(41);
                   challengeTextView.setText(Integer.toString(a) + " - " + Integer.toString(b));
                   break;
               case 3:
                   a = rand.nextInt(11);
                   b = rand.nextInt(11);
                   challengeTextView.setText(Integer.toString(a) + " * " + Integer.toString(b));
                   break;
               case 4:
                   a = rand.nextInt(80) + 20;
                   b = rand.nextInt(20) + 1;
                   challengeTextView.setText(Integer.toString(a) + " / " + Integer.toString(b));
           }

           int wrongAnswer = 0;
           answers.clear();
           for(int i = 0 ; i < 4 ; i++){
               if(i ==locationOfCorrectAnswer){
                   switch(selectedChallenge){
                       case 1:
                           answers.add(a+b);
                           break;
                       case 2:
                           answers.add(a-b);
                           break;
                       case 3:
                           answers.add(a*b);
                           break;
                       case 4:
                           answers.add(a/b);
                           break;
                   }
               }else{
                   switch(selectedChallenge){
                       case 1:
                           wrongAnswer = rand.nextInt(41);
                           while(wrongAnswer == (a+b)) {
                               wrongAnswer = rand.nextInt(41);
                           }
                           break;
                       case 2:
                           wrongAnswer = rand.nextInt(82);
                           while(wrongAnswer == (a-b)) {
                               wrongAnswer = rand.nextInt(82);
                           }
                           break;
                       case 3:
                           wrongAnswer = rand.nextInt(130);
                           while(wrongAnswer == (a*b)) {
                               wrongAnswer = rand.nextInt(130);
                           }
                           break;
                       case 4:
                           wrongAnswer = rand.nextInt(15);
                           while(wrongAnswer == (a/b)) {
                               wrongAnswer = rand.nextInt(15);
                           }
                           break;
                   }
                   answers.add(wrongAnswer);
               }
           }
           button0.setText(Integer.toString(answers.get(0)));
           button1.setText(Integer.toString(answers.get(1)));
           button2.setText(Integer.toString(answers.get(2)));
           button3.setText(Integer.toString(answers.get(3)));
       }
    }


    public void ceditsScreen(){

    }


    public void aboutScreen(){

    }


    public void optionChosen(View view){
        selectedChallengeTagNumber = Integer.parseInt(view.getTag().toString()) - 3;
        startScreen.setVisibility(view.INVISIBLE);
        switch (selectedChallengeTagNumber){
            case 1:
                generateChallenge(selectedChallengeTagNumber);
                break;
            case 2:
                generateChallenge(selectedChallengeTagNumber);
                break;
            case 3:
                generateChallenge(selectedChallengeTagNumber);
                break;
            case 4:
                generateChallenge(selectedChallengeTagNumber);
                break;
            case 5:
                generateChallenge(selectedChallengeTagNumber);
                break;
            case 6:
                ceditsScreen();
                break;
            case 7:
                aboutScreen();
                break;
        }
    }

    public void backToHome(View view){
        countDownTimer.cancel();
        answers.clear();
        challengeScreen.setVisibility(view.INVISIBLE);
        startScreen.setVisibility(view.VISIBLE);
        flag = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timerTextView = (TextView) findViewById(R.id.timerTextView);
        challengeTextView = (TextView) findViewById(R.id.challengeTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        additionButton = (Button) findViewById(R.id.additionButton);
        substractionButton = (Button) findViewById(R.id.substractionButton);
        multiplicationButton = (Button) findViewById(R.id.multiplicationButton);
        divisionButton = (Button) findViewById(R.id.divisionButton);
        randomButton = (Button) findViewById(R.id.randomButton);
        creditsButton = (Button) findViewById(R.id.creditsButton);
        aboutButton = (Button) findViewById(R.id.aboutButton);
        challengeScreen = (RelativeLayout) findViewById(R.id.challengeScreen);
        startScreen = (LinearLayout) findViewById(R.id.startScreen);

    }
}
