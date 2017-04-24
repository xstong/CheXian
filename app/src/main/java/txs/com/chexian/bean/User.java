package txs.com.chexian.bean;
import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import txs.com.chexian.MyApplication;

import static android.content.Context.MODE_PRIVATE;
import static okhttp3.internal.Internal.instance;

public class User implements Serializable {

   private static User instance = null;
    private SharedPreferences pref = MyApplication.getInstance().getSharedPreferences("userinfo", MODE_PRIVATE);
    SharedPreferences.Editor editor = pref.edit();

    private int usr_id;
    private String usr_userName;
    private String usr_passWord;
    private String usr_phone;
    private String  usr_createTime;
    private String  usr_lastTime;
    private String usr_lastIP;
    private List<String > usr_orderList;
    public int getUsr_id() {
        return usr_id;
    }
    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }
    public String getUsr_userName() {
        return usr_userName;
    }
    public void setUsr_userName(String usr_userName) {
        this.usr_userName = usr_userName;
    }
    public String getUsr_passWord() {
        return usr_passWord;
    }
    public void setUsr_passWord(String usr_passWord) {
        this.usr_passWord = usr_passWord;
    }
    public String getUsr_phone() {
        return usr_phone;
    }
    public void setUsr_phone(String usr_phone) {
        this.usr_phone = usr_phone;
    }

    public String getUsr_lastTime() {
        return usr_lastTime;
    }

    public void setUsr_lastTime(String usr_lastTime) {
        this.usr_lastTime = usr_lastTime;
    }

    public String getUsr_createTime() {
        return usr_createTime;
    }

    public void setUsr_createTime(String usr_createTime) {
        this.usr_createTime = usr_createTime;
    }

    public String getUsr_lastIP() {
        return usr_lastIP;
    }
    public void setUsr_lastIP(String usr_lastIP) {
        this.usr_lastIP = usr_lastIP;
    }
    public List<String> getUsr_orderList() {
        return usr_orderList;
    }
    public void setUsr_orderList(List<String> usr_orderList) {
        this.usr_orderList = usr_orderList;
    }

  public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

     public User() {
        if (pref == null) {
            pref = MyApplication.getInstance().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        }

    }

    @Override
    public String toString() {
        return "User{" +
                ", usr_id=" + usr_id +
                ", usr_userName='" + usr_userName + '\'' +
                ", usr_passWord='" + usr_passWord + '\'' +
                ", usr_phone='" + usr_phone + '\'' +
                ", usr_createTime='" + usr_createTime + '\'' +
                ", usr_lastTime='" + usr_lastTime + '\'' +
                ", usr_lastIP='" + usr_lastIP + '\'' +
                ", usr_orderList=" + usr_orderList +
                '}';
    }
}