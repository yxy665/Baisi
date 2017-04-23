package demo.yang.com.baisi.Utils;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * 设置抖动效果
 * Created by yxy on 2017/4/6.
 * email:1084625746@qq.com
 */

public class CustomAnimation extends Animation {
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        t.getMatrix().setTranslate(0,(float)Math.sin(interpolatedTime*50)*20);
        super.applyTransformation(interpolatedTime, t);
    }
}
