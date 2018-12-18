package com.feederfox.ujaval.politician.bean;

public class Polling_bean {

    int Polling_ID;
    int FK_Polling_PID;
    String Polling_Name;
    String Polling_Upvote;
    String Polling_DownVote;
    String Polling_Date;
    String Polling_Month_Name;
    String Polling_Like;
    String Polling_Dislike;


    public int getPolling_ID() {
        return Polling_ID;
    }

    public void setPolling_ID(int polling_ID) {
        Polling_ID = polling_ID;
    }

    public int getFK_Polling_PID() {
        return FK_Polling_PID;
    }

    public void setFK_Polling_PID(int FK_Polling_PID) {
        this.FK_Polling_PID = FK_Polling_PID;
    }

    public String getPolling_Name() {
        return Polling_Name;
    }

    public void setPolling_Name(String polling_Name) {
        Polling_Name = polling_Name;
    }

    public String getPolling_Upvote() {
        return Polling_Upvote;
    }

    public void setPolling_Upvote(String polling_Upvote) {
        Polling_Upvote = polling_Upvote;
    }

    public String getPolling_DownVote() {
        return Polling_DownVote;
    }

    public void setPolling_DownVote(String polling_DownVote) {
        Polling_DownVote = polling_DownVote;
    }

    public String getPolling_Date() {
        return Polling_Date;
    }

    public void setPolling_Date(String polling_Date) {
        Polling_Date = polling_Date;
    }

    public String getPolling_Month_Name() {
        return Polling_Month_Name;
    }

    public void setPolling_Month_Name(String polling_Month_Name) {
        Polling_Month_Name = polling_Month_Name;
    }

    public String getPolling_Like() {
        return Polling_Like;
    }

    public void setPolling_Like(String polling_Like) {
        Polling_Like = polling_Like;
    }

    public String getPolling_Dislike() {
        return Polling_Dislike;
    }

    public void setPolling_Dislike(String polling_Dislike) {
        Polling_Dislike = polling_Dislike;
    }
}
