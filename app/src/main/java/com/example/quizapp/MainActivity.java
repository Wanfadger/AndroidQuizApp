package com.example.quizapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private EditText answer;
private TextView questionNo,score,question;
private ProgressBar progressBar;
private Button submitBtn,resetBtn;
private int currentNo=0,correctAns=0,currentPosition=0;
private QuizModel quizModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
  quizModel = new QuizModel();
        //views
        answer = findViewById(R.id.answer_id);
        questionNo = findViewById(R.id.question_no);
        score = findViewById(R.id.score);
        question = findViewById(R.id.question_id);
        //progress bar
        progressBar = findViewById(R.id.quiz_bar);
        //view data
        setViewElementsData();


        //Toast.makeText(this , "p "+progress+" size "+quizModel.questions_answer().size()+" current "+(currentNo+1) ,Toast.LENGTH_LONG).show();
        //buttons
        findViewById(R.id.submit_btn).setOnClickListener(this);
        findViewById(R.id.reset_btn).setOnClickListener(this);

    }

    private void setViewElementsData(){

        questionNo.setText("Question_no: "+String.valueOf((currentNo)+1));
        score.setText("Score: "+correctAns+"/"+quizModel.questions_answer().size());
        question.setText(quizModel.questions_answer().get(currentNo).getQuestion());

        double progress = ((currentNo)*100)/quizModel.questions_answer().size();
        progressBar.setProgress((int) progress);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submit_btn:
                String ans = answer.getText().toString();
                if(!ans.isEmpty()){
                    checkAnswer(ans.trim());
                }else{
                    Toast.makeText(this , "answer to continue" ,Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.reset_btn:
                resetAnswer();
                //Toast.makeText(this ,"reset" , Toast.LENGTH_LONG).show();
                break;
        }
    }


    public void checkAnswer(String answer){

              //  if( quizModel.questions_answer().size() > currentNo) {
                    if (answer.equalsIgnoreCase(quizModel.questions_answer().get(currentNo).getAnswer())) {
                        correctAns= correctAns+1;
                        currentNo=currentNo+1;

                        //reset views
                        if( quizModel.questions_answer().size() > currentNo) {
                            setViewElementsData();
                        }else{
                            Toast.makeText(this , "show in dialog " , Toast.LENGTH_LONG).show();
                            final AlertDialog.Builder resultDialog = new AlertDialog.Builder(this)
                                    .setTitle("Final Result")
                                    .setMessage("successfully completed test \n" +
                                            "Got: " +correctAns+" correct \n"+
                                            "Failed "+(quizModel.questions_answer().size()-correctAns)+
                                            "passing percentage "+ ((correctAns*100)/quizModel.questions_answer().size())
                                    ).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(getApplicationContext() , "Thank you ", Toast.LENGTH_LONG).show();
                                        }
                                    })
                                    .setNegativeButton("Restart quiz ", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            correctAns=0;
                                            currentNo=0;
                                            setViewElementsData();
                                        }
                                    });

                            resultDialog.create();
                            resultDialog.show();
                        }

                } else{
                        currentNo++;
                        if( quizModel.questions_answer().size() > currentNo) {
                            setViewElementsData();
                        }else{
                            Toast.makeText(this , "show in dialog " , Toast.LENGTH_LONG).show();
                            final AlertDialog.Builder resultDialog = new AlertDialog.Builder(this)
                                    .setTitle("Final Result")
                                    .setMessage("successfully completed test \n" +
                                            "Got: " +correctAns+" correct \n"+
                                            "Failed "+(quizModel.questions_answer().size()-correctAns)+
                                            "passing percentage "+ ((correctAns*100)/quizModel.questions_answer().size())
                                    ).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(getApplicationContext() , "Thank you ", Toast.LENGTH_LONG).show();
                                            correctAns=0;
                                            currentNo=0;
                                            progressBar.setProgress(0);
                                            setViewElementsData();
                                        }
                                    })
                                    .setNegativeButton("Restart quiz ", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            correctAns=0;
                                            currentNo=0;
                                            setViewElementsData();
                                        }
                                    });

                            resultDialog.create();
                            resultDialog.show();
                        }
                    }

                resetAnswer();

                Toast.makeText(this , "currentNo "+currentNo ,Toast.LENGTH_LONG).show();
            }



    private void resetAnswer(){
        answer.setText("");
    }








}
