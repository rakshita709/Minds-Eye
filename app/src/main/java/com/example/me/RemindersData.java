package com.example.me;


import java.text.SimpleDateFormat;
import java.util.Date;

public class RemindersData  {

    String title;
    Long time;
    Boolean everyday;

    public RemindersData(String title, Long time,  Boolean everyday)
    {
        this.title = title;
        this.everyday = everyday;
        this.time = time;
    }

    String getTitle()
    {
        return title;
    }

    String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
        return simpleDateFormat.format(new Date(time));
    }

    String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(new Date(time));
    }

    String getEveryday() {
        if(everyday)
            return "YES";
        else
            return "NO";
    }

}
