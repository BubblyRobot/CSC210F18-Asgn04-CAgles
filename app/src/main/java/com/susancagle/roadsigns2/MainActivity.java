package com.susancagle.roadsigns2;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static ImageView iv_picture;
    private static RadioGroup rg_choices;
    private static RadioButton rb_selected;
    private static RadioButton rb_choiceA;
    private static RadioButton rb_choiceB;
    private static RadioButton rb_choiceC;
    private static TextView tv_question;
    private static TextView tv_number;
    private static Button b_submit;
    private int currentQuestionIndex;
    private ArrayList<Question> questions;
    private int score = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initialize();
    }

    private void initialize() {
        iv_picture = findViewById(R.id.picture_iv);
        rg_choices = findViewById(R.id.choices_radioGroup);
        rb_choiceA = findViewById(R.id.a_rb);
        rb_choiceB = findViewById(R.id.b_rb);
        rb_choiceC = findViewById(R.id.c_rb);
        tv_question = findViewById(R.id.question_tv);
        tv_number = findViewById(R.id.score_number);
        b_submit = findViewById(R.id.submit_button);

        currentQuestionIndex = 0;
        questions = new ArrayList<>();

        questions.add(new Question(R.drawable.windingroad, "What is this Warning sign?",
                "Slippery When Wet", "Ice Skating Ring Ahead", "Winding Road", "c"));
        questions.add(new Question(R.drawable.yieldahead, "What is this Warning sign?",
                "Blargggll", "Yield Ahead", "Swervy Signage", "b"));
        questions.add(new Question(R.drawable.moosecrossing, "What is this Warning sign?",
                "Moose Crossing", "Amusement Park Ahead", "No Poaching Allowed", "a"));

        this.displayQuestion(currentQuestionIndex);
    }

    public void checkSubmit(View view) {
        //we'll only call advance button if correct answer was selected.



        if(answerIsRight() ) {
            //display a toast message

           Toast.makeText(getApplicationContext(), "Right!", Toast.LENGTH_SHORT).show();
            score++;
            tv_number.setText("Score:" + Integer.toString(score));
            advance();
        }

        else{
            Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean answerIsRight() {
        String answer = "x";
        int button_id = rg_choices.getCheckedRadioButtonId();
        rb_selected = findViewById(button_id);
        if (rb_selected == rb_choiceA) answer = "a";
        if (rb_selected == rb_choiceB) answer = "b";
        if (rb_selected == rb_choiceC) answer = "c";

        return questions.get(currentQuestionIndex).isCorrectAnswer(answer);
    }

    private void displayQuestion(int index) {

        iv_picture.setImageResource(questions.get(currentQuestionIndex).getPictureID());
        tv_question.setText(questions.get(currentQuestionIndex).getQuestionText());
        rb_choiceA.setText(questions.get(currentQuestionIndex).getChoiceA());
        rb_choiceB.setText(questions.get(currentQuestionIndex).getChoiceB());
        rb_choiceC.setText(questions.get(currentQuestionIndex).getChoiceC());
        rg_choices.clearCheck(); //this clears the entire radio group each time
        //so each time you play it it has fresh radio group.
    }

    private void advance(){
        //we want to increment the current index but if we go past the index list we display a message that the game is over.
        //we'll use modulo for this.

            currentQuestionIndex = (currentQuestionIndex + 1) % questions.size();
            displayQuestion(currentQuestionIndex);
        }



}
