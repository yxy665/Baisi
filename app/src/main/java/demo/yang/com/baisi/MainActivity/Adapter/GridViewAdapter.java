package demo.yang.com.baisi.MainActivity.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import demo.yang.com.baisi.R;
import demo.yang.com.baisi.Widget.CircleImageView;

/**
 * Created by yxy on 2017/3/9.
 * email:1084625746@qq.com
 */

public class GridViewAdapter extends BaseAdapter {

    private int[] img = {R.drawable.fashipin,R.drawable.fatupian,R.drawable.faduanzi,R.drawable.fashengyin,R.drawable.falianjie};
    private  String[] text = {"发视频","发图片","发段子","发声音","发链接"};

    @Override
    public int getCount() {
        return text.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        if (view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.popupwindow_gridview_item,null);
            viewHolder.imageView = (CircleImageView) view.findViewById(R.id.popupwindow_gridview_item_img);
            viewHolder.textView = (TextView) view.findViewById(R.id.popupwindow_gridview_item_text);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(img[i]);
        viewHolder.textView.setText(text[i]);
        return view;
    }

    class ViewHolder{
        private CircleImageView imageView;
        private TextView textView;
    }

}
