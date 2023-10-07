package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQstiontexView;
    TextView questionTexView;

    Button ans_A,ans_B,ans_C,ans_D;

    Button SubmitBtn;

    int score=0;

    int totalQuestion=QuestionAnswer.question.length;
    int correntQuestionIndex=0;
    String selectedAnswer="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQstiontexView=findViewById(R.id.total_questiopn);
        questionTexView=findViewById(R.id.question);

        ans_A=findViewById(R.id.ans_A);
        ans_B=findViewById(R.id.ans_B);
        ans_C=findViewById(R.id.ans_C);
        ans_D=findViewById(R.id.ans_D);

        SubmitBtn=findViewById(R.id.SubmitBtn);


        ans_A.setOnClickListener(this);
        ans_B.setOnClickListener(this);
        ans_C.setOnClickListener(this);
        ans_D.setOnClickListener(this);
        SubmitBtn.setOnClickListener(this);


        totalQstiontexView.setText("total Question:"+totalQuestion);

        loadNewQuestion();






    }

    @Override
    public void onClick(View v) {


        ans_A.setBackgroundColor(Color.WHITE);

        ans_B.setBackgroundColor(Color.WHITE);

        ans_C.setBackgroundColor(Color.WHITE);
        ans_D.setBackgroundColor(Color.WHITE);








        Button ClickButton=(Button) v;

        if (ClickButton.getId()==R.id.SubmitBtn){



            if (selectedAnswer.equals(QuestionAnswer.correctAnswers[correntQuestionIndex])){


                score++;
            }

            correntQuestionIndex++;
            loadNewQuestion();



        }else {


            //choices button  clicked

            selectedAnswer=ClickButton.getText().toString();
            ClickButton.setBackgroundColor(Color.BLUE);
        }






    }





    void   loadNewQuestion(){

        if (correntQuestionIndex==totalQuestion){

            finishQuiz();
            return;
        }

   questionTexView.setText(QuestionAnswer.question[correntQuestionIndex]);

   ans_A.setText(QuestionAnswer.choices[correntQuestionIndex][0]);

   ans_B.setText(QuestionAnswer.choices[correntQuestionIndex][1]);
   ans_C.setText(QuestionAnswer.choices[correntQuestionIndex][2]);
   ans_D.setText(QuestionAnswer.choices[correntQuestionIndex][3]);




    }


void finishQuiz(){

        String passStatus="";
        if (score>totalQuestion+0.60){
            passStatus="passed";



        }else {


            passStatus="fell";
        }



        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("score is"+score+"out of "+totalQuestion)
                .setPositiveButton("restart",(dialog, which) -> restartQuiz())
                .show();






    }
public  void   restartQuiz(){



        score=0;
        correntQuestionIndex=0;
        loadNewQuestion();
}




}