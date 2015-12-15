package people.ontheway;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.IconPageIndicator;
import com.viewpagerindicator.IconPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import people.ontheway.fragment.BaseFragment;
import people.ontheway.fragment.SearchFragment;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.indicator)
    IconPageIndicator indicator;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        List<BaseFragment> fragments = initFragments();
        FragmentAdapter adapter = new FragmentAdapter(fragments, getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        indicator.setViewPager(viewpager);
    }

    private List<BaseFragment> initFragments() {
        List<BaseFragment> fragments = new ArrayList<BaseFragment>();

        BaseFragment userFragment = new BaseFragment();
        userFragment.setTitle("用户");
        userFragment.setIconId(R.drawable.tab_user_selector);
        fragments.add(userFragment);

        BaseFragment noteFragment = new BaseFragment();
        noteFragment.setTitle("记事本");
        noteFragment.setIconId(R.drawable.tab_record_selector);
        fragments.add(noteFragment);

        BaseFragment contactFragment = new BaseFragment();
        contactFragment.setTitle("联系人");
        contactFragment.setIconId(R.drawable.tab_user_selector);
        fragments.add(contactFragment);

        BaseFragment recordFragment = new BaseFragment();
        recordFragment.setTitle("记录");
        recordFragment.setIconId(R.drawable.tab_record_selector);
        fragments.add(recordFragment);

        return fragments;
    }

    class FragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
        private List<BaseFragment> mFragments;

        public FragmentAdapter(List<BaseFragment> fragments, FragmentManager fm) {
            super(fm);
            mFragments = fragments;
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getIconResId(int index) {
            return mFragments.get(index).getIconId();
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragments.get(position).getTitle();
        }
    }
}
