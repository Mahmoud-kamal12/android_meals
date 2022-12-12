package com.bfcai.meals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registerActivity extends AppCompatActivity {

    // عرفت الفاريابل بتاعتي لي في صفحه التسجيل
    EditText mEmail,mPassword;
    Button mRegisterBtn;
    TextView mLoginBtn;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // عرفت كل عنصر بيساوي ايه في صفحه xml بتاعت التسجيل
        mEmail      = findViewById(R.id.Email);
        mPassword   = findViewById(R.id.password);
        mRegisterBtn= findViewById(R.id.registerBtn);
        mLoginBtn   = findViewById(R.id.createText);
        progressBar = findViewById(R.id.progressBar);

        // عرفت الميثود لي هستخدمها لتسجيل الدخول
        fAuth = FirebaseAuth.getInstance();

        // هنا طلب منه يشوف اول ميخش التطبيق هل المستخدم مسجل اساسا
        // لو اه يخش علي طول علي الصفحه الرئيسيه لو لا يكمل تسجيل حساب
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // لو داس علي زر تسجيل حساب بياخد البيانات لي دخلها المستخدم ويبعتها للفايربيز
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();


                // هنا بيشوف لو التيكست بوكس بتاع الايميل فاضي بيطلب من المستخدم يدخله
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }
                // هنا بيشوف لو التيكست بوكس بتاع الباسورد فاضي بيطلب من المستخدم يدخله
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required.");
                    return;
                }
                // هنا بيشوف لو التيكست بوكس بتاع الباسورد فيه اقل من 6 عناصر بيطلب من اليوزر يزود
                if(password.length() < 6){
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;
                }
                // لو كله تمام بيظهر الدايره لي بتلف لحد ميراجع البيانات ويتاكد انها تمام
                progressBar.setVisibility(View.VISIBLE);

                    //  هنا ببدء ابعت البيانات واعمل حساب في الفايربيز باستخدام ميثود جاهزه
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // لو تمام خلاص ويظهر توست ان الايميل اتكرييت
                        if(task.isSuccessful()){

                            Toast.makeText(registerActivity.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else {
                            // غير كدا يظهر توست وفيها المشكله وايه هي ويخفي الدايره لي بتلف
                            Toast.makeText(registerActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


        // زرار بيروح لصفحه تسجيل الدخول
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),loginActivity.class));
            }
        });

    }
}