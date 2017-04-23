package demo.yang.com.baisi.PersonalCenter.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import demo.yang.com.baisi.PersonalCenter.Bean.MySubscribe_Bean;
import demo.yang.com.baisi.R;

/**
 * Created by yxy on 2017/3/15.
 * email:1084625746@qq.com
 */

public class MySubscribe_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MySubscribe_Bean> list;
    private Context context;

    public MySubscribe_Adapter(List<MySubscribe_Bean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.personal_my_subscribe,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).head.setImageResource(list.get(position).getImg_id());
        ((MyViewHolder)holder).title_mySubscribe.setText(list.get(position).getTitle_mySubscribe());
        ((MyViewHolder)holder).num_mySubscribe.setText(list.get(position).getNum_mySubscribe()+"订阅");
        ((MyViewHolder)holder).num_mySubscribe_essence.setText(list.get(position).getNum_mySubscribe_essence());
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
        private ImageView head;
        private TextView title_mySubscribe,num_mySubscribe,num_mySubscribe_essence;
        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view_my_subscribe);
            head = (ImageView) itemView.findViewById(R.id.img_my_subscribe);
            title_mySubscribe = (TextView) itemView.findViewById(R.id.title_my_subscribe);
            num_mySubscribe = (TextView) itemView.findViewById(R.id.num_my_subscribe);
            num_mySubscribe_essence = (TextView) itemView.findViewById(R.id.num_my_subscribe_essence);
        }
    }

}
