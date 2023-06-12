package com.firstapp.login_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText user,pass;
    TextView log;
    Button btn;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=findViewById(R.id.user);
        pass=findViewById(R.id.pass);
        btn=findViewById(R.id.btn);
        log=findViewById(R.id.log);
      mAuth =FirebaseAuth.getInstance();

      log.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
              startActivity(intent);
          }
      });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String u =  user.getText().toString();
              String p=pass.getText().toString();

              if(TextUtils.isEmpty(u)){
                  Toast.makeText(MainActivity.this, "Enter the user", Toast.LENGTH_SHORT).show();
                  return;
              }
              if(TextUtils.isEmpty(p)){
                  Toast.makeText(MainActivity.this, "Password", Toast.LENGTH_SHORT).show();
                  return;
              }

                mAuth.createUserWithEmailAndPassword(u, p)

                        .addOnCompleteListener( (OnCompleteListener<AuthResult>) task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "successfull", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}