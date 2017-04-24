package txs.com.chexian;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import txs.com.chexian.Service.ChexianService;
import txs.com.chexian.Tools.ClearEditText;
import txs.com.chexian.Tools.SelfDialog;
import txs.com.chexian.Tools.StaticStrings;
import txs.com.chexian.Tools.RegularExpression;
import txs.com.chexian.bean.User;

/**
 * Created by Administrator on 2016/11/22.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnFocusChangeListener,TextWatcher {

    ClearEditText username;
    ClearEditText password;
    ClearEditText passwordConfirm;
    ClearEditText telephone;
    ClearEditText confirmCode;

    Button getCode;
    Button register;
    SelfDialog selfDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        initer();

    

        getCode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = initNewUser();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(StaticStrings.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ChexianService mChexianService = retrofit.create(ChexianService.class);

                Call<Integer> call = mChexianService.register(user.getUsr_userName(), user.getUsr_passWord(), user.getUsr_passWord());
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {

                        if (1==response.body()) {

                            Dialogshow();


                        } else {
                            Toast.makeText(getApplicationContext(), "请检查网络",
                                    Toast.LENGTH_SHORT).show();
                        }
                        Log.e("===", "return-:" + response.body());
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Log.e("RetrofitException", t.toString());
                    }
                });

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    void Dialogshow() {
        selfDialog = new SelfDialog(RegisterActivity.this);
        selfDialog.setTitle("提示");
        selfDialog.setMessage("注册成功,是否去登陆？");
        selfDialog.setYesOnclickListener("返回登录", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        selfDialog.setNoOnclickListener("留在本页", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                selfDialog.dismiss();
            }
        });
        selfDialog.show();
    }

    User initNewUser() {
        User user = new User();
        user.setUsr_phone(telephone.getText().toString());
        user.setUsr_passWord(password.getText().toString());
        user.setUsr_userName(username.getText().toString());
        return user;

    }

    void initer() {

        username = (ClearEditText) findViewById(R.id.regUserName);
        password = (ClearEditText) findViewById(R.id.regPwd);
        passwordConfirm = (ClearEditText) findViewById(R.id.regPwdConfirm);
        telephone = (ClearEditText) findViewById(R.id.regPhone);
        confirmCode = (ClearEditText) findViewById(R.id.regConfirmCode);
        getCode = (Button) findViewById(R.id.getCode);
        register = (Button) findViewById(R.id.register);
        username.setOnFocusChangeListener(this);
        password.setOnFocusChangeListener(this);
        passwordConfirm.setOnFocusChangeListener(this);
        telephone.setOnFocusChangeListener(this);
        username.addTextChangedListener(this);
        password.addTextChangedListener(this);
        passwordConfirm.addTextChangedListener(this);
        telephone.addTextChangedListener(this);
        register.setEnabled(false);




    }

    @Override



    public void onFocusChange(View v, boolean hasFocus) {
        int id = v.getId();
        if (id == R.id.regPhone && hasFocus == false) {
            String phoneString = telephone.getText().toString();
            if ((!RegularExpression.isPhoneNumberValid(phoneString)) && (!phoneString.equals(""))) {
                Toast.makeText(getApplicationContext(), "请输入正确的电话号码格式",
                        Toast.LENGTH_SHORT).show();


            }
            if (phoneString.equals("")) {
                Toast.makeText(getApplicationContext(), "电话号码不能为空",
                        Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.regPwdConfirm && hasFocus == false) {
            String passwordString = password.getText().toString();
            String passwordConfirmString = passwordConfirm.getText().toString();
            if (!passwordString.equals(passwordConfirmString)) {
                Toast.makeText(getApplicationContext(), "输入的两次密码不同，请重新输入",
                        Toast.LENGTH_SHORT).show();

            }
            if (passwordString.equals("")) {
                Toast.makeText(getApplicationContext(), "密码不能为空",
                        Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.regUserName && hasFocus == false) {
            String usernameString = username.getText().toString();
            if (usernameString.equals("")) {
                Toast.makeText(getApplicationContext(), "用户名不能为空",
                        Toast.LENGTH_SHORT).show();

            } else if (RegularExpression.isChineseCharactor(usernameString)) {

                Toast.makeText(getApplicationContext(), "用户名不能为中文",
                        Toast.LENGTH_SHORT).show();
            } else {
                hasUserName(usernameString);
            }
        } else if (id == R.id.password && hasFocus == false) {
            String passwordString = password.getText().toString();
            if (passwordString.length() <= 6) {
                Toast.makeText(getApplicationContext(), "密码不能小于6位",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }


    /**
     * 检查用户是否存在
     */
    public void  hasUserName(String username) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StaticStrings.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ChexianService mChexianService = retrofit.create(ChexianService.class);
        Call<String> call = mChexianService.searchUser(username,"1");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                if(Integer.parseInt(response.body().toString())==1){
                    Toast.makeText(getApplicationContext(), "用户已存在",
                            Toast.LENGTH_SHORT).show();


                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("RetrofitException", t.toString());
            }
        });


    }
    /**
     *
     * 检查登陆条件
     * ClearEditText username;
     ClearEditText password;
     ClearEditText passwordConfirm;
     ClearEditText telephone;
     ClearEditText confirmCode;
     */


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

        if (username.getText().toString().equals("")
                ||password.getText().toString().equals("")
                ||passwordConfirm.getText().toString().equals("")
                ||telephone.getText().toString().equals("")
                ||!RegularExpression.isPhoneNumberValid(telephone.getText().toString())
                ||RegularExpression.isChineseCharactor(username.getText().toString())
             ){

            register.setEnabled(false);

        }else {
            register.setEnabled(true);
        }


    }

    /**
     *
     * 获取验证码
     */






}
