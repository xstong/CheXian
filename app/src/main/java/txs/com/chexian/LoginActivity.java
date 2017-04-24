package txs.com.chexian;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;


import java.io.IOException;
import java.util.Set;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import txs.com.chexian.Service.ChexianService;
import txs.com.chexian.Tools.ClearEditText;
import txs.com.chexian.Tools.LoginTool;
import txs.com.chexian.Tools.RegularExpression;
import txs.com.chexian.Tools.StaticStrings;
import txs.com.chexian.bean.User;


import static txs.com.chexian.Tools.UserSerialize.serialize;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    ClearEditText edtTxtPassword;
    ClearEditText edtTxtTelephone;
    Button button;
    TextView toRegister;
    String loginFlag;
    LoginTool lt = new LoginTool();

    /**
     * 初始化控件
     */
    void init() {
        edtTxtPassword = (ClearEditText) findViewById(R.id.password);
        edtTxtTelephone = (ClearEditText) findViewById(R.id.telephone);
        button = (Button) findViewById(R.id.button);
        toRegister = (TextView) findViewById(R.id.toRegister);
        //将TextView的显示文字设置为SpannableString
        toRegister.setText(getClickableSpan());
        //设置该句使文本的超连接起作用
        toRegister.setMovementMethod(LinkMovementMethod.getInstance());

    }

    void initUser() {
        String loginText = edtTxtTelephone.getText().toString();

        lt.setLoginFlag(getLoginFlag(loginText));
        lt.setLoginString(edtTxtTelephone.getText().toString());
        lt.setLoginPassWord(edtTxtPassword.getText().toString());


    }

    /**
     * 判断登陆的字符串是电话还是用户名
     *
     * @param loginText
     * @return
     */
    private String getLoginFlag(String loginText) {
        if (RegularExpression.isPhoneNumberValid(loginText)) {
            loginFlag = "2";//是电话
        } else
            loginFlag = "1";//是用户名
        return loginFlag;

    }

    /**
     * 设置下划线文字颜色等
     *
     * @return
     */
    private SpannableString getClickableSpan() {
        SpannableString spanStr = new SpannableString("新用户注册");
        //设置下划线文字
        spanStr.setSpan(new UnderlineSpan(), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的单击事件
        spanStr.setSpan(new ClickableSpan() {

            @Override
            public void onClick(View widget) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        }, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的前景色
        spanStr.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanStr;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();

        /**
         * 登录操作
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initUser();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(StaticStrings.baseURL)

                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ChexianService loginService = retrofit.create(ChexianService.class);


                Call<User> call = loginService.login(lt.getLoginString(), lt.getLoginPassWord(), lt.getLoginFlag());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        User user = response.body();


                        if (!"".equals(user.getUsr_userName())) {
                            SharedPreferences pref = MyApplication.getInstance().getSharedPreferences("UserInfo", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putBoolean("valid", true);
                            editor.putString("Usr_userName",user.getUsr_userName());
                            editor.putInt("Usr_id",user.getUsr_id());
                            editor.putString("Usr_phone",user.getUsr_phone());
                      //    editor.putStringSet("tUsr_orderList", (Set<String>) user.getUsr_orderList());
                            editor.putString("Usr_lastTime",user.getUsr_lastTime());
                            editor.putString("Usr_lastTime",user.getUsr_createTime());
                            editor.putString("Usr_lastIP",user.getUsr_lastIP());



                            editor.commit();
                            Intent intent = new Intent();
                            intent.setClass(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "系统繁忙请稍后",
                                    Toast.LENGTH_SHORT).show();


                        }


                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.e("RetrofitException", t.toString());
                    }
                });
            }
        });
    }

}

