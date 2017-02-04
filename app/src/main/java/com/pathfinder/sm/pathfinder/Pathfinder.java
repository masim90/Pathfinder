package com.pathfinder.sm.pathfinder;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import java.util.Random;

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
    List<Button> pathArray = new ArrayList<Button>();
    int levelDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewLogin();
    }

    public void onClickChooseLevel(View view){
        switch (view.getId()){
            case R.id.button_Level1:
                levelDifficulty = 7;
                viewLevel(levelDifficulty);
                break;
            case R.id.button_Level2:
                levelDifficulty = 8;
                viewLevel(levelDifficulty);
                break;
            case R.id.button_Level3:
                levelDifficulty = 9;
                viewLevel(levelDifficulty);
                break;
            case R.id.button_Level4:
                levelDifficulty = 10;
                viewLevel(levelDifficulty);
                break;
            case R.id.button_Level5:
                levelDifficulty = 11;
                viewLevel(levelDifficulty);
                break;
            case R.id.button_Level6:
                levelDifficulty = 12;
                viewLevel(levelDifficulty);
                break;
            case R.id.button_Level7:
                levelDifficulty = 13;
                viewLevel(levelDifficulty);
                break;
        }
    }

    public void onClickStartLevel(View view){
        switch (view.getId()){
            case R.id.button_Start1:
                levelDifficulty = 7;
                viewLevelPath(levelDifficulty);
                break;
            case R.id.button_Start2:
                levelDifficulty = 8;
                viewLevelPath(levelDifficulty);
                break;
            case R.id.button_Start3:
                levelDifficulty = 9;
                viewLevelPath(levelDifficulty);
                break;
            case R.id.button_Start4:
                levelDifficulty = 10;
                viewLevelPath(levelDifficulty);
                break;
            case R.id.button_Start5:
                levelDifficulty = 11;
                viewLevelPath(levelDifficulty);
                break;
            case R.id.button_Start6:
                levelDifficulty = 12;
                viewLevelPath(levelDifficulty);
                break;
            case R.id.button_Start7:
                levelDifficulty = 13;
                viewLevelPath(levelDifficulty);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() < 100){
            Button button  = (Button)findViewById(view.getId());
            System.out.println(pathArray);
            if(!pathArray.isEmpty()) {
                if(pathArray.size() == 1 && button.getX() == pathArray.get(0).getX() && button.getY() == pathArray.get(0).getY()){
                    button.setBackgroundColor(Color.YELLOW);
                    pathArray.remove(0);
                    Toast.makeText(this, "Glückwunsch!", Toast.LENGTH_SHORT).show();
                    viewChooseLevel();
                }
                else if (button.getX() == pathArray.get(0).getX() && button.getY() == pathArray.get(0).getY()) {
                    button.setBackgroundColor(Color.YELLOW);
                    pathArray.remove(0);
                }
                else{
                    Toast.makeText(this, "Falsches Feld! Neustart", Toast.LENGTH_SHORT).show();
                    pathArray.clear();
                    viewLevelPath(levelDifficulty);
                }
            }
            else{
                Toast.makeText(this, "Whoops", Toast.LENGTH_SHORT).show();
            }
            /*
            System.out.println(button);
            System.out.println(Arrays.asList(pathArray));
            if(Arrays.asList(pathArray).contains(button.getId())){
                button.setBackgroundColor(Color.YELLOW);
            }
            else{
                Toast.makeText(this, "Falsches Feld!", Toast.LENGTH_SHORT).show();
                viewLevel1Path();
            }*/
            allowedPath();
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
            /*case R.id.button_Level1:
                viewLevel1();
                break;*/
            //case R.id.button_Start:
                //viewLevelPath();
               // break;
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
        //level1 = (Button) findViewById(R.id.button_Level1);
        //level1.setOnClickListener(this);
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

    public void viewLevel(int levelDifficulty){
        switch (levelDifficulty){
            case 7:
                setContentView(R.layout.level1_start);
                break;
            case 8:
                setContentView(R.layout.level2_start);
                break;
            case 9:
                setContentView(R.layout.level3_start);
                break;
            case 10:
                setContentView(R.layout.level4_start);
                break;
            case 11:
                setContentView(R.layout.level5_start);
                break;
            case 12:
                setContentView(R.layout.level6_start);
                break;
            case 13:
                setContentView(R.layout.level7_start);
                break;

        }


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

    public void viewLevelPath(int levelDifficulty){
        setContentView(R.layout.level1_landing);
        addButtons();
        final Handler myHandler = new Handler();
        XandY customVariable = new XandY(0, 3);
        buttonArray[0][3].setBackgroundColor(Color.GREEN);
        pathArray.add(0,buttonArray[0][3]);
        for(int i=0;i<levelDifficulty;i++){
            myHandler.postDelayed(new delayRunnable(customVariable, i, buttonArray, pathArray),1000+i*1000);
        }
        
        for(int i=0;i<11;i++){
            for(int j=0;j<8;j++){
                buttonArray[i][j].setOnClickListener(this);
                //buttonArray[i][j].setBackgroundColor(Color.BLUE);
            }
        }
        //allowedPath();

        back = (Button) findViewById(R.id.button_Back);
        back.setOnClickListener(this);


    }

    public void allowedPath(){
        for (int i = 0; i < buttonArray.length; i++) {
            for (int j = 0; j < buttonArray[i].length; j++) {
                for (int x = Math.max(0, i - 1); x <= Math.min(i + 1, buttonArray.length); x++) {
                    for (int y = Math.max(0, j - 1); y <= Math.min(j + 1,
                            buttonArray[i].length); y++) {
                        if (x >= 0 && y >= 0 && x < buttonArray.length
                                && y < buttonArray[i].length) {
                            if(x!=i || y!=j){
                                if(((ColorDrawable)buttonArray[x][y].getBackground()).getColor() != Color.YELLOW){
                                    buttonArray[x][y].setOnClickListener(this);
                                }
                                else{
                                    continue;
                                }
                            }
                        }
                    }
                }
                System.out.println("\n");
            }
        }
    }

    /*public void showPath(){
        int x = 3;
        int y = 0;
        buttonArray[x][y].setBackgroundColor(Color.GREEN);
        for(int i=0;i<8;i++){
            Random richtung = new Random();
            richtung.nextInt(4)+1;
            switch (richtung){
                case 1:
                    if(isset(buttonArray[x+1][y]) && buttonArray[x+1][y].getBackground() != Color.GREEN)
            }
        }
    }*/

}
