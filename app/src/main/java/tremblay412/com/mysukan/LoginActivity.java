package tremblay412.com.mysukan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText ET_email, ET_password;
    private Button LoginButton;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ET_email = (EditText)findViewById(R.id.ET_email);
        ET_password = (EditText)findViewById(R.id.ET_password);
        LoginButton = (Button)findViewById(R.id.LoginButton);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserLogin();
            }
        });
    }

    private void UserLogin(){
        String email = ET_email.getText().toString();
        String password = ET_password.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Login ...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.hide();
                            Toast.makeText(LoginActivity.this,"You are now logged in",Toast.LENGTH_SHORT).show();
                            Intent myIntent = new Intent(LoginActivity.this,AdminActivity.class);
                            startActivity(myIntent);
                            finish();

                        }else{
                            progressDialog.hide();
                            Toast.makeText(LoginActivity.this,"Login Failed. Please check your info",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
