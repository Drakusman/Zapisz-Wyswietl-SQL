package Main.App.Translate;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class DisplayActivity extends AppCompatActivity {


    private static final String NICK = "DisplayActivity";


    MyDatabase instaDB;


    private ListView myListFinall;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.display_layout);


        myListFinall = (ListView) findViewById(R.id.listLang);


        instaDB = new MyDatabase(this);


        if(MainActivity.chooseOption ==false)
        {


            WordsList();


        }
        if(MainActivity.chooseOption ==true)
        {


            LangList();


        }
    }


    private void LangList()
    {


        Log.d(NICK, "Showing data.");


            instaDB.getDataLang();


            Cursor MainDatabaseStream = instaDB.takeLangData();


            final ArrayList<String> listFromDB1 = new ArrayList<>();


            final ArrayList<String> listFromDB2 = new ArrayList<>();


            final ArrayList<String> listFromDB3 = new ArrayList<>();


            final ArrayList<String> listFromDBFull = new ArrayList<>();


            while(MainDatabaseStream.moveToNext())
            {


                listFromDB1.add(MainDatabaseStream.getString(1));


                listFromDB2.add(MainDatabaseStream.getString(2));


                listFromDB3.add(MainDatabaseStream.getString(3));

            }


            for(int i=0;i<70;i++)
            {


                String text = listFromDB1.get(i).toString()+ "                                [" +listFromDB2.get(i).toString()+"]";


                if(listFromDB3.get(i).equals("0"))
                {

                    myListFinall.setItemChecked(i,false);


                }
                if(listFromDB3.get(i).equals("1"))
                {

                    myListFinall.setItemChecked(i,true);


                }


                listFromDBFull.add(text);


            }




            ArrayAdapter <String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,listFromDBFull);


            myListFinall.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            myListFinall.setAdapter(myAdapter);






        myListFinall.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String itemName = listFromDB1.get(i);


                if(myListFinall.isItemChecked(i))
                {


                    instaDB.fixCheckBox(true,itemName);


                }
                else
                {


                    instaDB.fixCheckBox(false,itemName);


                }

                Cursor MainDatabaseStream = instaDB.takeLangData();


                final ArrayList<String> listFromDB1 = new ArrayList<>();


                final ArrayList<String> listFromDB2 = new ArrayList<>();


                ArrayList<String> listFromDB3 = new ArrayList<>();


                ArrayList<String> listFromDBLast = new ArrayList<>();





                while(MainDatabaseStream.moveToNext())
                {


                    listFromDB1.add(MainDatabaseStream.getString(1));


                    listFromDB2.add(MainDatabaseStream.getString(2));


                    listFromDB3.add(MainDatabaseStream.getString(3));


                }
                for(int j=0;j<70;j++)
                {


                    String textLineLang = listFromDB1.get(j).toString()+ "                                  [" +listFromDB2.get(j).toString()+"]";


                    if(listFromDB3.get(j).equals("1"))
                    {


                        myListFinall.setItemChecked(j,true);


                    }
                    if(listFromDB3.get(j).equals("0"))
                    {


                        myListFinall.setItemChecked(j,false);


                    }


                    listFromDBLast.add(textLineLang);


                }


                ArrayAdapter <String> myAdapter2 = new ArrayAdapter<String>(DisplayActivity.this, android.R.layout.simple_list_item_multiple_choice,listFromDBLast);

            }
        });

    }




    private void WordsList() {
        Log.d(NICK, "Displaying data.");



        Cursor data = instaDB.takeWordsData();


        ArrayList<String> listData = new ArrayList<>();


        while(data.moveToNext()){


            listData.add(data.getString(1));
        }


        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);


        myListFinall.setAdapter(adapter);



        myListFinall.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String nickname = adapterView.getItemAtPosition(i).toString();

                Log.d(NICK, "You Click on " + nickname);



                Cursor myDatabaseAccess = instaDB.getID(nickname);


                int wordId = -1;

                while(myDatabaseAccess.moveToNext())
                {

                    wordId = myDatabaseAccess.getInt(0);
                }



                if(wordId > -1){
                    Log.d(NICK, "The ID is: " + wordId);

                    Intent editScreenIntent = new Intent(DisplayActivity.this, UpdateActivity.class);


                    editScreenIntent.putExtra("id",wordId);



                    editScreenIntent.putExtra("name",nickname);


                    startActivity(editScreenIntent);

                }
                else{

                    toastMessage("No ID found!");

                }
            }
        });
    }





    private void toastMessage(String message){

        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();

    }
}
