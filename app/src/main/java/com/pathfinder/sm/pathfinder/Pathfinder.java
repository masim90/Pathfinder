package com.pathfinder.sm.pathfinder;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
    Button[][] buttonArray = new Button[11][8];
    String loginname = "s";
    String passwort = "m";

    Button[][] pathArray = new Button[11][8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewLogin();
    }

    @Override
    public void onClick(View view) {
        if(view.getId() < 100){
            Button button  = (Button)findViewById(view.getId());
            System.out.println(button);
            System.out.println(Arrays.asList(pathArray));
            if(Arrays.asList(pathArray).contains(button.getId())){
                button.setBackgroundColor(Color.YELLOW);
            }
            else{
                Toast.makeText(this, "Falsches Feld!", Toast.LENGTH_SHORT).show();
                viewLevel1Path();
            }
        }
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
                viewLevel1Path();
                break;
            case R.id.button_Back:
                viewChooseLevel();
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
        setContentView(R.layout.level1_start);
        start = (Button) findViewById(R.id.button_Start);
        start.setOnClickListener(this);

        back = (Button) findViewById(R.id.button_Back);
        back.setOnClickListener(this);


    }

    public void addButtons(){
        TableLayout tl = (TableLayout)findViewById(R.id.level1_landing);

        int bId = 0;
        int leftMargin=5;
        int topMargin=5;
        int rightMargin=5;
        int bottomMargin=5;
        TableRow.LayoutParams tableRowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
        tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        tableRowParams.weight = 1;
        tableRowParams.height = 80;
        tableRowParams.width = 80;

        for(int row=0;row<11;row++){
            TableRow currentRow = new TableRow(this);
            currentRow.setLayoutParams(tableRowParams);

            for(int column=0;column<8;column++){


                Button currentButton = new Button(this);
                currentButton.setLayoutParams(tableRowParams);
                currentButton.setId(bId);
                currentButton.setBackgroundColor(Color.BLUE);
                //currentButton.setOnClickListener(this);
                buttonArray[row][column] = currentButton;
                currentRow.addView(currentButton);
                bId++;
            }
            tl.addView(currentRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    public void viewLevel1Path(){
        setContentView(R.layout.level1_landing);
        addButtons();
        final Handler myHandler = new Handler();

        for(int i=0;i<12;i++){
            final int indexA = i;
                myHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        switch(indexA){
                            case 0:
                                buttonArray[0][3].setBackgroundColor(Color.GREEN);
                                pathArray[0][1] = buttonArray[0][3];
                                pathArray[0][1].setId(buttonArray[0][3].getId());
                                break;
                            case 1:
                                buttonArray[1][3].setBackgroundColor(Color.GREEN);
                                pathArray[0][2] = buttonArray[1][3];
                                pathArray[0][2].setId(buttonArray[1][3].getId());
                                break;
                            case 2:
                                buttonArray[1][2].setBackgroundColor(Color.GREEN);
                                pathArray[0][3] = buttonArray[1][2];
                                pathArray[0][3].setId(buttonArray[1][2].getId());
                                break;
                            case 3:
                                buttonArray[1][1].setBackgroundColor(Color.GREEN);
                                pathArray[0][4] = buttonArray[1][1];
                                pathArray[0][4].setId(buttonArray[1][1].getId());
                                break;
                            case 4:
                                buttonArray[2][1].setBackgroundColor(Color.GREEN);
                                pathArray[0][5] = buttonArray[2][1];
                                pathArray[0][5].setId(buttonArray[2][1].getId());
                                break;
                            case 5:
                                buttonArray[3][1].setBackgroundColor(Color.GREEN);
                                pathArray[0][6] = buttonArray[3][1];
                                pathArray[0][6].setId(buttonArray[3][1].getId());
                                break;
                            case 6:
                                buttonArray[3][2].setBackgroundColor(Color.GREEN);
                                pathArray[0][7] = buttonArray[3][2];
                                pathArray[0][7].setId(buttonArray[3][2].getId());
                                break;
                            case 7:
                                buttonArray[3][3].setBackgroundColor(Color.GREEN);
                                pathArray[1][0] = buttonArray[3][3];
                                pathArray[1][0].setId(buttonArray[3][3].getId());
                                break;
                            case 8:
                                buttonArray[3][4].setBackgroundColor(Color.GREEN);
                                pathArray[1][1] = buttonArray[3][4];
                                pathArray[1][1].setId(buttonArray[3][4].getId());
                                break;
                            case 9:
                                buttonArray[4][4].setBackgroundColor(Color.GREEN);
                                pathArray[1][2] = buttonArray[4][4];
                                pathArray[1][2].setId(buttonArray[4][4].getId());
                                break;
                            case 10:
                                buttonArray[5][4].setBackgroundColor(Color.GREEN);
                                pathArray[1][3] = buttonArray[5][4];
                                pathArray[1][3].setId(buttonArray[5][4].getId());

                                break;
                            case 11:
                                for(int k=0;k<11;k++){
                                    for(int l=0;l<8;l++){
                                        buttonArray[k][l].setBackgroundColor(Color.BLUE);
                                    }
                                }
                                break;
                        }
                    }
                }, 1000+i*1000);


        }
        for(int i=0;i<11;i++){
            for(int j=0;j<8;j++){
                buttonArray[i][j].setOnClickListener(this);
            }
        }


        back = (Button) findViewById(R.id.button_Back);
        back.setOnClickListener(this);


    }

}
