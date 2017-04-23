package demo.yang.com.baisi.Essence.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import demo.yang.com.baisi.Essence.Bean.Essence_content_duanzi;
import demo.yang.com.baisi.R;

/**
 * Created by yxy on 2017/3/16.
 * email:1084625746@qq.com
 */

public class Essence_Duanzi_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Essence_content_duanzi> list = new ArrayList<>();
    private Context context;

    public Essence_Duanzi_Adapter(Context context) {
        this.context = context;
    }

    public void setList(List<Essence_content_duanzi> mlist){
        this.list = mlist;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.essence_content_item,parent,false);
        RecyclerView.ViewHolder holder = new ImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getProfile_image()).diskCacheStrategy(DiskCacheStrategy.ALL)//缓存多个尺寸
                .thumbnail(0.1f)//先显示缩略图  缩略图为原图的1/10
                .error(R.mipmap.ic_launcher)
                .dontAnimate()
                .into((((EssenceContentViewHolder)holder).head));
        ((EssenceContentViewHolder)holder).user_name.setText(list.get(position).getScreen_name());
        ((EssenceContentViewHolder)holder).create_time.setText(list.get(position).getCreate_time());
        ((EssenceContentViewHolder)holder).content.setText(list.get(position).getText());
        ((EssenceContentViewHolder)holder).text_love.setText(list.get(position).getLove()+"");
        ((EssenceContentViewHolder)holder).text_hate.setText(list.get(position).getHate()+"");
        ((EssenceContentViewHolder)holder).text_share.setText(list.get(position).getRepost()+"");
        ((EssenceContentViewHolder)holder).text_comment.setText(list.get(position).getComment()+"");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<Essence_content_duanzi> newDatas){
        list.clear();
        list.addAll(newDatas);
        notifyDataSetChanged();
    }

    public void addData(List<Essence_content_duanzi> newDatas){
        list.addAll(newDatas);
        notifyDataSetChanged();
    }



}
