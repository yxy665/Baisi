package demo.yang.com.baisi.Utils;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by yxy on 2017/3/9.
 * email:1084625746@qq.com
 */

public class Screen {

    private int width;
    private int height;

    public Screen(Activity context){
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;
        height = metrics.heightPixels;
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public int getWidth(){
        return width;
    }

    /**
     * 获取屏幕高度
     * @return
     */
    public int getHeight(){
        return height;
    }
}
