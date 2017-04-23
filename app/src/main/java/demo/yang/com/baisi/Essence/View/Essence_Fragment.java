package demo.yang.com.baisi.Essence.View;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.yang.com.baisi.Essence.Adapter.EssencePagerAdapter;
import demo.yang.com.baisi.R;


public class Essence_Fragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Essence_Fragment() {
        // Required empty public constructor
    }

    public static Essence_Fragment newInstance(String param1, String param2) {
        Essence_Fragment fragment = new Essence_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_essence, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.essence_top_viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.essence_top_tablayout);
        EssencePagerAdapter essencePagerAdapter = new EssencePagerAdapter(getChildFragmentManager());
        essencePagerAdapter.addFragment(Essence_content_Fragment.newInstance("tuijian"),"推荐");
        essencePagerAdapter.addFragment(Essence_content_Fragment.newInstance("shipin"),"视频");
        essencePagerAdapter.addFragment(Essence_content_Fragment.newInstance("tupian"),"图片");
        essencePagerAdapter.addFragment(Essence_content_Fragment.newInstance("duanzi"),"段子");
        essencePagerAdapter.addFragment(Essence_content_Fragment.newInstance("toupiao"),"投票");
        essencePagerAdapter.addFragment(Essence_content_Fragment.newInstance("paihang"),"排行");
        essencePagerAdapter.addFragment(Essence_content_Fragment.newInstance("hudongqu"),"互动区");
        essencePagerAdapter.addFragment(Essence_content_Fragment.newInstance("wanghong"),"网红");
        essencePagerAdapter.addFragment(Essence_content_Fragment.newInstance("shehui"),"社会");
        essencePagerAdapter.addFragment(Essence_content_Fragment.newInstance("meinv"),"美女");
        essencePagerAdapter.addFragment(Essence_content_Fragment.newInstance("lengzhishi"),"冷知识");
        essencePagerAdapter.addFragment(Essence_content_Fragment.newInstance("youxi"),"游戏");
        viewPager.setAdapter(essencePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

}
