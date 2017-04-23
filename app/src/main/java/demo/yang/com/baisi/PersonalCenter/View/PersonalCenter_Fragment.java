package demo.yang.com.baisi.PersonalCenter.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import demo.yang.com.baisi.PersonalCenter.Adapter.MySubscribe_Adapter;
import demo.yang.com.baisi.PersonalCenter.Adapter.ReCommend_Subscribe_Adapter;
import demo.yang.com.baisi.PersonalCenter.Bean.MySubscribe_Bean;
import demo.yang.com.baisi.PersonalCenter.Bean.Recommend_Subscribe_bean;
import demo.yang.com.baisi.R;

public class PersonalCenter_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ViewPager viewPager;
    private MyPagerAdapter myPagerAdapter;
    private ImageView imageView;
    private ImageView[] imagePoints;//viewpager的小圆点
    private LinearLayout mViewPoints;//包裹小圆点的linearlayout
    private RecyclerView recyclerView_mySubscribe,recyclerView_recommend_subscribe;
    private MySubscribe_Adapter mySubscribe_adapter;
    private ReCommend_Subscribe_Adapter reCommend_subscribe_adapter;
    private LinearLayoutManager manager1,manager2;

    private List<MySubscribe_Bean> list_mySubscribe;
    private List<Recommend_Subscribe_bean> list_recommend_subscribe;

    private class MyPagerAdapter extends PagerAdapter {

        private List<View> list;

        public MyPagerAdapter(List<View> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = list.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(list.get(position));
        }
    }


    public PersonalCenter_Fragment() {
        // Required empty public constructor
    }

    public static PersonalCenter_Fragment newInstance(String param1, String param2) {
        PersonalCenter_Fragment fragment = new PersonalCenter_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personalcenter, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPoints = (LinearLayout) view.findViewById(R.id.viewpager_point);
        recyclerView_mySubscribe = (RecyclerView) view.findViewById(R.id.recycler_mySubscribe);
        recyclerView_recommend_subscribe = (RecyclerView) view.findViewById(R.id.recycler_recommend_subscribe);
        List<View> list = new ArrayList<>();
        View view1 = inflater.inflate(R.layout.personal_viewpager_one,null);
        View view2 = inflater.inflate(R.layout.personal_viewpager_two,null);
        list.add(view1);
        list.add(view2);
        imagePoints = new ImageView[list.size()];
        for (int i = 0;i<list.size();i++){
            imageView = new ImageView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20,20);
            layoutParams.setMargins(5,0,5,0);
            imageView.setLayoutParams(layoutParams);
            imagePoints[i] = imageView;
            if (i == 0){
                imagePoints[i].setBackgroundResource(R.drawable.point_select);
            }else{
                imagePoints[i].setBackgroundResource(R.drawable.point_noselect);
            }
            mViewPoints.addView(imagePoints[i]);
        }
        myPagerAdapter = new MyPagerAdapter(list);
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0;i<imagePoints.length;i++){
                    imagePoints[position].setBackgroundResource(R.drawable.point_select);
                    if (i != position){
                        imagePoints[i].setBackgroundResource(R.drawable.point_noselect);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        getMySubscribeData();
        manager1 = new LinearLayoutManager(getContext());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        mySubscribe_adapter = new MySubscribe_Adapter(list_mySubscribe,getContext());
        recyclerView_mySubscribe.setLayoutManager(manager1);
        recyclerView_mySubscribe.setAdapter(mySubscribe_adapter);
        LinearLayout.LayoutParams layoutParams_mySubscribe = (LinearLayout.LayoutParams) recyclerView_mySubscribe.getLayoutParams();
        layoutParams_mySubscribe.height = setRecyclerViewMySubscribeHeight(mySubscribe_adapter);
        recyclerView_mySubscribe.setLayoutParams(layoutParams_mySubscribe);
        getRecommendSubscribe();
        manager2 = new LinearLayoutManager(getContext());
        manager2.setOrientation(LinearLayoutManager.VERTICAL);
        reCommend_subscribe_adapter = new ReCommend_Subscribe_Adapter(list_recommend_subscribe,getContext());
        recyclerView_recommend_subscribe.setLayoutManager(manager2);
        recyclerView_recommend_subscribe.setAdapter(reCommend_subscribe_adapter);
//        recyclerView_recommend_subscribe.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        LinearLayout.LayoutParams layoutParams_recommendSubscribe = (LinearLayout.LayoutParams) recyclerView_recommend_subscribe.getLayoutParams();
        layoutParams_recommendSubscribe.height = setRecyclerViewRecommendSubscribeHeight(reCommend_subscribe_adapter);
        recyclerView_recommend_subscribe.setLayoutParams(layoutParams_recommendSubscribe);
        return view;
    }

    private void getMySubscribeData(){
        list_mySubscribe = new ArrayList<>();
        MySubscribe_Bean mySubscribe_bean = new MySubscribe_Bean(R.drawable.dingyue_gaoxiao,"搞笑","315.6万","10756738");
        list_mySubscribe.add(new MySubscribe_Bean(R.drawable.dingyue_tuchao,"吐槽","162.4万","1756676"));
        list_mySubscribe.add(mySubscribe_bean);
    }

    private void getRecommendSubscribe(){
        list_recommend_subscribe = new ArrayList<>();
        list_recommend_subscribe.add(new Recommend_Subscribe_bean(R.drawable.dingyue_zipai,"自拍","19.5万","101124"));
        list_recommend_subscribe.add(new Recommend_Subscribe_bean(R.drawable.dingyue_nansheng,"男神","16.7万","10732"));
        list_recommend_subscribe.add(new Recommend_Subscribe_bean(R.drawable.dingyue_neihan,"内涵","184.3万","853983"));
        list_recommend_subscribe.add(new Recommend_Subscribe_bean(R.drawable.dingyue_weishipin,"微视频","120.8万","1339239"));
        list_recommend_subscribe.add(new Recommend_Subscribe_bean(R.drawable.dingyue_egao,"恶搞","192.3万","840309"));
    }

    //设置RecyclerView的高度（如何获取每一个item的高度？）
    private int setRecyclerViewRecommendSubscribeHeight(ReCommend_Subscribe_Adapter adapter){
        int totalHeight = 0;
        for (int i = 0;i < adapter.getItemCount();i++){
            int height = 180;
            totalHeight += height;
        }
        return totalHeight;
    }
    private int setRecyclerViewMySubscribeHeight(MySubscribe_Adapter adapter){
        int totalHeight = 0;
        for (int i = 0;i < adapter.getItemCount();i++){
            int height = 180;
            totalHeight += height;
        }
        return totalHeight;
    }

}
