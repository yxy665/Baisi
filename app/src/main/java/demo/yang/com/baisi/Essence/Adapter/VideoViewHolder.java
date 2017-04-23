package demo.yang.com.baisi.Essence.Adapter;

import android.view.View;
import android.view.ViewStub;

import demo.yang.com.baisi.R;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by yxy on 2017/4/20.
 * email:1084625746@qq.com
 */

public class VideoViewHolder extends EssenceContentViewHolder {

    public JCVideoPlayerStandard jcVideoPlayerStandard;

    public VideoViewHolder(View itemView){
        super(itemView, TYPE_VIDEO);
    }

    @Override
    public void initSubView(int viewType, ViewStub viewStub) {
        if(viewStub == null){
            throw new IllegalArgumentException("viewStub is null...");
        }
        viewStub.setLayoutResource(R.layout.viewstub_video_body);
        View subView = viewStub.inflate();
        JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) subView.findViewById(R.id.player_list_video);
        if (jcVideoPlayerStandard!=null){
            this.jcVideoPlayerStandard = jcVideoPlayerStandard;
        }
    }
}
