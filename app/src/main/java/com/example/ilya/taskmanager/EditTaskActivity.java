package com.example.ilya.taskmanager;

/**
 * Created by Ilya on 22.07.2017.
 */
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class EditTaskActivity extends Activity {
    HttpClient httpClient;

    EditText etTitle,etDescription;
    TextView tvBeginDate,tvEndDate,tvPeriod;
    Button btnCreateTask, btnAddDate,btnAddEndDate,btnAddPeriodTime;
    int DIALOG_DATE = 1,DIALOG_DATE2 = 2;
    int DIALOG_TIME = 3;
    int myYear = 2017;
    int myMonth = 05;
    int myDay = 05;
    int myHour = 14;
    int myMinute = 35;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);

        etTitle=(EditText)findViewById(R.id.etTitle);
        etDescription=(EditText)findViewById(R.id.etDescription);
        tvBeginDate=(TextView)findViewById(R.id.tvBeginDate);
        tvEndDate=(TextView)findViewById(R.id.tvEndDate);
        tvPeriod=(TextView)findViewById(R.id.tvPeriod);

        btnCreateTask=(Button)findViewById(R.id.btnCreateTask);

        Intent intent = getIntent();

         id = intent.getIntExtra("id",0);
        etTitle.setText(String.valueOf(id));
        etDescription.setText(intent.getStringExtra("description"));
        tvBeginDate.setText(intent.getStringExtra("beginDate"));
        tvEndDate.setText(intent.getStringExtra("endDate"));
        tvPeriod.setText(intent.getStringExtra("period"));

    }




    public void onClickEditTask(View view){

        String id=etTitle.getText().toString();
        String beginDate=tvBeginDate.getText().toString();
        String endDate=tvEndDate.getText().toString();
        String period=tvPeriod.getText().toString();
        String description=etDescription.getText().toString();

        RequestParams params=new RequestParams();
        params.put("id",id);
        params.put("mainReview",endDate);
        params.put("shortReview",description);
        params.put("bookName",beginDate);
        params.put("reviewAuthor",period);
        params.setUseJsonStreamer(true);

        HttpClient.postCreateTask(params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Toast.makeText(EditTaskActivity.this,"Task updated", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(EditTaskActivity.this,MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(EditTaskActivity.this,"Update failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void onClickDeleteTask(View view){
         HttpClient.deleteTask(String.valueOf(id), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Toast.makeText(EditTaskActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(EditTaskActivity.this,MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(EditTaskActivity.this, "No connection", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void onClickPeriod(View view){
        showDialog(DIALOG_TIME);
    }
    public void onClickEndDate(View view){
        showDialog(DIALOG_DATE2);
    }

    public void onClickAddDate(View view){
        showDialog(DIALOG_DATE);
    }

    protected Dialog onCreateDialog(int id){
        if(id==DIALOG_DATE){
            DatePickerDialog tpd=new DatePickerDialog(this, myCallBack, myYear,myMonth,myDay);
            return  tpd;
        }
        if(id==DIALOG_DATE2){
            DatePickerDialog tpd1=new DatePickerDialog(this, myCallBack2, myYear,myMonth,myDay);
            return  tpd1;
        }
        if (id == DIALOG_TIME) {
            TimePickerDialog pd = new TimePickerDialog(this, myCallBack3, myHour, myMinute, true);
            return pd;
        }
        return super.onCreateDialog(id);
    }
    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear+1;
            myDay = dayOfMonth;
            tvBeginDate.setText(myDay + "/" + myMonth + "/" + myYear);
        }
    };
    DatePickerDialog.OnDateSetListener myCallBack2 = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear+1;
            myDay = dayOfMonth;
            tvEndDate.setText(myDay + "/" + myMonth + "/" + myYear);
        }
    };
    TimePickerDialog.OnTimeSetListener myCallBack3 = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            tvPeriod.setText( myHour + ":" + myMinute);
        }
    };
}
