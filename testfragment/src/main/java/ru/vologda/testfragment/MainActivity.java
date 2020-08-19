package ru.vologda.testfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener {
    FrameLayout fragmentContainer;
    EditText editText;
    TextView textView;
    Button button,button2;
    int count=0;
    int p=3,r=3;
    ArrayList<Question> myQuestions;
    int currentQuestion=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.start_activity);
    }

    private void openFragment(String text, int p, int r) {
      BlankFragment fragment = BlankFragment.newInstance(text,p,r);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right,R.anim.enter_from_right,
                R.anim.exit_to_right);
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_container,fragment,"BLANK_FRAGMENT").commit();
    }

    @Override
    public void onFragmentInteraction(String sendBackText) {
        if(sendBackText.equals("reset")){
            currentQuestion=0;
            count=1;
            p=3; r=3;
            setContentView(R.layout.rules);
        }
        textView.setText(myQuestions.get(currentQuestion).text);
        onBackPressed();
     }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if (count == 0) {
                count++;
                setContentView(R.layout.rules);
            } else if (count == 1) {
                count++;
                runQuest();
            }
        }
            return true;
        }
        public void runQuest () {
            setContentView(R.layout.activity_main);

            fragmentContainer = findViewById(R.id.fragment_container);
            textView = findViewById(R.id.textView);
            Questions questions = new Questions();
            myQuestions = questions.getQuestions(6);
            textView.setText(myQuestions.get(currentQuestion).text);
            button = findViewById(R.id.button1);
            button2 = findViewById(R.id.button2);
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String text = myQuestions.get(currentQuestion).answerText;
                    if (view.getId() == R.id.button1 && myQuestions.get(currentQuestion).answer) {
                        r -= 1;
                    } else if (view.getId() == R.id.button2 && !myQuestions.get(currentQuestion).answer) {
                        r -= 1;
                    } else {
                        p -= 1;
                    }
                    currentQuestion++;
                    openFragment(text, p, r);
                }
            };
            button.setOnClickListener(listener);
            button2.setOnClickListener(listener);
        }

}
