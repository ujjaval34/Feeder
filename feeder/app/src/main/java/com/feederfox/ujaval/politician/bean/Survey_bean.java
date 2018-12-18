package com.feederfox.ujaval.politician.bean;

public class Survey_bean {

    int Survey_ID;
    int FK_Survey_PID;
    String Survey_Name;
    String Survey_Poll_Question;
    String Survey_Date;
    String Survey_Month;


    public int getSurvey_ID() {
        return Survey_ID;
    }

    public void setSurvey_ID(int survey_ID) {
        Survey_ID = survey_ID;
    }

    public int getFK_Survey_PID() {
        return FK_Survey_PID;
    }

    public void setFK_Survey_PID(int FK_Survey_PID) {
        this.FK_Survey_PID = FK_Survey_PID;
    }

    public String getSurvey_Name() {
        return Survey_Name;
    }

    public void setSurvey_Name(String survey_Name) {
        Survey_Name = survey_Name;
    }

    public String getSurvey_Poll_Question() {
        return Survey_Poll_Question;
    }

    public void setSurvey_Poll_Question(String survey_Poll_Question) {
        Survey_Poll_Question = survey_Poll_Question;
    }

    public String getSurvey_Date() {
        return Survey_Date;
    }

    public void setSurvey_Date(String survey_Date) {
        Survey_Date = survey_Date;
    }

    public String getSurvey_Month() {
        return Survey_Month;
    }

    public void setSurvey_Month(String survey_Month) {
        Survey_Month = survey_Month;
    }
}
