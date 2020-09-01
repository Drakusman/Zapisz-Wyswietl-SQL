package Main.App.Translate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDatabase extends SQLiteOpenHelper {

    private static final String NICK = "MyDatabase";



    private static final String TableWords = "word_table";


    private static final String WordColumn1 = "ID";


    private static final String WordColumn2 = "name";




    private static final String TableLanguage = "language_table";


    private static final String LanguageColumn1 = "ID";


    private static final String LanguageColumn2 = "language";


    private static final String LanguageColumn3 = "code";


    private static final String LanguageColumn4 = "checkbox";



    private String[] LanguageNames = new String[]{"af","sq","ar","hy","az","ba","eu","be","bn","bs","bg","ca","km","zh","zh-TW","cv",
            "hr","cs","da","nl","en","eo","et","fi","fr","ka","de","el","gu","ht","he","hi",
            "hu","is","id","ga","it","ja","kk","ky","ko","ku","lv","lt","ms","ml","mt","mn","nb","nn","pa","fa","pl","pt","ps","ro","ru","sr",
            "sk", "sl","so","es","sv","ta","te","th","tr","uk","ur","vi"};



    private String[] LanguageCodes = new String[]{"Afrikaans","Albanian","Arabic","Armenian","Azerbaijani","Bashkir",
            "Basque","Belarusian","Bengali","Bosnian","Bulgarian","Catalan","Central Khmer", "Chinese (Simplified)","Chinese (Traditional)",
            "Chuvash","Croatian","Czech","Danish","Dutch","English","Esperanto","Estonian","Finnish",
            "French","Georgian","German","Greek","Gujarati","Haitian","Hebrew","Hindi","Hungarian","Icelandic",
            "Indonesian","Irish","Italian","Japanese", "Kazakh","Kirghiz","Korean","Kurdish","Latvian",
            "Lithuanian","Malay","Malayalam","Maltese","Mongolian","Norwegian Bokmal","Norwegian Nynorsk",
            "Panjabi","Persian","Polish","Portuguese","Pushto","Romanian","Russian","Serbian","Slovakian","Slovenian",
            "Somali","Spanish","Swedish","Tamil","Telugu","Thai","Turkish","Ukrainian","Urdu","Vietnamese"};



    public MyDatabase(Context context) {
        super(context, TableWords, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase base) {


        String createTable = "CREATE TABLE " + TableWords + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WordColumn2 +" TEXT)";
        base.execSQL(createTable);



        createTable = "CREATE TABLE " + TableLanguage + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                LanguageColumn2 +" TEXT, " + LanguageColumn3 + " TEXT, "+ LanguageColumn4 + " BOOLEAN)";
        base.execSQL(createTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase base, int i, int i1) {


        base.execSQL("DROP IF TABLE EXISTS " + TableWords);

        onCreate(base);

        base.execSQL("DROP IF TABLE EXISTS " + TableLanguage);

        onCreate(base);

    }

    public boolean newData(String choosen) {

        SQLiteDatabase dataBase = this.getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(WordColumn2, choosen);

        Log.d(NICK, "Adding " + choosen + " to " + TableWords);

        long end = dataBase.insert(TableWords, null, contentValues);


        if (end == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor takeWordsData(){

        SQLiteDatabase base = this.getWritableDatabase();


        String query = "SELECT * FROM " + TableWords;

        Cursor accessToBase = base.rawQuery(query, null);

        return accessToBase;
    }
    public Cursor takeLangData(){


        SQLiteDatabase base = this.getWritableDatabase();
        String query = "SELECT * FROM " + TableLanguage;

        Cursor StreamDataBase = base.rawQuery(query, null);


        return StreamDataBase;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param ID
     * @return
     */
    public Cursor getID(String ID){

        SQLiteDatabase base = this.getWritableDatabase();

        String query = "SELECT " + WordColumn1 + " FROM " + TableWords +
                " WHERE " + WordColumn2 + " = '" + ID + "'";

        Cursor myDataBaseAccess = base.rawQuery(query, null);

        return myDataBaseAccess;
    }

    /**
     * Updates the name field
     * @param name_New
     * @param id
     * @param name_Old
     */

    public void fixNames(String name_New, int id, String name_Old){


        SQLiteDatabase base = this.getWritableDatabase();


        String query = "UPDATE " + TableWords + " SET " + WordColumn2 +
                " = '" + name_New + "' WHERE " + WordColumn1 + " = '" + id + "'";


        Log.d(NICK, "query: " + query);


        Log.d(NICK, "Changing name to " + name_New);


        base.execSQL(query);
    }
    public void fixCheckBox(Boolean  ifChecked, String id){


        SQLiteDatabase base = this.getWritableDatabase();


        String query = "UPDATE " + TableLanguage + " SET " + LanguageColumn4 +
                " = '" + ifChecked + "' WHERE " + LanguageColumn2 + " = '" + id + "'";


        Log.d(NICK, "query: " + query);


        Log.d(NICK, "Setting name to " + ifChecked);


        base.execSQL(query);
    }
    /**
     * Delete from database
     * @param id
     * @param name
     */
    public void removeName(int id, String name){

        SQLiteDatabase base = this.getWritableDatabase();


        String query = "DELETE FROM " + TableWords + " WHERE "
                + WordColumn1 + " = '" + id + "'" +
                " AND " + WordColumn2 + " = '" + name + "'";

        Log.d(NICK, "query: " + query);


        Log.d(NICK, "Removing " + name + " from database.");


        base.execSQL(query);
    }

    public boolean getDataLang() {

        SQLiteDatabase base = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        long end=0;

        for(int i=0; i<70;i++)
        {
            values.put(LanguageColumn2, LanguageCodes[i]);

            values.put(LanguageColumn3, LanguageNames[i]);

            values.put(LanguageColumn4,true);

            end= base.insert(TableLanguage, null, values);
        }


        Log.d(NICK, "Language added!");



        if (end == -1)
        {
            return false;
        }
        else {
            return true;
        }
    }


}
























