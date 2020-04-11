package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DatabaseHelper db;
    EditText email,password,name;
    Button sign_in;
    TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db=new DatabaseHelper(this);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        sign_in=findViewById(R.id.sign_in);
        name=findViewById(R.id.name);
        log=findViewById(R.id.log);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent=new Intent(Register.this,Login.class);
                startActivity(loginIntent);
            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eid=email.getText().toString();
                String pass=password.getText().toString();
                String uname=name.getText().toString();

                if(eid.equals("")||pass.equals("")||uname.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter all the fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean ckemail=db.ck_usr_exists(eid);
                    if(ckemail==true)
                    {
                        boolean insert=db.insert(eid,pass,uname);
                        if(insert==true)
                            Toast.makeText(getApplicationContext(),"Registeration was successfull",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Email already exists",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
