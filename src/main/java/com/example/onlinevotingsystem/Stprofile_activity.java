package com.example.onlinevotingsystem;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class Stprofile_activity extends AppCompatActivity {

    TextView id,name,course,email,mob;
    Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stprofile);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        id=(TextView) findViewById(R.id.idtxt);
        name=(TextView) findViewById(R.id.nametxt);
        course=(TextView) findViewById(R.id.coursetxt);
        email=(TextView) findViewById(R.id.emailtxt);
        mob=(TextView) findViewById(R.id.mobtxt);
        b= (Button)findViewById(R.id.vote);


        showalldata();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Voting_pannel.class));

            }
        });

    }


    private void showalldata() {

        Intent intent = getIntent();

        String stid = intent.getStringExtra("id");
        String stname = intent.getStringExtra("name");
        String stcourse = intent.getStringExtra("course");
        String stemail = intent.getStringExtra("email");
        String stmob = intent.getStringExtra("mob");


        id.setText(stid);
        name.setText(stname);
        course.setText(stcourse);
        email.setText(stemail);
        mob.setText(stmob);

    }
}