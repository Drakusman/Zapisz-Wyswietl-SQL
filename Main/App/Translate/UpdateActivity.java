package Main.App.Translate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UpdateActivity extends AppCompatActivity {

    private static final String NICK = "UpdateActivity";

    public Button saveButton, deleteButton;

    private EditText textEditable;

    MyDatabase instaDB;


    private String choosenItem;

    private int choosenID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.update_layout);

        saveButton = (Button) findViewById(R.id.btnSave);

        deleteButton = (Button) findViewById(R.id.btnDelete);

        textEditable = (EditText) findViewById(R.id.editable_item);


        instaDB = new MyDatabase(this);


        Intent oldIntent = getIntent();


        choosenID = oldIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value


        choosenItem = oldIntent.getStringExtra("name");


        textEditable.setText(choosenItem);


        saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                String item = textEditable.getText().toString();

                if(!item.equals("")){

                    instaDB.fixNames(item, choosenID, choosenItem);

                }else{

                    toastMessage("Put name first");

                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                instaDB.removeName(choosenID, choosenItem);


                textEditable.setText("");


                toastMessage("Deleted!");


            }
        });

    }

    private void toastMessage(String message)
    {

        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();

    }
}
























