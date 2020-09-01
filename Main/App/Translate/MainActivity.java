package Main.App.Translate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private static final String Main = "MainActivity";


    MyDatabase instaDB;


    private Button addNewWordButton, showWords, newWordButton, displayWordsButton, editWordButton, languageSettingButton;


    private EditText fieldForNewWord;


    public static boolean chooseOption = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingUpButtons();

        instaDB = new MyDatabase(this);


        displayWordsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);


                chooseOption = false;


                startActivity(intent);
            }
        });


        languageSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);


                chooseOption =true;


                startActivity(intent);
            }
        });


        addNewWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String newEntry = fieldForNewWord.getText().toString();


                if (fieldForNewWord.length() != 0)
                {


                    PutData(newEntry);


                    fieldForNewWord.setText("");



                } else {

                    toastMessage("This field is Empty!");

                }

            }
        });





        editWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);


                chooseOption =false;


                startActivity(intent);


            }
        });





        newWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fieldForNewWord.setVisibility(View.VISIBLE);


                addNewWordButton.setVisibility(View.VISIBLE);


            }
        });


    }


    public void PutData(String newAdd)
    {


        boolean putData = instaDB.newData(newAdd);


        if (putData)
        {

            toastMessage("Data added");

        }
        else {


            toastMessage("Something goes wrong!");


        }
    }




    public void PutLanguage()
    {


        boolean addedData = instaDB.getDataLang();


        if (addedData)
        {

            toastMessage("Data added!");


        } else {


            toastMessage("Something goes wrong!");

        }
    }

    public void settingUpButtons()
    {


        newWordButton = (Button) findViewById(R.id.addPhrases);


        addNewWordButton = (Button) findViewById(R.id.btnAdd);


        fieldForNewWord = (EditText) findViewById(R.id.editText);


        displayWordsButton = (Button) findViewById(R.id.displayPhrases);


        editWordButton = (Button) findViewById(R.id.editPhrases);


        languageSettingButton = (Button) findViewById(R.id.languageSubscription);


    }





    private void toastMessage(String message){


        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();


    }



}
