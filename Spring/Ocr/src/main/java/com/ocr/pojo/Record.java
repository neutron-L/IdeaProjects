package com.ocr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private int id;
    private int userId;
    private File photo;
    private String result;
    private Date time;

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}