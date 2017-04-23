package demo.yang.com.baisi.Shopping.View;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.yang.com.baisi.R;
import demo.yang.com.baisi.Shopping.Adapter.ShoppingPagerAdapter;

public class Shopping_Fragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public Shopping_Fragment() {
        // Required empty public constructor
    }

    public static Shopping_Fragment newInstance(String param1, String param2) {
        Shopping_Fragment fragment = new Shopping_Fragment();
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
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.shopping_top_tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.shopping_top_viewPager);
        ShoppingPagerAdapter shoppingPagerAdapter = new ShoppingPagerAdapter(getChildFragmentManager());
        shoppingPagerAdapter.addFragment(Shopping_content_Fragment.newInstance("jingxuan"),"精选");
        shoppingPagerAdapter.addFragment(Shopping_content_Fragment.newInstance("nanzhuang"),"男装");
        shoppingPagerAdapter.addFragment(Shopping_content_Fragment.newInstance("jujia"),"居家");
        shoppingPagerAdapter.addFragment(Shopping_content_Fragment.newInstance("shuma"),"数码");
        shoppingPagerAdapter.addFragment(Shopping_content_Fragment.newInstance("neiyi"),"内衣");
        shoppingPagerAdapter.addFragment(Shopping_content_Fragment.newInstance("meishi"),"美食");
        shoppingPagerAdapter.addFragment(Shopping_content_Fragment.newInstance("nvzhuang"),"女装");
        shoppingPagerAdapter.addFragment(Shopping_content_Fragment.newInstance("muying"),"母婴");
        shoppingPagerAdapter.addFragment(Shopping_content_Fragment.newInstance("xiebao"),"鞋包");
        shoppingPagerAdapter.addFragment(Shopping_content_Fragment.newInstance("meizhuanggehu"),"美妆个护");
        shoppingPagerAdapter.addFragment(Shopping_content_Fragment.newInstance("shipin"),"饰品");
        viewPager.setAdapter(shoppingPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

}
