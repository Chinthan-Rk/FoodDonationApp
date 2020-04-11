package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText email,password;
    Button login;
    TextView register;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db=new DatabaseHelper(this);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        login=findViewById(R.id.login);
        register=findViewById(R.id.new_user);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent=new Intent(Login.this,Register.class);
                startActivity(registerIntent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eid=email.getText().toString();
                String pass=password.getText().toString();

                if(eid.equals("")||pass.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter all the credentials",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean ckuser=db.ck_val_usr(eid,pass);
                    if(ckuser==true)
                    {
                        Intent mainIntent=new Intent(Login.this,MainActivity.class);
                        startActivity(mainIntent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Invalid User",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
