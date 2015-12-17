package people.ontheway;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.IconPageIndicator;
import com.viewpagerindicator.IconPagerAdapter;
import com.viewpagerindicator.IconTabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import people.ontheway.fragment.BaseFragment;
import people.ontheway.fragment.ConsultantFragment;
import people.ontheway.fragment.HelpFragment;
import people.ontheway.fragment.HomeFragment;
import people.ontheway.fragment.MyFragment;
import people.ontheway.fragment.SearchFragment;

public class MainActivity extends FragmentActivity
        implements SearchFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener,
        MyFragment.OnFragmentInteractionListener,
        HelpFragment.OnFragmentInteractionListener,
        ConsultantFragment.OnFragmentInteractionListener{

    @Bind(R.id.indicator)
    IconTabPageIndicator indicator;
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
        indicator.setViewPager(viewpager,2);//默认显示Tab:我的主页
    }

    private List<BaseFragment> initFragments() {
        List<BaseFragment> fragments = new ArrayList<BaseFragment>();

        BaseFragment searchFragment = new SearchFragment();
        searchFragment.setTitle("搜索");
        searchFragment.setIconId(R.drawable.tab_record_selector);
        fragments.add(searchFragment);

        BaseFragment consultantFragment = new ConsultantFragment();
        consultantFragment.setTitle("咨询师");
        consultantFragment.setIconId(R.drawable.tab_record_selector);
        fragments.add(consultantFragment);

        BaseFragment homeFragment = new HomeFragment();
        homeFragment.setTitle("主页");
        homeFragment.setIconId(R.drawable.tab_user_selector);
        fragments.add(homeFragment);

        BaseFragment myFragment = new MyFragment();
        myFragment.setTitle("我");
        myFragment.setIconId(R.drawable.tab_record_selector);
        fragments.add(myFragment);

        BaseFragment helpFragment = new HelpFragment();
        helpFragment.setTitle("帮助");
        helpFragment.setIconId(R.drawable.tab_record_selector);
        fragments.add(helpFragment);

        return fragments;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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
