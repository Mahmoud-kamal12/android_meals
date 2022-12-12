package com.bfcai.meals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // عرفت ميثود جاهزه ده عباره عن ميثود بتستني مده معينه وبعدين بتزود امر ما
        // وهنا طلبت منها انها تستني 4 ثواني وبعدين هتتنقل من صفحه سبلاش سكرين ل الصفحه الرئيسيةؤ
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                    Intent intent = new Intent(splashActivity.this, loginActivity.class);
                    startActivity(intent);
                    finish();


            }
        },4000);

    }

}