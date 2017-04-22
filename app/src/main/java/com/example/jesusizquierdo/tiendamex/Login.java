package com.example.jesusizquierdo.tiendamex;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jesusizquierdo.tiendamex.Classes.Person;
import com.example.jesusizquierdo.tiendamex.Dialog.SimpleDialogFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity implements SimpleDialogFragment.OnCompleteListener{
EditText mEmailField,mPasswordField;
    Button login;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    TextView newUserText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailField = (EditText) findViewById(R.id.editTextEmail);
        mPasswordField = (EditText) findViewById(R.id.editText2);
        login =(Button) findViewById(R.id.buttonLogin);
        newUserText = (TextView) findViewById(R.id.textViewSignup);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        newUserText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSimpleDialog();
            }
        });


    }

    public void registerUser(final String email,final String password,final String name){

       // final String email = mEmailField.getText().toString().trim();
        //final String password = mPasswordField.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast toast = Toast.makeText(Login.this,"Enter Email",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast toast = Toast.makeText(Login.this,"Enter Password",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        progressDialog.setMessage("You are being registered");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"REGISTERD!",Toast.LENGTH_SHORT).show();
                            saveInformation(name);
                            progressDialog.dismiss();
                            startActivity(new Intent(Login.this,ScrollingActivity.class));
                        }else{
                            Toast.makeText(Login.this,"failed",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });



    }
    private void loginUser(){
        String email = mEmailField.getText().toString().trim();
        String password = mPasswordField.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Plese enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logining you in...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"logged in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this,ScrollingActivity.class));
                            overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right);
                        }else{
                            Toast.makeText(Login.this,"log fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onComplete(String email, String password, String name) {
        Toast.makeText(Login.this,email + " " + password, Toast.LENGTH_SHORT).show();
        registerUser(email,password,name);
    }
    public void showSimpleDialog(){
        SimpleDialogFragment simpleDialogFragment = new SimpleDialogFragment();
        simpleDialogFragment.show(getSupportFragmentManager(),"Custom Dialog Fragment");

    }
    public void saveInformation(String userName){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Person person = new Person(userName);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            databaseReference.child(user.getUid()).setValue(person);
        }
        Toast.makeText(Login.this,"Information saved",Toast.LENGTH_SHORT).show();

    }

}
