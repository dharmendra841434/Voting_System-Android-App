package com.example.onlinevotingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.regex.Pattern;

public class home_activity extends AppCompatActivity implements View.OnClickListener {

    Button log;
    TextView reg;
    EditText id, pass;
    CheckBox c1;

    ProgressBar p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);



        log = findViewById(R.id.login);
        log.setOnClickListener(this);
        reg = findViewById(R.id.Regnow);
        reg.setOnClickListener(this);
        id=(EditText)findViewById(R.id.studentid);
        pass= (EditText) findViewById(R.id.password);
        c1=(CheckBox)findViewById(R.id.show);
        p1=(ProgressBar)findViewById(R.id.progress);

       c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if (b)
               {
                   pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

               }else
               {
                   pass.setTransformationMethod(PasswordTransformationMethod.getInstance());

               }
           }
       });

        id.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                String mid=id.getText().toString();


                if(!Pattern.matches("^[1]{0,1}+[8]{0,1}+[1]{0,1}+[0]{0,1}+[1]{0,1}+[3]{0,1}+[1]{0,1}+[0]{0,1}+[0-9]{0,4}$",mid))
                {
                    id.setError("Wrong ID Please Follow Pattern");
                }
                else
                {
                    id.setError(null);
                    id.setEnabled(true);
                }

            }
        });
      pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {

          String mpass=pass.getText().toString();
          @Override
          public void onFocusChange(View view, boolean b) {
              if(!Pattern.matches("^[a-zA-Z]{0,20}+[0-9]{0,10}$",mpass))
              {
                  pass.setError("use only alphabet and number");
              }
              else
              {
                  pass.setError(null);
                  pass.setEnabled(true);
              }
          }
      });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login:

                String stid = id.getText().toString();
                String stpass = pass.getText().toString();

                if (!stid.isEmpty())
                {
                    id.setError(null);
                    id.setEnabled(true);

                    if (!stpass.isEmpty())
                    {
                        pass.setError(null);
                        pass.setEnabled(true);

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference reference = firebaseDatabase.getReference("StudentData");
                        Query checkid = reference.orderByChild("id").equalTo(stid);

                        checkid.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                if (snapshot.exists())
                                {
                                    id.setEnabled(true);
                                    String checkpass = snapshot.child(stid).child("password").getValue(String.class);
                                    if (checkpass.equals(stpass))
                                    {
                                        pass.setEnabled(true);
                                        Toast.makeText(getApplicationContext(),"Login sucessfull",Toast.LENGTH_SHORT).show();

                                        String studentid = snapshot.child(stid).child("id").getValue(String.class);
                                        String studentname = snapshot.child(stid).child("name").getValue(String.class);
                                        String studentcourse = snapshot.child(stid).child("course").getValue(String.class);
                                        String studentemail = snapshot.child(stid).child("email").getValue(String.class);
                                        String studentmob = snapshot.child(stid).child("mob").getValue(String.class);




                                        Intent  intent = new Intent(getApplicationContext(),Stprofile_activity.class);
                                        intent.putExtra("id",studentid);
                                        intent.putExtra("name",studentname);
                                        intent.putExtra("course",studentcourse);
                                        intent.putExtra("email",studentemail);
                                        intent.putExtra("mob",studentmob);

                                        startActivity(intent);
                                        finish();

                                    }
                                    else
                                    {
                                        AlertDialog alertDialog = new AlertDialog.Builder(home_activity.this)
                                                .setMessage("Wrong Password")
                                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        pass.setText(null);

                                                    }
                                                }).show();
                                        pass.requestFocus();
                                    }
                                }
                                else
                                {
                                    AlertDialog alertDialog = new AlertDialog.Builder(home_activity.this)
                                            .setMessage("Wrong Student Id")
                                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    id.setText(null);

                                                }
                                            }).show();
                                    id.requestFocus();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                    }
                    else
                    {
                        pass.setError("please enter password");
                    }

                }else
                {
                    id.setError("Please Enter Your Id");
                }


                break;
            case R.id.Regnow:

                startActivity(new Intent(home_activity.this, Reg_activity.class));

                break;

        }


    }


}