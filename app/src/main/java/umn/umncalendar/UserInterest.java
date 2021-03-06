package umn.umncalendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.*;

public class UserInterest extends AppCompatActivity {
    private InterestHelper itHelper = new InterestHelper();

    // clickable variables
    private CheckBox checkbox_music;
    private CheckBox checkbox_movie;
    private CheckBox checkbox_sport;
    private CheckBox checkbox_career;
    private CheckBox checkbox_recreation;
    private CheckBox checkbox_food;
    private CheckBox checkbox_culture;
    private CheckBox checkbox_photo;
    private Button continue_interest;
    private TextView text_welcome;
    private TextView text_user_name;
    private TextView text_welcome_message;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interest);
        final String userEmail = getIntent().getStringExtra("email");
        final String userName = getIntent().getStringExtra("name");
        define();
        text_user_name.setText(userName);


        continue_interest.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                ArrayList<String> interest = new ArrayList<>();
                clickStored(interest);
                itHelper.addEntry(userEmail, interest);

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }


    /**
     * define all clickable variables
     */
    public void define(){
        checkbox_music = (CheckBox)findViewById(R.id.checkbox_music);
        checkbox_movie = (CheckBox)findViewById(R.id.checkbox_movie);
        checkbox_sport = (CheckBox)findViewById(R.id.checkbox_sport);
        checkbox_career = (CheckBox)findViewById(R.id.checkbox_career);
        checkbox_recreation = (CheckBox)findViewById(R.id.checkbox_recreation);
        checkbox_culture = (CheckBox)findViewById(R.id.checkbox_culture);
        checkbox_photo = (CheckBox)findViewById(R.id.checkbox_photo);
        checkbox_food = (CheckBox)findViewById(R.id.checkbox_food);

        continue_interest = (Button)findViewById(R.id.continue_interest);
        text_welcome = (TextView)findViewById(R.id.text_welcome);
        text_welcome_message = (TextView)findViewById(R.id.text_welcome_message);
        text_user_name = (TextView)findViewById(R.id.text_user_name);


    }

    /**
     * add interest to user's info
     * @param addedInterest: a list that contains user's selection
     */
    public void clickStored(ArrayList<String> addedInterest){
        if (checkbox_music.isChecked()){
            addedInterest.add(checkbox_music.getHint().toString());
        }
        if (checkbox_movie.isChecked()){
            addedInterest.add(checkbox_movie.getHint().toString());
        }

        if (checkbox_recreation.isChecked()){
            addedInterest.add(checkbox_recreation.getHint().toString());
        }
        if (checkbox_career.isChecked()){
            addedInterest.add(checkbox_career.getHint().toString());
        }
        if (checkbox_food.isChecked()){
            addedInterest.add(checkbox_food.getHint().toString());
        }
        if (checkbox_sport.isChecked()){
            addedInterest.add(checkbox_sport.getHint().toString());
        }
        if (checkbox_culture.isChecked()){
            addedInterest.add(checkbox_culture.getHint().toString());
        }
        if (checkbox_photo.isChecked()){
            addedInterest.add(checkbox_photo.getHint().toString());
        }
    }
}