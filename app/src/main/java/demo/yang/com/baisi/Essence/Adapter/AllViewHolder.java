package demo.yang.com.baisi.Essence.Adapter;

import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import demo.yang.com.baisi.R;
import demo.yang.com.baisi.Widget.MultiImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by yxy on 2017/4/21.
 * email:1084625746@qq.com
 */

public class AllViewHolder extends EssenceContentViewHolder {

    public JCVideoPlayerStandard jcVideoPlayerStandard;
    public MultiImageView multiImageView;
    public TextView textView;

    public AllViewHolder(View itemView) {
        super(itemView, TYPE_ALL);
    }

    @Override
    public void initSubView(int viewType, ViewStub viewStub) {
        if(viewStub == null){
            throw new IllegalArgumentException("viewStub is null...");
        }
        viewStub.setLayoutResource(R.layout.viewstub_all_body);
        View subView = viewStub.inflate();
        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) subView.findViewById(R.id.player_list_video_all);
        MultiImageView multiImageView = (MultiImageView) subView.findViewById(R.id.multiImagView_all);
        textView = (TextView) subView.findViewById(R.id.textview_quantu_all);
        if (jcVideoPlayerStandard!=null){
            this.jcVideoPlayerStandard = jcVideoPlayerStandard;
        }
        if(multiImageView != null){
            this.multiImageView = multiImageView;
        }
    }
}
