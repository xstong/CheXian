package txs.com.chexian;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import android.view.Window;
import android.view.WindowManager;


/**
 * Created by Administrator on 2016/11/4.
 */

public class WelcomeActivity extends Activity {


    SharedPreferences pref = MyApplication.getInstance().getSharedPreferences("UserInfo", MODE_PRIVATE);
     Boolean  valid=pref.getBoolean("valid",false);




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.welcome_layout);



        new Handler().postDelayed(new Runnable() {
            @Override

            public void run() {
                if (valid) {//自动登录

                    Intent intent = new Intent();
                    intent.setClass(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);


                } else {

                    Intent intent = new Intent();
                    intent.setClass(WelcomeActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
            }
        }, 500);
    }


}




