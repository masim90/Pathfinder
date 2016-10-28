package com.pathfinder.sm.pathfinder;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Pathfinder extends Activity implements View.OnClickListener, View.OnHoverListener{

    Button register;
    Button login;
    Button close;
    TextView title;
    EditText loginName;
    EditText loginPw;
    Button back;
    Button level1;

    String loginname = "s";
    String passwort = "m";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewLogin();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.button_Register:
                viewRegister();
                break;
            case R.id.button_Login:
                if(loginName.getText().toString().equals(loginname) && loginPw.getText().toString().equals(passwort)){
                    Toast.makeText(this, "Sie haben sich erfolgreich eingeloggt", Toast.LENGTH_SHORT).show();
                    viewChooseLevel();
                }
                else{
                    Toast.makeText(this, "Falsche Benutzerdaten", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_Level1:
                viewLevel1();
                break;
            case R.id.button_Back:
                viewLogin();
                break;
            case R.id.button_close:
                finish();
        }
    }

    @Override
    public boolean onHover(View view, MotionEvent motionEvent) {
        return true;
    }

    public void viewChooseLevel(){
        setContentView(R.layout.level_landing);
        back = (Button) findViewById(R.id.button_Back);
        back.setOnClickListener(this);
        level1 = (Button) findViewById(R.id.button_Level1);
        level1.setOnClickListener(this);
    }

    public void viewLogin(){
        setContentView(R.layout.activity_pathfinder);
        register = (Button) findViewById(R.id.button_Register);
        login = (Button) findViewById(R.id.button_Login);
        close = (Button) findViewById(R.id.button_close);
        title = (TextView) findViewById(R.id.largeText_LoginTitle);
        loginName = (EditText) findViewById(R.id.personName_editName);
        loginPw = (EditText) findViewById(R.id.password_editPW);
        register.setOnClickListener(this);
        login.setOnClickListener(this);
        close.setOnClickListener(this);
    }

    public void viewRegister(){
        setContentView(R.layout.register_landing);
        back = (Button) findViewById(R.id.button_Back);
        back.setOnClickListener(this);
    }

    public void viewLevel1(){
        setContentView(R.layout.level1_landing);
    }
}
