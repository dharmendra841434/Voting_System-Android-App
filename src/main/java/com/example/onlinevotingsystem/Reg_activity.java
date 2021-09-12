package com.example.onlinevotingsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.regex.Pattern;



public class Reg_activity extends AppCompatActivity implements View.OnFocusChangeListener {

    EditText id,name,course,email,mob,pass,pass2;
    Button submit;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference refrence;
    TextView login;

    CheckBox c1,c2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        id = (EditText) findViewById(R.id.studentid);
        id.setOnFocusChangeListener(this);
        name=(EditText) findViewById(R.id.studentname);
        name.setOnFocusChangeListener(this);
        course = (EditText) findViewById(R.id.studentcourse);
        course.setOnFocusChangeListener(this);
        email=(EditText) findViewById(R.id.studentemail);
        email.setOnFocusChangeListener(this);
        mob = (EditText) findViewById(R.id.studentmob);
        mob.setOnFocusChangeListener(this);
        pass=(EditText) findViewById(R.id.studentpass);
        pass.setOnFocusChangeListener(this);
        pass2=(EditText) findViewById(R.id.studentpass2);
        pass2.setOnFocusChangeListener(this);
        submit=(Button) findViewById(R.id.submit);
        login = (TextView)findViewById(R.id.loginbtn);

        c1=(CheckBox) findViewById(R.id.show);
        c2=(CheckBox) findViewById(R.id.show2);

        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b)
                {
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });
        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b)
                {
                    pass2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    pass2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),home_activity.class));
                finish();
            }
        });



        submit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myid = id.getText().toString();
                String myname = name.getText().toString();
                String mycourse = course.getText().toString();
                String myemail = email.getText().toString();
                String mymob = mob.getText().toString();
                String mypass = pass.getText().toString();
                String mypass2 = pass2.getText().toString();
              if (!myid.isEmpty())
              {
                  id.setError(null);
                  id.setEnabled(true);
                  if (!myname.isEmpty())
                  {
                      name.setError(null);
                      name.setEnabled(true);
                      if (!mycourse.isEmpty())
                      {
                          course.setError(null);
                          course.setEnabled(true);

                          if (!myemail.isEmpty())
                          {
                              email.setError(null);
                              email.setEnabled(true);
                              if (!mymob.isEmpty())
                              {
                                  mob.setError(null);
                                  mob.setEnabled(true);

                                  if (!mypass.isEmpty())
                                  {
                                      pass.setError(null);
                                      pass.setEnabled(true);

                                      if(!mypass2.isEmpty() && mypass2.equals(mypass))
                                      {
                                          pass2.setError(null);
                                          pass2.setEnabled(true);
                                          senddata();
                                      }
                                      else
                                      {
                                          AlertDialog alertDialog = new AlertDialog.Builder(Reg_activity.this)
                                                  .setMessage("Password not matched Pleae Enter same ")
                                                  .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                      @Override
                                                      public void onClick(DialogInterface dialogInterface, int i) {
                                                          pass2.setText(null);

                                                      }
                                                  }).show();
                                      }
                                  }
                                  else
                                  {
                                      pass.setError("enter password");
                                  }
                              }
                              else
                              {
                                  mob.setError("enter mobile number");
                              }
                          }
                          else
                          {
                              email.setError("enter email");
                          }
                      }
                      else
                      {
                          course.setError("enter course");
                      }
                  }
                  else
                  {
                      name.setError("enter name");

                  }
              }
              else
              {
                  id.setError("Enter id");
              }


            }
        });
    }

    private void senddata() {

        firebaseDatabase =firebaseDatabase.getInstance();
        refrence = firebaseDatabase.getReference("StudentData");

        String stid = id.getText().toString();
        String stname = name.getText().toString();
        String stcourse = course.getText().toString();
        String stemail = email.getText().toString();
        String stmob = mob.getText().toString();
        String stpass = pass.getText().toString();


        storedata dt = new storedata(stid,stname,stcourse,stemail,stmob,stpass);
        refrence.child(stid).setValue(dt);


        AlertDialog alertDialog = new AlertDialog.Builder(Reg_activity.this)
                .setMessage("Registration Sucessfull")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Reg_activity.this,home_activity.class));
                        finish();
                    }
                }).show();
    }




        @Override
        public void onFocusChange (View view,boolean b){


            String mid = id.getText().toString();
            String mname = name.getText().toString();
            String mcourse = course.getText().toString();
            String memail = email.getText().toString();
            String mmob = mob.getText().toString();
            String mpass = pass.getText().toString();
            String mpass2 = pass2.getText().toString();

            switch (view.getId()) {
                case R.id.studentid:
                    if (!Pattern.matches("^[1]{0,1}+[8]{0,1}+[1]{0,1}+[0]{0,1}+[1]{0,1}+[3]{0,1}+[1]{0,1}+[0]{0,1}+[0-9]{0,4}$", mid)) {
                        id.setError("Wrong ID Please Follow Pattern");
                    } else {
                        id.setError(null);
                        id.setEnabled(true);
                    }
                    break;
                case R.id.studentname:
                    if (!Pattern.matches("^[a-zA-Z  ]{0,30}$", mname)) {
                        name.setError("only Alphabet used");
                    } else {
                        name.setError(null);
                        name.setEnabled(true);
                    }
                    break;
                case R.id.studentcourse:
                    if (!Pattern.matches("^[a-zA-Z]{0,3}$", mcourse)) {
                        course.setError("Alphabet used");
                    } else {
                        course.setError(null);
                        course.setEnabled(true);
                    }
                    break;
                case R.id.studentemail:
                    if (!Pattern.matches("^[a-zA-Z0-9]{0,20}+[@]{1}+[a-zA-Z0-9]{0,20}+[.]{1}+[a-zA-Z0-9]{0,20}$", memail)) {
                        email.setError("enter vailed email");
                    } else {
                        email.setError(null);
                        email.setEnabled(true);
                    }
                    break;
                case R.id.studentmob:
                    if (!Pattern.matches("(0/91)?[7-9][0-9]{9}", mmob)) {
                        mob.setError("Entert Valid Mobile Number");
                    } else {
                        mob.setError(null);
                        mob.setEnabled(true);
                    }
                    break;
                case R.id.studentpass:
                    if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", mpass)) {
                        pass.setError("Make a Strong Password");
                    } else {
                        pass.setError(null);
                        pass.setEnabled(true);
                    }
                    break;
                case R.id.studentpass2:
                    if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", mpass2)) {
                        pass2.setError("Make a Strong Password");
                    } else {
                        pass2.setError(null);
                        pass2.setEnabled(true);
                    }
                    break;
            }
        }
    }