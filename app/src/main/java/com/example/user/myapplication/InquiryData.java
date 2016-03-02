package com.example.user.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by Faisal on 2/29/2016.
 */
public class InquiryData implements Serializable
{
    private int userID;

    private String name;
    private int phone;
    private String date;
    private String institute;
    private String subject;
    private String supervisor;
    private String replyDate;
    private String signature;





    public int getUserID()
    {
        return userID;
    }

    public String getName()
    {
        return name;
    }

    public int getPhone()
    {
        return phone;
    }

    public String getDate()
    {
        return date;
    }

    public String getInstitute()
    {
        return institute;
    }

    public String getSubject()
    {
        return subject;
    }

    public String getSupervisor()
    {
        return supervisor;
    }

    public String getReplyDate()
    {
        return replyDate;
    }

    public Bitmap getSignature()
    {
        byte[] decodedString = Base64.decode(signature, Base64.NO_WRAP);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        Log.d("image: ",signature);
        return decodedByte;
    }


    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPhone(int phone)
    {
        this.phone = phone;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public void setInstitute(String institute)
    {
        this.institute = institute;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public void setSupervisor(String supervisor)
    {
        this.supervisor = supervisor;
    }

    public void setReplyDate(String replyDate)
    {
        this.replyDate = replyDate;
    }

    public void setSignature(String signature)
    {
        this.signature = signature;
    }
}
