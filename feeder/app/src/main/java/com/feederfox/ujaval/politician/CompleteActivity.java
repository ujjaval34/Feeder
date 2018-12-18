package com.feederfox.ujaval.politician;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.feederfox.ujaval.politician.Db.DbHelper;
import com.feederfox.ujaval.politician.bean.Politician_bean;

import java.util.ArrayList;

public class CompleteActivity extends AppCompatActivity {

    TextView textView;
    EditText et_Synopsis;
    EditText et_Projects_Taken;
    EditText et_Educational_Qualification;
    EditText et_Family_History;
    EditText et_Politician_Rating;
    Button bt_sub;

    int politic_id;
    DbHelper dbHelper;

    ArrayList<Politician_bean> politic_arraylist=new ArrayList<Politician_bean>();
    Politician_bean politician_bean;
    long status=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        textView=(TextView) findViewById(R.id.textView);
        et_Synopsis=(EditText) findViewById(R.id.et_Synopsis);

        et_Projects_Taken=(EditText) findViewById(R.id.et_Projects_Taken);
        et_Educational_Qualification=(EditText) findViewById(R.id.et_Educational_Qualification);
        et_Family_History=(EditText) findViewById(R.id.et_Family_History);
        et_Politician_Rating=(EditText) findViewById(R.id.et_Politician_Rating);
        bt_sub=(Button) findViewById(R.id.bt_sub);
        dbHelper=new DbHelper(this);

        Intent intent = getIntent();
        politic_id=intent.getIntExtra("message",0);  //get id from main activity

        politic_arraylist=new ArrayList<Politician_bean>();
        politic_arraylist=dbHelper.get_plitician_byID(politic_id+"");  //get complete details from database

        if (politic_arraylist !=null){
            for (int i=0;i<politic_arraylist.size();i++){

                textView.setText(politic_arraylist.get(i).getPolitic_Name());
                et_Synopsis.setText(politic_arraylist.get(i).getSynopsis());
                et_Projects_Taken.setText(politic_arraylist.get(i).getProjects_Taken());
                et_Educational_Qualification.setText(politic_arraylist.get(i).getEducational_Qualification());

                et_Family_History.setText(politic_arraylist.get(i).getFamily_History());
                et_Politician_Rating.setText(politic_arraylist.get(i).getPolitician_Rating());

            }
        }


        bt_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                politician_bean=new Politician_bean();
                politician_bean.setSynopsis(et_Synopsis.getText().toString());
                politician_bean.setProjects_Taken(et_Projects_Taken.getText().toString());
                politician_bean.setEducational_Qualification(et_Educational_Qualification.getText().toString());
                politician_bean.setFamily_History(et_Family_History.getText().toString());
                politician_bean.setPolitician_Rating(et_Politician_Rating.getText().toString());
                politician_bean.setPolitic_ID(politic_id);

                politic_arraylist.add(politician_bean);

                status=dbHelper.Updatedetails(politic_arraylist);     //update your data with latest information
                if (status>0){
                    finish();
                }


            }
        });
    }
}
