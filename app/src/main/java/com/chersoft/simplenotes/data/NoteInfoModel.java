package com.chersoft.simplenotes.data;

import java.util.Date;
import java.util.Random;

public class NoteInfoModel {
    private String name;
    private Date date;

    //TODO: remove stub constructor
    public NoteInfoModel(){
        this.name = "note" + new Random().nextInt();
        this.date = new Date();
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "NoteInfoModel{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
