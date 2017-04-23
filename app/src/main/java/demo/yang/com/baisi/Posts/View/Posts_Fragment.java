package demo.yang.com.baisi.Posts.View;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.yang.com.baisi.Posts.Adapter.PostsPagerAdapter;
import demo.yang.com.baisi.R;

public class Posts_Fragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Posts_Fragment() {
        // Required empty public constructor
    }

    public static Posts_Fragment newInstance(String param1, String param2) {
        Posts_Fragment fragment = new Posts_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.posts_top_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.posts_top_viewpager);
        PostsPagerAdapter postsPagerAdapter = new PostsPagerAdapter(getChildFragmentManager());
        postsPagerAdapter.addFragment(Posts_content_Fragment.newInstance("quanbu"),"全部");
        postsPagerAdapter.addFragment(Posts_content_Fragment.newInstance("shipin"),"视频");
        postsPagerAdapter.addFragment(Posts_content_Fragment.newInstance("tupian"),"图片");
        postsPagerAdapter.addFragment(Posts_content_Fragment.newInstance("duanzi"),"段子");
        postsPagerAdapter.addFragment(Posts_content_Fragment.newInstance("toupiao"),"投票");
        postsPagerAdapter.addFragment(Posts_content_Fragment.newInstance("hudongqu"),"互动区");
        postsPagerAdapter.addFragment(Posts_content_Fragment.newInstance("wanghong"),"网红");
        postsPagerAdapter.addFragment(Posts_content_Fragment.newInstance("lengzhishi"),"冷知识");
        postsPagerAdapter.addFragment(Posts_content_Fragment.newInstance("meinv"),"美女");
        postsPagerAdapter.addFragment(Posts_content_Fragment.newInstance("shengyin"),"声音");
        postsPagerAdapter.addFragment(Posts_content_Fragment.newInstance("youxi"),"游戏");
        viewPager.setAdapter(postsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

}
