package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText username_et;
    private EditText password_et;
    private Button login_bt;
    private TextView register_tv;
    private TextView help_tv;
    private TextView forget_tv;
    private SharedPreferences sp;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        username_et=findViewById(R.id.user_editText);
        password_et=findViewById(R.id.password_editText);
        login_bt=findViewById(R.id.login_button);
        register_tv=findViewById(R.id.register_textView);
        help_tv=findViewById(R.id.help_textView);
        forget_tv=findViewById(R.id.forget_textView);
        sp=this.getSharedPreferences("user",this.MODE_PRIVATE);

        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=username_et.getText().toString();
                String password=password_et.getText().toString();
                if(username.isEmpty()||password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
                else if(username.equals("admin")&&password.equals("123")){
                    Intent intent=new Intent();
                    intent.setClass(MainActivity.this, WelcomeActivity.class);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("username",username);
                    editor.commit();
                    startActivity(intent);
                }
            }
        });
        register_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        help_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"敬请期待",Toast.LENGTH_SHORT).show();
            }
        });
        forget_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"忘记过去，重新开始",Toast.LENGTH_LONG).show();
            }
        });
    }

}