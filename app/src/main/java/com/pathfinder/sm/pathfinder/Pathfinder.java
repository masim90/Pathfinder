package com.pathfinder.sm.pathfinder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Pathfinder extends Activity implements View.OnClickListener{

    Button register;
    Button registerSave;
    Button login;
    Button close;
    TextView title;
    EditText loginName;
    EditText loginPw;
    EditText userName;
    EditText userMail;
    EditText userPW1;
    EditText userPW2;
    Button back;
    Button backToLogin;
    Button guide;
    Button[][] buttonArray = new Button[11][8];
    List<Button> pathArray = new ArrayList<Button>();
    int levelDifficulty;
    SharedPreferences prefs;
    final String KEY1 = "key1";
    final String KEY2 = "key2";
    final String KEY3 = "key3";
    final String KEY4 = "key4";
    final String SHARED_PREFERENCES_KEY = "prefdata1";
    String username;
    String usermail;
    String userpw1;
    String userpw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = this.getSharedPreferences(SHARED_PREFERENCES_KEY, MODE_PRIVATE);
        viewLogin();


    }

    /*
     * OnClick Event für die Level-Buttons und setzen der Schwierigkeit für das Aktivieren der
     * anderen Level-Buttons
     */
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

    /*
     * OnClick Event für die Start-Buttons und setzen der Schwierigkeit für die Länge des Pfades
     */
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

    /*
     * Prüfen ob alle Felder gefüllt sind und speichern in XML Datei
     */
    public void registerUser(){

        if(userName.getText().length() == 0)
        {
            Toast.makeText(this, "Bitte Benutzername eingeben!", Toast.LENGTH_SHORT).show();
        }
        else if(userMail.getText().length() == 0)
        {
            Toast.makeText(this, "Bitte Email eingeben!", Toast.LENGTH_SHORT).show();
        }
        else if(userPW1.getText().length() == 0)
        {
            Toast.makeText(this, "Bitte Passwort eingeben!", Toast.LENGTH_SHORT).show();
        }
        else if(userPW2.getText().length() == 0)
        {
            Toast.makeText(this, "Bitte Passwort wiederholen!", Toast.LENGTH_SHORT).show();
        }
        else if(!userPW2.getText().toString().equals(userPW1.getText().toString()))
        {
            Toast.makeText(this, "Passwörter müssen übereinstimmen!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            username = userName.getText().toString();
            usermail = userMail.getText().toString();
            userpw1 = userPW1.getText().toString();
            userpw2 = userPW2.getText().toString();
            prefs.edit().putString(KEY1, username).apply();
            prefs.edit().putString(KEY2, usermail).apply();
            prefs.edit().putString(KEY3, userpw1).apply();
            prefs.edit().putString(KEY4, userpw2).apply();
            Toast.makeText(this, "Registrierung erfolgreich!", Toast.LENGTH_SHORT).show();
            viewLogin();
        }
    }

    @Override
    public void onClick(View view)
    {
        /*
         * Spielfeld-Buttons haben keinen String als Id, daher kann hier auf einen Int<100 abgeprüft
         * werden
         */
        if(view.getId() < 100)
        {
            Button button  = (Button)findViewById(view.getId());
            /*
             * Abprüfen, ob noch Buttons vom vorgegebenen Pfad in der path-Arraylist vorhanden sind
             */
            if(!pathArray.isEmpty())
            {
                /*
                 * Nur noch 1 Element im pathArray vorhanden und Koordinaten gleich?
                 */
                if(pathArray.size() == 1 && button.getX() == pathArray.get(0).getX()
                        && button.getY() == pathArray.get(0).getY())
                {
                    /*
                     * Button-Farbe auf Gelb setzen, erstes Element aus pathArray löschen, Glückwunsch
                     * und Levelauswahl anzeigen
                     */
                    button.setBackgroundColor(Color.YELLOW);
                    pathArray.remove(0);
                    Toast.makeText(this, "Glückwunsch!", Toast.LENGTH_SHORT).show();
                    viewChooseLevel(levelDifficulty);
                }
                /*
                 * Koordinaten gleich?
                 */
                else if (button.getX() == pathArray.get(0).getX()
                        && button.getY() == pathArray.get(0).getY())
                {
                    /*
                     * Button-Farbe auf Gelb setzen und erstes Element aus pathArray löschen
                     */
                    button.setBackgroundColor(Color.YELLOW);
                    pathArray.remove(0);
                }
                /*
                 * Falsches Feld gedrückt
                 */
                else
                {
                    /*
                     * Benutzer entscheidet, ob Neustart des Levels oder zurück zur Levelauswahl
                     */
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Falsches Feld!");
                    builder.setMessage("Neustart?");
                    builder.setPositiveButton("Neustart", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            pathArray.clear();
                            viewLevelPath(levelDifficulty);
                        }
                    });
                    builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            pathArray.clear();
                            levelDifficulty = levelDifficulty-1;
                            viewChooseLevel(levelDifficulty);
                        }
                    });
                    AlertDialog dialog = builder.show();
                    TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
                    messageText.setGravity(Gravity.CENTER);
                }
            }
        }
        else
        {
            switch (view.getId())
            {
                case R.id.button_Register:
                    viewRegister();
                    break;

                /*
                 * Methode aufrufen um Benutzer-Daten zu speichern
                 */
                case R.id.button_RegisterSave:
                    registerUser();
                    break;

                /*
                 * Prüfen ob eingegebene Login-Daten korrekt
                 */
                case R.id.button_Login:
                    if(loginName.getText().toString().equals(prefs.getString(KEY1,"Falscher Benutzername"))
                            && loginPw.getText().toString().equals(prefs.getString(KEY3, "Falsches Passwort")))
                    {
                        Toast.makeText(this, "Sie haben sich erfolgreich eingeloggt", Toast.LENGTH_SHORT).show();
                        viewChooseLevel(levelDifficulty);
                    }
                    else
                    {
                        Toast.makeText(this, "Falsche Benutzerdaten", Toast.LENGTH_SHORT).show();
                    }
                    break;

                /*
                 * Anleitung anzeigen
                 */
                case R.id.button_Guide:
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Anleitung");
                    builder.setMessage("1. Wählen Sie zunächst ein Level aus. \n\n2. Die nächsten " +
                            "Level sind ausgegraut, bis Sie das jeweils Vorige erfolgreich" +
                                    " abgeschlossen haben. Die Schwierigkeit erhöht sich pro Level, " +
                            "indem die Länge des Pfades erhöht wird. \n\n3. Sobald Sie " +
                                    "ein Level ausgewählt und auf Start gedrückt haben, wird Ihnen " +
                            "ein Pfad vorgezeichnet. \n\n4. Prägen Sie sich den Pfad gut " +
                                    "ein! Ihre Aufgabe besteht darin, den vorgegebenen Pfad aus " +
                            "dem Gedächtnis nachzuzeichnen. \n\n5. Berühren Sie hierfür das Feld " +
                                    "des Buttons, von dem Sie denken, dass er der nächste des " +
                            "generierten Pfades war. \n\n6. Sollten Sie falsch liegen, werden Sie " +
                                    "gefragt, ob Sie das Level neu starten oder abbrechen wollen. " +
                            "Wenn Sie den kompletten Pfad richtig nachgezeichnet haben, können Sie " +
                            "das nächste Level auswählen."
                    );
                    builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            viewChooseLevel(levelDifficulty);
                        }
                    });
                    AlertDialog dialog = builder.show();
                    TextView messageText = (TextView) dialog.findViewById(android.R.id.message);
                    messageText.setGravity(Gravity.LEFT);
                    break;
                case R.id.button_BackToLogin:
                    viewLogin();
                    break;
                case R.id.button_Back:
                    viewChooseLevel(levelDifficulty);
                    break;
                case R.id.button_close:
                    finish();
            }
        }
    }

    /*
     * Levelauswahl anzeigen und Buttons nur aktivieren wenn voriges Level abgeschlossen wurde
     */
    public void viewChooseLevel(int levelDifficulty)
    {
        System.out.println(levelDifficulty);
        setContentView(R.layout.level_landing);
        backToLogin = (Button) findViewById(R.id.button_BackToLogin);
        guide = (Button) findViewById(R.id.button_Guide);
        backToLogin.setOnClickListener(this);
        guide.setOnClickListener(this);
        Button lvl1 = (Button) findViewById(R.id.button_Level1);
        Button lvl2 = (Button) findViewById(R.id.button_Level2);
        Button lvl3 = (Button) findViewById(R.id.button_Level3);
        Button lvl4 = (Button) findViewById(R.id.button_Level4);
        Button lvl5 = (Button) findViewById(R.id.button_Level5);
        Button lvl6 = (Button) findViewById(R.id.button_Level6);
        Button lvl7 = (Button) findViewById(R.id.button_Level7);
        lvl1.setEnabled(true);
        lvl2.setEnabled(false);
        lvl3.setEnabled(false);
        lvl4.setEnabled(false);
        lvl5.setEnabled(false);
        lvl6.setEnabled(false);
        lvl7.setEnabled(false);
        switch(levelDifficulty)
        {
            case 7:
                lvl2.setEnabled(true);
                break;
            case 8:
                lvl2.setEnabled(true);
                lvl3.setEnabled(true);
                break;
            case 9:
                lvl2.setEnabled(true);
                lvl3.setEnabled(true);
                lvl4.setEnabled(true);
                break;
            case 10:
                lvl2.setEnabled(true);
                lvl3.setEnabled(true);
                lvl4.setEnabled(true);
                lvl5.setEnabled(true);
                break;
            case 11:
                lvl2.setEnabled(true);
                lvl3.setEnabled(true);
                lvl4.setEnabled(true);
                lvl5.setEnabled(true);
                lvl6.setEnabled(true);
                break;
            case 12:
                lvl2.setEnabled(true);
                lvl3.setEnabled(true);
                lvl4.setEnabled(true);
                lvl5.setEnabled(true);
                lvl6.setEnabled(true);
                lvl7.setEnabled(true);
                break;
            default:
                lvl1.setEnabled(true);
        }
    }

    /*
     * Login anzeigen
     */
    public void viewLogin()
    {
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

    /*
     * Registrierung anzeigen
     */
    public void viewRegister()
    {
        setContentView(R.layout.register_landing);
        userName = (EditText) findViewById(R.id.personName_editName);
        userMail = (EditText) findViewById(R.id.personName_editEmail);
        userPW1 = (EditText) findViewById(R.id.personName_editPW1);
        userPW2 = (EditText) findViewById(R.id.personName_editPW2);
        backToLogin = (Button) findViewById(R.id.button_BackToLogin);
        registerSave = (Button) findViewById(R.id.button_RegisterSave);
        backToLogin.setOnClickListener(this);
        registerSave.setOnClickListener(this);
    }

    /*
     * Startseite für jedes Level anzeigen
     */
    public void viewLevel(int levelDifficulty)
    {
        switch (levelDifficulty)
        {
            case 7:
                setContentView(R.layout.level1_start);
                back = (Button) findViewById(R.id.button_Back);
                back.setOnClickListener(this);
                break;
            case 8:
                setContentView(R.layout.level2_start);
                back = (Button) findViewById(R.id.button_Back);
                back.setOnClickListener(this);
                break;
            case 9:
                setContentView(R.layout.level3_start);
                back = (Button) findViewById(R.id.button_Back);
                back.setOnClickListener(this);
                break;
            case 10:
                setContentView(R.layout.level4_start);
                back = (Button) findViewById(R.id.button_Back);
                back.setOnClickListener(this);
                break;
            case 11:
                setContentView(R.layout.level5_start);
                back = (Button) findViewById(R.id.button_Back);
                back.setOnClickListener(this);
                break;
            case 12:
                setContentView(R.layout.level6_start);
                back = (Button) findViewById(R.id.button_Back);
                back.setOnClickListener(this);
                break;
            case 13:
                setContentView(R.layout.level7_start);
                back = (Button) findViewById(R.id.button_Back);
                back.setOnClickListener(this);
                break;
        }
    }

    /*
     * Spielfeld mit Buttons befüllen
     */
    public void addButtons()
    {
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

        for(int row=0;row<11;row++)
        {
            TableRow currentRow = new TableRow(this);
            currentRow.setLayoutParams(tableRowParams);

            for(int column=0;column<8;column++)
            {
                Button currentButton = new Button(this);
                currentButton.setLayoutParams(tableRowParams);
                currentButton.setId(bId);
                currentButton.setBackgroundColor(Color.BLUE);
                buttonArray[row][column] = currentButton;
                currentRow.addView(currentButton);
                bId++;
            }
            tl.addView(currentRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT));
        }
    }

    /*
     * Pfad anzeigen
     */
    public void viewLevelPath(int levelDifficulty)
    {
        setContentView(R.layout.level1_landing);
        addButtons();
        final Handler myHandler = new Handler();
        XandY customVariable = new XandY(0, 3);
        buttonArray[0][3].setBackgroundColor(Color.GREEN);
        pathArray.add(0,buttonArray[0][3]);

        for(int i=0;i<levelDifficulty;i++)
        {
            /*
             * Verzögerung für jeden Button des Pfades
             */
            myHandler.postDelayed(new DelayRunnable(customVariable, i, buttonArray, pathArray, levelDifficulty),1000+i*1000);
        }

        /*
         * Buttons klickbar machen und vorgegebenen Pfad löschen
         */
        myHandler.postDelayed(new DelayRunnable(buttonArray, this)
        {
            @Override
            public void run()
            {
                for (int i = 0; i < 11; i++)
                {
                    for (int j = 0; j < 8; j++)
                    {
                        buttonArray[i][j].setBackgroundColor(Color.BLUE);
                        buttonArray[i][j].setOnClickListener(pathfinder);
                    }
                }
            }
        }, 1000+levelDifficulty*1000);

        back = (Button) findViewById(R.id.button_Back);
        back.setOnClickListener(this);
    }
}
