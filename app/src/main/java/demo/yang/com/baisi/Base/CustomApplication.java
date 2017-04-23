package demo.yang.com.baisi.Base;

import android.app.Application;
import android.content.Context;

/**
 * Created by yxy on 2017/3/29.
 * email:1084625746@qq.com
 */

public class CustomApplication extends Application {

    private static CustomApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getApplication(){
        return getApplication();
    }

    public static synchronized CustomApplication getContext(){
        return instance;
    }

}
