package com.example.ilya.taskmanager;

/**
 * Created by Ilya on 16.07.2017.
 */
import android.app.Activity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;


public class RegisterActivity extends Activity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnRegister;
    private Button btnLinkToLogin;

    private EditText inputEmail;
    private EditText inputPassword;
    private TextInputLayout emailWrapper;
    private TextInputLayout passwordWrapper;

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    HttpClient httpClient;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
        emailWrapper=(TextInputLayout) findViewById(R.id.emailWrapperRegister);
        passwordWrapper=(TextInputLayout)findViewById(R.id.passwordWrapperRegister);





    }


    // Register Button Click event
    public void onClickCreateAccount(View view){

        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (validateEmail(email) && validatePassword(password)) {
            emailWrapper.setErrorEnabled(false);
            passwordWrapper.setErrorEnabled(false);
            //  registerUser
            RequestParams params=new RequestParams();
            params.put("login",email);
            params.put("password",password);
            params.setUseJsonStreamer(true);

            HttpClient.postCreateUser(params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Toast.makeText(RegisterActivity.this,"User created", Toast.LENGTH_SHORT).show();
                    Toast.makeText(RegisterActivity.this,responseBody.toString(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });

            progressDialog.setMessage("Registration...");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            //progressBar.setVisibility(View.GONE);
                            if(!task.isSuccessful()){   //
                                // Toast.makeText(RegisterActivity.this,"reg succes",Toast.LENGTH_SHORT).show();
                                Toast.makeText(RegisterActivity.this, "Registration failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(RegisterActivity.this,"You have successfully registered!",Toast.LENGTH_SHORT).show();
                                 startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                 finish();
                            }
                        }
                    });

           /* RequestParams params=new RequestParams();
            params.put("email",email);
            params.put("password",password);
            params.setUseJsonStreamer(true);

            HttpClient.postCreateUser(params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Toast.makeText(RegisterActivity.this, "Аккаунт создан", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    Toast.makeText(RegisterActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
                }
            });  */

        } else if(!validateEmail(email)){
            emailWrapper.setError("Not a valid email address!");
        }else if(!validatePassword(password)){
            passwordWrapper.setError("Password must be more then 5 characters!");
        }



    }

    // Link to Login Screen
    public void onClickToLoginPage(View view){

        Intent i = new Intent(getApplicationContext(),
                LoginActivity.class); //LoginActivity
        startActivity(i);
        finish();
    }
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean validatePassword(String password) {
        return password.length() > 5;
    }



}

