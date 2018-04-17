package com.razerblade.restaurant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private EditText mEmail,mPassword,mRepeat;
    private Button mRegister;
    private ProgressDialog pbDialog;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pbDialog = new ProgressDialog(this);
        mEmail=(EditText)findViewById(R.id.editTextUsernameRegister);
        mPassword=(EditText)findViewById(R.id.editTextPasswordRegister);
        mRepeat=(EditText)findViewById(R.id.editTextRepeatRegister);
        mRegister=(Button)findViewById(R.id.buttonLoginRegister);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEmail.getText().toString().isEmpty()){
                    mEmail.setError("Requied");
                    return;
                }if(mPassword.getText().toString().isEmpty()){
                    mPassword.setError("Requied");
                    return;
                }if(mRepeat.getText().toString().isEmpty()){
                    mRepeat.setError("Requied");
                    return;
                }if(!mPassword.getText().toString().equals(mRepeat.getText().toString())){
                    mRepeat.setError("Not Match");
                    mPassword.setError("Not Match");
                    return;
                }
                pbDialog.setMessage("Proses Mendaftarkan");
                pbDialog.setIndeterminate(true);
                pbDialog.show();
                createUser();
            }
        });

    }
    private void createUser(){
        final String email = mEmail.getText().toString();
        final String passwrod = mPassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email,passwrod)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            pbDialog.dismiss();
                            Log.d("Proses Daftar","Berhasil");
                            currentUser = mAuth.getCurrentUser();
                            startActivity(new Intent(Register.this,Login.class));
                            finish();
                        }else{
                            pbDialog.dismiss();
                            Log.v("Proses Daftar","Gagal");
                            Toast.makeText(Register.this, "Pendaftaran Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
