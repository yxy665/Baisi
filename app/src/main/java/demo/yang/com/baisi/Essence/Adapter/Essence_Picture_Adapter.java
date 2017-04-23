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

import demo.yang.com.baisi.Essence.Bean.Essence_content_picture;
import demo.yang.com.baisi.Essence.View.Image_Activity;
import demo.yang.com.baisi.R;
import demo.yang.com.baisi.Widget.MultiImageView;

/**
 * Created by yxy on 2017/3/30.
 * email:1084625746@qq.com
 */

public class Essence_Picture_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<Essence_content_picture> list = new ArrayList<>();
    private Context context;

    public Essence_Picture_Adapter(Context context) {
        this.context = context;
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
            switch (((EssenceContentViewHolder)holder).viewType) {
                case EssenceContentViewHolder.TYPE_IMAGE:// 处理图片
                    if(holder instanceof ImageViewHolder){
                        final List<String> photos = new ArrayList<>();
                        photos.add(list.get(position).getImage0());
                        if (photos!=null) {
                            ((ImageViewHolder)holder).multiImageView.setVisibility(View.VISIBLE);
                            if (IsGIF(list.get(position).getImage0())){
                                ((ImageViewHolder)holder).textView.setVisibility(View.GONE);//查看全图的背景
                            }else {
                                ((ImageViewHolder)holder).textView.setVisibility(View.VISIBLE);//查看全图的背景
                            }
                            ((ImageViewHolder)holder).multiImageView.setList(photos);
                            ((ImageViewHolder)holder).multiImageView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    //                                    Toast.makeText(context,"点击了图片！",Toast.LENGTH_SHORT).show();
                                    //imagesize是作为loading时的图片size
                                    Image_Activity.ImageSize imageSize = new Image_Activity.ImageSize(view.getMeasuredWidth(), view.getMeasuredHeight());
                                    Image_Activity.startImagePagerActivity(context, photos, position, imageSize);
                                }
                            });

                            ((ImageViewHolder) holder).multiImageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    Intent intent=new Intent(context,Message_detailed_Activity.class);
//                                    Bundle bundle=new Bundle();
//                                    bundle.putSerializable("tiezi",circleItem);
//                                    intent.putExtras(bundle);
//                                    context.startActivity(intent);
                                }
                            });
                        } else {
                            ((ImageViewHolder)holder).multiImageView.setVisibility(View.GONE);
                        }
                    }
                    break;
                default:
                    break;
            }



    }

    /**
     * 判断图片是否为GIF图片
     */
    private boolean IsGIF(String url){
        if (url.substring(url.length()-3,url.length()).equals("gif")){
//            Log.e(TAG, "IsGIF: "+ url.substring(url.length()-3,url.length()));
            return true;
        }else {
//            Log.e(TAG, "IsNotGIF: "+ url.substring(url.length()-3,url.length()));
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<Essence_content_picture> newDatas){
        list.clear();
        list.addAll(newDatas);
        notifyDataSetChanged();
    }

    public void addData(List<Essence_content_picture> newDatas){
        list.addAll(newDatas);
        notifyDataSetChanged();
    }
}
