package demo.yang.com.baisi.Essence.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import demo.yang.com.baisi.R;
import demo.yang.com.baisi.Widget.CircleImageView;
import demo.yang.com.baisi.Widget.ExpandTextView;

/**
 * Created by yxy on 2017/3/23.
 * email:1084625746@qq.com
 */

public abstract class EssenceContentViewHolder extends RecyclerView.ViewHolder {

    public final static int TYPE_IMAGE = 2;
    public final static int TYPE_VIDEO = 3;
    public final static int TYPE_ALL = 4;
    public int viewType;
    public ImageView img_love,img_hate,img_share,img_comment;
    public CircleImageView head;
    public TextView user_name,create_time,text_love,text_hate,text_share,text_comment;
    public ExpandTextView content;

    public EssenceContentViewHolder(View itemView,int viewtype) {
        super(itemView);
        this.viewType = viewtype;
        ViewStub viewStub = (ViewStub) itemView.findViewById(R.id.viewStub);
        initSubView(viewType,viewStub);
        head = (CircleImageView) itemView.findViewById(R.id.essence_content_img_head);
        img_love = (ImageView) itemView.findViewById(R.id.essence_content_love);
        img_hate = (ImageView) itemView.findViewById(R.id.essence_content_hate);
        img_share = (ImageView) itemView.findViewById(R.id.essence_content_share);
        img_comment = (ImageView) itemView.findViewById(R.id.essence_content_comment);
        user_name = (TextView) itemView.findViewById(R.id.essence_content_user_name);
        create_time = (TextView) itemView.findViewById(R.id.essence_content_create_time);
        content = (ExpandTextView) itemView.findViewById(R.id.essence_content);
        content.setOnExpandTextViewClicklistener(new ExpandTextView.OnExpandTextViewClicklistener() {
            @Override
            public void onExpandTextViewClick() {

            }
        });
        text_love = (TextView) itemView.findViewById(R.id.essence_content_num_love);
        text_hate = (TextView) itemView.findViewById(R.id.essence_content_num_hate);
        text_share = (TextView) itemView.findViewById(R.id.essence_content_num_share);
        text_comment = (TextView) itemView.findViewById(R.id.essence_content_num_comment);
    }

    public abstract void initSubView(int viewType, ViewStub viewStub);
}
