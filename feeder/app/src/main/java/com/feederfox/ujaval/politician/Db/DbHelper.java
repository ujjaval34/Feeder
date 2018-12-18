package com.feederfox.ujaval.politician.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.feederfox.ujaval.politician.bean.Politician_bean;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    public static String dbName="politic_database";



    public DbHelper(Context context) {
        super(context, dbName, null, 2);
    }

    public static String Politic_TABLE="politic_Table";
    public static String ID="Id";
    public static String Politic_ID="politic_Id";
    public static String Politic_Name="politic_name";
    public static String Image="image";
    public static String Synopsis="synopsis";
    public static String Projects_Taken="projects_taken";
    public static String Educational_Qualification="educational_qualification";
    public static String Family_History="family_history";
    public static String Video_link="video_link";
    public static String Politician_Rating="politician_rating";

    public static String Polling_TABLE="polling_Table";
    public static String PID="pId";
    public static String Polling_ID="polling_Id";
    public static String FK_Polling_PID="fk_polling_pid";
    public static String Polling_Name="polling_name";
    public static String Polling_Upvote="polling_upvote";
    public static String Polling_DownVote="polling_downVote";
    public static String Polling_Date="polling_date";
    public static String Polling_Month_Name="polling_month_name";
    public static String Polling_Like="polling_like";
    public static String Polling_Dislike="polling_dislike";

    public static String Article_TABLE="article_Table";
    public static String AID="aId";
    public static String Article_ID="article_Id";
    public static String FK_Article_PID="fk_article_pid";
    public static String Article_Name="article_name";
    public static String Article_Title="article_title";
    public static String Article_Image="article_image";
    public static String Article_Description="article_description";
    public static String Article_Date="article_Date";
    public static String Article_Month="article_Month";

    public static String Survey_TABLE="survey_Table";
    public static String SID="sId";
    public static String Survey_ID="survey_id";
    public static String FK_Survey_PID="fk_survey_pid";
    public static String Survey_Name="survey_name";
    public static String Survey_Poll_Question="survey_poll_question";
    public static String Survey_Date="survey_date";
    public static String Survey_Month="survey_month";


    public static String Comments_TABLE="comments_Table";
    public static String CID="cId";
    public static String Comments_id="comments_id";
    public static String FK_Comments_PID="fk_comments_pid";
    public static String Comments_image="comments_image";
    public static String Comments_user="comments_user";
    public static String Comments_uploaded_on="comments_uploaded_on";
    public static String Comments_comment="comments_comment";
    public static String Comments_ratings="comments_ratings";




    public static String CREATE_Politic_TABLE="CREATE TABLE "+Politic_TABLE+" ( "+ID+" INTEGER PRIMARY KEY,"
            +Politic_ID + " INTEGER,"
            +Politic_Name + " TEXT,"
            +Image + " TEXT,"
            +Synopsis + " TEXT,"
            +Projects_Taken + " TEXT,"
            +Educational_Qualification + " TEXT,"
            +Family_History + " TEXT,"
            +Video_link + " TEXT,"
            +Politician_Rating + " TEXT"
            + ")";


    public static String CREATE_Polling_TABLE="CREATE TABLE "+Polling_TABLE+" ( "+PID+" INTEGER PRIMARY KEY,"
            +Polling_ID + " INTEGER,"
            +FK_Polling_PID + " INTEGER,"
            +Polling_Name + " TEXT,"
            +Polling_Upvote + " TEXT,"
            +Polling_DownVote + " TEXT,"
            +Polling_Date + " TEXT,"
            +Polling_Month_Name + " TEXT,"
            +Polling_Like + " TEXT,"
            +Polling_Dislike + " TEXT"
            + ")";

    public static String CREATE_Article_TABLE="CREATE TABLE "+Article_TABLE+" ( "+AID+" INTEGER PRIMARY KEY,"
            +Article_ID + " INTEGER,"
            +FK_Article_PID + " INTEGER,"
            +Article_Name + " TEXT,"
            +Article_Title + " TEXT,"
            +Article_Image + " TEXT,"
            +Article_Description + " TEXT,"
            +Article_Date + " TEXT,"
            +Article_Month + " TEXT"
            + ")";


    public static String CREATE_Survey_TABLE="CREATE TABLE "+Survey_TABLE+" ( "+SID+" INTEGER PRIMARY KEY,"
            +Survey_ID + " INTEGER,"
            +FK_Survey_PID + " INTEGER,"
            +Survey_Name + " TEXT,"
            +Survey_Poll_Question + " TEXT,"
            +Survey_Date + " TEXT,"
            +Survey_Month + " TEXT"
            + ")";

    public static String CREATE_Comments_TABLE="CREATE TABLE "+Comments_TABLE+" ( "+CID+" INTEGER PRIMARY KEY,"
            +Comments_id + " INTEGER,"
            +FK_Comments_PID + " INTEGER,"
            +Comments_image + " TEXT,"
            +Comments_user + " TEXT,"
            +Comments_uploaded_on + " TEXT,"
            +Comments_comment + " TEXT,"
            +Comments_ratings + " TEXT"
            + ")";



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_Politic_TABLE);
        db.execSQL(CREATE_Polling_TABLE);
        db.execSQL(CREATE_Article_TABLE);
        db.execSQL(CREATE_Survey_TABLE);
        db.execSQL(CREATE_Comments_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void savedetails(ArrayList<Politician_bean> politician_beanArrayList){

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values=new ContentValues();
        long status =0;
        Cursor cursor=null;

        for (int i=0;i<politician_beanArrayList.size();i++){
            String sql ="SELECT * From "+Politic_TABLE+" WHERE "+Politic_ID+" = '"+politician_beanArrayList.get(i).getPolitic_ID()+"'";
            cursor=db.rawQuery(sql,null);

            values.put(Politic_ID,politician_beanArrayList.get(i).getPolitic_ID());

            values.put(Politic_Name,politician_beanArrayList.get(i).getPolitic_Name());
            values.put(Image,politician_beanArrayList.get(i).getImage());
            values.put(Synopsis,politician_beanArrayList.get(i).getSynopsis());
            values.put(Projects_Taken,politician_beanArrayList.get(i).getProjects_Taken());
            values.put(Educational_Qualification,politician_beanArrayList.get(i).getEducational_Qualification());
            values.put(Family_History,politician_beanArrayList.get(i).getFamily_History());
            values.put(Video_link,politician_beanArrayList.get(i).getVideo_link());
            values.put(Politician_Rating,politician_beanArrayList.get(i).getPolitician_Rating());

            db.insert(Politic_TABLE,null,values);



        }
        cursor.close();
        db.close();
        values.clear();
    }



    public ArrayList<Politician_bean> get_plitician(){
        Politician_bean politician_bean;
        ArrayList<Politician_bean> politicianList= new ArrayList<Politician_bean>();
        SQLiteDatabase db= this.getReadableDatabase();
        String status="";
        String sql="SELECT * FROM "+Politic_TABLE ;
        Cursor cursor=db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            politician_bean=new Politician_bean();
            politician_bean.setPolitic_Name(cursor.getString(cursor.getColumnIndex(Politic_Name)));
            politician_bean.setPolitic_ID(cursor.getInt(cursor.getColumnIndex(Politic_ID)));
            politician_bean.setImage(cursor.getString(cursor.getColumnIndex(Image)));
            politician_bean.setSynopsis(cursor.getString(cursor.getColumnIndex(Synopsis)));
            politician_bean.setProjects_Taken(cursor.getString(cursor.getColumnIndex(Projects_Taken)));
            politician_bean.setEducational_Qualification(cursor.getString(cursor.getColumnIndex(Educational_Qualification)));
            politician_bean.setFamily_History(cursor.getString(cursor.getColumnIndex(Family_History)));
            politician_bean.setVideo_link(cursor.getString(cursor.getColumnIndex(Video_link)));
            politician_bean.setPolitician_Rating(cursor.getString(cursor.getColumnIndex(Politician_Rating)));

            politicianList.add(politician_bean);
        }
        db.close();
        cursor.close();
        return politicianList;
    }



    public ArrayList<Politician_bean> get_plitician_byID(String id){
        Politician_bean politician_bean;
        ArrayList<Politician_bean> politicianList= new ArrayList<Politician_bean>();
        SQLiteDatabase db= this.getReadableDatabase();
        String status="";
        String sql="SELECT * FROM "+Politic_TABLE +" WHERE "+Politic_ID+" = '"+id+"'";
        Cursor cursor=db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            politician_bean=new Politician_bean();
            politician_bean.setPolitic_Name(cursor.getString(cursor.getColumnIndex(Politic_Name)));
            politician_bean.setPolitic_ID(cursor.getInt(cursor.getColumnIndex(Politic_ID)));
            politician_bean.setImage(cursor.getString(cursor.getColumnIndex(Image)));
            politician_bean.setSynopsis(cursor.getString(cursor.getColumnIndex(Synopsis)));
            politician_bean.setProjects_Taken(cursor.getString(cursor.getColumnIndex(Projects_Taken)));
            politician_bean.setEducational_Qualification(cursor.getString(cursor.getColumnIndex(Educational_Qualification)));
            politician_bean.setFamily_History(cursor.getString(cursor.getColumnIndex(Family_History)));
            politician_bean.setVideo_link(cursor.getString(cursor.getColumnIndex(Video_link)));
            politician_bean.setPolitician_Rating(cursor.getString(cursor.getColumnIndex(Politician_Rating)));

            politicianList.add(politician_bean);
        }
        db.close();
        cursor.close();
        return politicianList;
    }




    public long Updatedetails(ArrayList<Politician_bean> politician_beanArrayList){

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values=new ContentValues();
        long status =0;
        Cursor cursor=null;

        for (int i=0;i<politician_beanArrayList.size();i++){
            String sql ="SELECT * From "+Politic_TABLE+" WHERE "+Politic_ID+" = '"+politician_beanArrayList.get(i).getPolitic_ID()+"'";
            cursor=db.rawQuery(sql,null);

            values.put(Politic_ID,politician_beanArrayList.get(i).getPolitic_ID());

            values.put(Synopsis,politician_beanArrayList.get(i).getSynopsis());
            values.put(Projects_Taken,politician_beanArrayList.get(i).getProjects_Taken());
            values.put(Educational_Qualification,politician_beanArrayList.get(i).getEducational_Qualification());
            values.put(Family_History,politician_beanArrayList.get(i).getFamily_History());
            values.put(Politician_Rating,politician_beanArrayList.get(i).getPolitician_Rating());

            status= db.update(Politic_TABLE, values,Politic_ID+" = '"+politician_beanArrayList.get(i).getPolitic_ID()+"'", null);



        }
        cursor.close();
        db.close();
        values.clear();
        return status;
    }


}
