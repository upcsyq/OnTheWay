package people.ontheway.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/2/28.
 */
public class SquareFragmentPageAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> list;

    public SquareFragmentPageAdapter(FragmentManager fm,ArrayList<Fragment> list){
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
