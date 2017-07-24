package com.example.ilya.taskmanager;

/**
 * Created by Ilya on 21.07.2017.
 */
import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import cz.msebera.android.httpclient.HttpEntity;

public class HttpClient {

    private static String BASE_URL = "http://testappspring.herokuapp.com"; //private static String BASE_URL = "http://testappspring.herokuapp.com";
    private static String ALL_TASK = "/reviews";                                        //"http://taskmanager-15.apphb.com/api/sessions"


    private static  AsyncHttpClient client=new AsyncHttpClient();

    public static void postCreateUser(RequestParams params,AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.post(BASE_URL,params, asyncHttpResponseHandler);
    }
    public static void postCreateTask(RequestParams params,AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.post(BASE_URL+ALL_TASK,params, asyncHttpResponseHandler);
    }
    public static void getTasks(RequestParams params,AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.get("url",params, asyncHttpResponseHandler);
    }
    public static void getAllTask(RequestParams params, AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.get(BASE_URL+ALL_TASK,params,asyncHttpResponseHandler);

    }
    public static void deleteTask(String taskID,AsyncHttpResponseHandler asyncHttpResponseHandler){
        client.delete(BASE_URL+ALL_TASK+"/"+taskID,asyncHttpResponseHandler);
    }
}
