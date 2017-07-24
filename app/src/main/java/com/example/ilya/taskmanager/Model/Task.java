package com.example.ilya.taskmanager.Model;

/**
 * Created by Ilya on 17.07.2017.
 */

public class Task  {
    public int id;
    public String beginDate;//bookName
    public String endDate;//mainReview
    public String period;//reviewAuthor
    public String description;//shortReview


    public Task(){

    }

    public Task(int id, String beginDate, String endDate, String period, String description) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.period = period;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  description;
    }

}
