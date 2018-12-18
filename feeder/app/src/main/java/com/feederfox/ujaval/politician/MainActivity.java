package com.feederfox.ujaval.politician;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.feederfox.ujaval.politician.Db.DbHelper;
import com.feederfox.ujaval.politician.app.AppController;
import com.feederfox.ujaval.politician.app.UrlConfig;
import com.feederfox.ujaval.politician.bean.Articles_bean;
import com.feederfox.ujaval.politician.bean.Comments_bean;
import com.feederfox.ujaval.politician.bean.Politician_bean;
import com.feederfox.ujaval.politician.bean.Polling_bean;
import com.feederfox.ujaval.politician.bean.Survey_bean;
import com.feederfox.ujaval.politician.exceptions.ExceptionLog;
import com.feederfox.ujaval.politician.network.ConnectionDetector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {

    ConnectionDetector connectionDetector;
    Politician_bean politician_bean;
    Polling_bean polling_bean;
    Articles_bean articles_bean;
    Survey_bean survey_bean;
    Comments_bean comment_bean;
    DbHelper dbHelper;

    ListView politic_list;
    CustomAdapter customAdapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ArrayList<Politician_bean> politician_Array =new ArrayList<Politician_bean>();

    ArrayList<Polling_bean> polling_Array =new ArrayList<Polling_bean>();
    ArrayList<Articles_bean> articles_Array =new ArrayList<Articles_bean>();
    ArrayList<Survey_bean> survey_Array =new ArrayList<Survey_bean>();
    ArrayList<Comments_bean> comments_Array =new ArrayList<Comments_bean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectionDetector=new ConnectionDetector(this);
        politician_bean=new Politician_bean();
        polling_bean=new Polling_bean();
        articles_bean=new Articles_bean();
        survey_bean=new Survey_bean();
        comment_bean=new Comments_bean();
        dbHelper=new DbHelper(this);
        politic_list=(ListView) findViewById(R.id.politic_list);
        sharedPreferences=this.getSharedPreferences("pref",0);
        editor=sharedPreferences.edit();







        if (connectionDetector.isConnectingToInternet()) {  //check internet connection
            int call_api=sharedPreferences.getInt("call_api",0);//call api only first time
            if (call_api==0){
                getlistview();
                editor.putInt("call_api",1);
                editor.commit();
            }else{
                getDataFrom_db();  //call server api
            }

        }
    }


    private void getlistview() {

        HttpsTrustManager.allowAllSSL();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, UrlConfig.URLLINK2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        try {
                            JSONObject res = new JSONObject(s);

                            if (res != null) {

                                politician_Array =new ArrayList<Politician_bean>();
                                articles_Array =new ArrayList<Articles_bean>();
                                survey_Array =new ArrayList<Survey_bean>();
                                comments_Array =new ArrayList<Comments_bean>();


                                JSONArray jsonArray = res.getJSONArray("Politician_data");

                                for(int i = 0 ; i < jsonArray.length(); i++) {
                                    JSONObject res2 = jsonArray.getJSONObject(i);
                                    int id = res2.getInt("id");
                                    String Name = res2.getString("Name");

                                    String Image = res2.getString("Image");
                                    String Synopsis = res2.getString("Synopsis");
                                    String Projects_Taken = res2.getString("Projects_Taken");
                                    String Educational_Qualification = res2.getString("Educational_Qualification");
                                    String Family_History = res2.getString("Family_History");
                                    String video_link = res2.getString("video_link");
                                    String Politician_Rating = res2.getString("Politician_Rating");

                                    politician_bean = new Politician_bean();
                                    politician_bean.setPolitic_ID(id);
                                    politician_bean.setPolitic_Name(Name);
                                    politician_bean.setImage(Image);
                                    politician_bean.setSynopsis(Synopsis);
                                    politician_bean.setProjects_Taken(Projects_Taken);
                                    politician_bean.setEducational_Qualification(Educational_Qualification);
                                    politician_bean.setFamily_History(Family_History);
                                    politician_bean.setVideo_link(video_link);
                                    politician_bean.setPolitician_Rating(Politician_Rating);

                                    politician_Array.add(politician_bean);  //set value to array list


                                    polling_bean = new Polling_bean();

                                    JSONObject nested = res2.getJSONObject("Polling");
                                    if (nested != null){
                                        polling_bean.setFK_Polling_PID(res2.getInt("id"));
                                    polling_bean.setPolling_ID(nested.getInt("id"));
                                    polling_bean.setPolling_Upvote(nested.getString("Upvote"));
                                    polling_bean.setPolling_DownVote(nested.getString("DownVote"));
                                    polling_bean.setPolling_Date(nested.getString("Date"));
                                    polling_bean.setPolling_Like(nested.getString("Like"));
                                    polling_bean.setPolling_Dislike(nested.getString("Dislike"));
                                    JSONObject nested2 = nested.getJSONObject("Month");
                                        if (nested2 != null) {
                                            polling_bean.setPolling_Month_Name(nested2.getString("Month_Name"));
                                        }

                                        polling_Array.add(polling_bean);//set value to array list
                                    }

                                    JSONArray articles = res2.getJSONArray("Politician_Articles");
                                    for(int j = 0 ; j < articles.length(); j++) {
                                        JSONObject articles_obj = articles.getJSONObject(j);
                                        articles_bean=new Articles_bean();
                                        articles_bean.setArticle_ID(articles_obj.getInt("id"));
                                        articles_bean.setFK_Article_PID(res2.getInt("id"));
                                        articles_bean.setArticle_Name(articles_obj.getString("Name"));
                                        articles_bean.setArticle_Title(articles_obj.getString("Title"));
                                        articles_bean.setArticle_Image(articles_obj.getString("Image"));
                                        articles_bean.setArticle_Description(articles_obj.getString("Description"));
                                        articles_bean.setArticle_Date(articles_obj.getString("Date"));

                                        JSONObject articles_nested = articles_obj.getJSONObject("Month");
                                        articles_bean.setArticle_Month(articles_nested.getString("Month_Name"));

                                        articles_Array.add(articles_bean);//set value to array list

                                        }

                                    JSONArray survey = res2.getJSONArray("Politician_Survey");
                                    for(int j = 0 ; j < survey.length(); j++) {
                                        JSONObject survey_obj = survey.getJSONObject(j);
                                        survey_bean=new Survey_bean();
                                        survey_bean.setSurvey_ID(survey_obj.getInt("id"));
                                        survey_bean.setFK_Survey_PID(res2.getInt("id"));
                                        survey_bean.setSurvey_Name(survey_obj.getString("Name"));
                                        survey_bean.setSurvey_Poll_Question(survey_obj.getString("Poll_Question"));
                                        survey_bean.setSurvey_Date(survey_obj.getString("Date"));

                                        JSONObject survey_nested = survey_obj.getJSONObject("Month");
                                        survey_bean.setSurvey_Month(survey_nested.getString("Month_Name"));

                                        survey_Array.add(survey_bean);//set value to array list

                                    }

                                    JSONArray comment = res2.getJSONArray("Politician_Comments");
                                    for(int j = 0 ; j < comment.length(); j++) {
                                        JSONObject comment_obj = comment.getJSONObject(j);
                                        comment_bean=new Comments_bean();
                                        comment_bean.setComments_id(comment_obj.getInt("id"));
                                        comment_bean.setFK_Comments_PID(res2.getInt("id"));
                                        comment_bean.setComments_image(comment_obj.getString("image"));
                                        comment_bean.setComments_name(comment_obj.getString("name"));
                                        comment_bean.setComments_user(comment_obj.getString("user"));
                                        comment_bean.setComments_uploaded_on(comment_obj.getString("uploaded_on"));
                                        comment_bean.setComments_comment(comment_obj.getString("comment"));
                                        comment_bean.setComments_ratings(comment_obj.getString("ratings"));

                                        comments_Array.add(comment_bean);//set value to array list

                                    }

                                }

                                dbHelper.savedetails(politician_Array);  //save server API data to database

                                getDataFrom_db(); //get data from database


                            } else {
                                Toast.makeText(getApplicationContext(), "server is busy please try after some time", Toast.LENGTH_LONG).show();

                            }

                        }catch(JSONException e){
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        System.out.println("RES    >>. volleyError " + volleyError);
                        try {
                            if (volleyError.getClass().equals(TimeoutError.class)) {
                                Toast.makeText(MainActivity.this,
                                        "Oops. Timeout !",
                                        Toast.LENGTH_LONG).show();
                            }
                            ExceptionLog.logWrite();
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                }){
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<String, String>();

                return params;
            }

        };
        RetryPolicy policy = new DefaultRetryPolicy(UrlConfig.SOCKET_TIME_OUT,
                UrlConfig.RETRY,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(stringRequest);



    }

    private void getDataFrom_db() {

        politician_Array =new ArrayList<Politician_bean>();
        politician_Array=dbHelper.get_plitician();
        customAdapter=new CustomAdapter(this,politician_Array);
        politic_list.setAdapter(customAdapter);
        politic_list.setOnItemClickListener(this);


    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        Intent in =new Intent(MainActivity.this,CompleteActivity.class);   //set id and open complete details
        in.putExtra("message",politician_Array.get(position).getPolitic_ID());
        startActivity(in);
    }

    public static class HttpsTrustManager implements X509TrustManager {

        private static TrustManager[] trustManagers;
        private static final X509Certificate[] _AcceptedIssuers = new X509Certificate[]{};

        @Override
        public void checkClientTrusted(
                java.security.cert.X509Certificate[] x509Certificates, String s)
                throws java.security.cert.CertificateException {

        }

        @Override
        public void checkServerTrusted(
                java.security.cert.X509Certificate[] x509Certificates, String s)
                throws java.security.cert.CertificateException {

        }

        public boolean isClientTrusted(X509Certificate[] chain) {
            return true;
        }

        public boolean isServerTrusted(X509Certificate[] chain) {
            return true;
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return _AcceptedIssuers;
        }

        public static void allowAllSSL() {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

            });

            SSLContext context = null;
            if (trustManagers == null) {
                trustManagers = new TrustManager[]{new HttpsTrustManager()};
            }

            try {
                context = SSLContext.getInstance("TLS");
                context.init(null, trustManagers, new SecureRandom());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }

            HttpsURLConnection.setDefaultSSLSocketFactory(context
                    .getSocketFactory());
        }

    }

}
