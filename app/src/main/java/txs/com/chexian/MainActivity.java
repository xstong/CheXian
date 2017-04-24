package txs.com.chexian;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import txs.com.chexian.bean.User;

import static android.content.ContentValues.TAG;
import static java.lang.Boolean.FALSE;
import static txs.com.chexian.Tools.UserSerialize.deSerialization;

/**
 * 主界面
 */

public class MainActivity extends Activity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.hahah);
        tv=(TextView)findViewById(R.id.textViewUser);

        super.onCreate(savedInstanceState);

        SharedPreferences pref = MyApplication.getInstance().getSharedPreferences("UserInfo", MODE_PRIVATE);
        /**
         *
         * 获取用户信息
         * 这个信息是登陆的时候存在sharedpreference 里的
         *
         editor.putString("Usr_userName",user.getUsr_userName());
         editor.putInt("Usr_id",user.getUsr_id());
         editor.putString("Usr_phone",user.getUsr_phone());
         editor.putString("Usr_lastTime",user.getUsr_lastTime());
         editor.putString("Usr_lastTime",user.getUsr_createTime());
         editor.putString("Usr_lastIP",user.getUsr_lastIP());
         *
         */
         tv.setText("username:"+pref.getString("Usr_userName","")+
                    "userid"+pref.getInt("Usr_id",0));

        Button mbutton=(Button) findViewById(R.id.button3);
         mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref=  MyApplication.getInstance().getSharedPreferences("UserInfo",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                editor.putBoolean("valid",false);
                editor.commit();
                int pid = android.os.Process.myPid();	//获取当前应用程序的PID
                android.os.Process.killProcess(pid);
            }
        });


    }


}
