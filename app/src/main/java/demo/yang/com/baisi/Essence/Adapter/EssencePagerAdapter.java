package demo.yang.com.baisi.Essence.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxy on 2017/3/5.
 * email:1084625746@qq.com
 */

public class EssencePagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragment = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public EssencePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment,String string){
        mFragment.add(fragment);
        mFragmentTitles.add(string);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get(position);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

}
