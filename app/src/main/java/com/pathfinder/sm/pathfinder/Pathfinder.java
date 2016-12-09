package com.pathfinder.sm.pathfinder;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class Pathfinder extends Activity implements View.OnClickListener, View.OnHoverListener{

    Button register;
    Button login;
    Button start;
    Button close;
    TextView title;
    EditText loginName;
    EditText loginPw;
    Button back;
    Button level1;
    Button b0_0;
    Button b0_1;
    Button b0_2;
    Button b0_3;
    Button b0_4;
    Button b0_5;
    Button b1_0;
    Button b1_1;
    Button b1_2;
    Button b1_3;
    Button b1_4;
    Button b1_5;
    Button b2_0;
    Button b2_1;
    Button b2_2;
    Button b2_3;
    Button b2_4;
    Button b2_5;
    Button b2_6;
    Button b3_0;
    Button b3_1;
    Button b3_2;
    Button b3_3;
    Button b3_4;
    Button b3_5;
    Button b4_0;
    Button b4_1;
    Button b4_2;
    Button b4_3;
    Button b4_4;
    Button b4_5;
    Button b5_0;
    Button b5_1;
    Button b5_2;
    Button b5_3;
    Button b5_4;
    Button b5_5;

    Button[][] buttonArray = new Button[6][6];




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
            case R.id.button_Start:
                addButtons();
                break;
            case R.id.button_Back:
                viewChooseLevel();
                break;
            case 55:
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
        start = (Button) findViewById(R.id.button_Start);
        start.setOnClickListener(this);

        back = (Button) findViewById(R.id.button_Back);
        back.setOnClickListener(this);


    }

    public void addButtons(){

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.level1_landing);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        String buttonId = "";
        int bId;
        int margin = 0;
        for(int i=0;i<6;i++){
            for(int j=0;j<6;j++){
                buttonArray[i][j] = new Button(this);
                buttonId = i + "" + j;
                bId = Integer.parseInt(buttonId);
                buttonArray[i][j].setId(bId);
                buttonArray[i][j].setBackgroundColor(Color.BLUE);
                buttonArray[i][j].setLayoutParams(lp);
                buttonArray[i][j].setY(30 - margin);
                buttonArray[i][j].setX(10 + margin);
                rl.addView(buttonArray[i][j], lp);
                margin = margin + 3;
                System.out.println(buttonArray[i][j].getId());
                buttonArray[i][j].setOnClickListener(this);

            }
        }


        back = (Button) findViewById(R.id.button_Back);
        back.setOnClickListener(this);
    }


}
