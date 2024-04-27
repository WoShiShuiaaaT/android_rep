package com.example.myapplication;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class RegisterActivity extends AppCompatActivity{
    private EditText username_et;
    private EditText password_et;
    private EditText password_et2;

    private Button submit_bt;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.register_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        username_et=findViewById(R.id.register_editText);
        password_et=findViewById(R.id.register_textPassword);
        password_et2=findViewById(R.id.register_textPassword2);
        submit_bt=findViewById(R.id.submit_button);
        sp=this.getSharedPreferences("user",this.MODE_PRIVATE);

        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=username_et.getText().toString();
                String password=password_et.getText().toString();
                String password2=password_et2.getText().toString();
                if(username.isEmpty()||password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "账号或密码为空", Toast.LENGTH_SHORT).show();
                }
                else if(!password.equals(password2)){
                    Toast.makeText(RegisterActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent();
                    intent.setClass(RegisterActivity.this,WelcomeActivity.class);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("username",username);
                    editor.commit();
                    startActivity(intent);
                }
            }
        });
    }
}
