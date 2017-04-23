package demo.yang.com.baisi.Essence.Adapter;

import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import demo.yang.com.baisi.R;
import demo.yang.com.baisi.Widget.MultiImageView;

/**
 * Created by yxy on 2017/3/23.
 * email:1084625746@qq.com
 */

public class ImageViewHolder extends EssenceContentViewHolder {
    /** 图片*/
    public MultiImageView multiImageView;
    public TextView textView;

    public ImageViewHolder(View itemView){
        super(itemView, TYPE_IMAGE);
    }

    @Override
    public void initSubView(int viewType, ViewStub viewStub) {
        if(viewStub == null){
            throw new IllegalArgumentException("viewStub is null...");
        }
        viewStub.setLayoutResource(R.layout.viewstub_imageview_body);
        View subView = viewStub.inflate();
        MultiImageView multiImageView = (MultiImageView) subView.findViewById(R.id.multiImagView);
        textView = (TextView) subView.findViewById(R.id.textview_quantu);
        if(multiImageView != null){
            this.multiImageView = multiImageView;
        }
    }
}
