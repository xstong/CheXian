package txs.com.chexian;

import android.app.Application;

/**
 * Created by txs on 2017/2/27.
 */

public class MyApplication extends Application{//获取上下文信息的类
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {

        if (instance == null)
            instance = new MyApplication();

        return instance;
    }
}
