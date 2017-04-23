package demo.yang.com.baisi.PersonalCenter.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import demo.yang.com.baisi.PersonalCenter.Bean.Recommend_Subscribe_bean;
import demo.yang.com.baisi.R;

/**
 * Created by yxy on 2017/3/15.
 * email:1084625746@qq.com
 */

public class ReCommend_Subscribe_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Recommend_Subscribe_bean> list;
    private Context context;

    public ReCommend_Subscribe_Adapter(List<Recommend_Subscribe_bean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.personal_recommend_subscribe,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).img_head.setImageResource(list.get(position).getImg_id());
        ((MyViewHolder)holder).title_recommend.setText(list.get(position).getTitle_mySubscribe());
        ((MyViewHolder)holder).num_subscribe_recommend.setText(list.get(position).getNum_mySubscribe()+"订阅");
        ((MyViewHolder)holder).num_essence_recommend.setText(list.get(position).getNum_mySubscribe_essence());
        if (position == 0){
            ((MyViewHolder)holder).view.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private ImageView img_head;
        private TextView title_recommend,num_subscribe_recommend,num_essence_recommend;
        private Button button;
        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view_recommend_subscribe);
            img_head = (ImageView) itemView.findViewById(R.id.img_recommend_subscribe);
            title_recommend = (TextView) itemView.findViewById(R.id.title_recommend);
            num_subscribe_recommend = (TextView) itemView.findViewById(R.id.num_subscribe_recommend);
            num_essence_recommend = (TextView) itemView.findViewById(R.id.num_recommend_essence);
            button = (Button) itemView.findViewById(R.id.btn_follow);
        }
    }

}
