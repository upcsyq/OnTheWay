package people.ontheway.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/2/28.
 */
public class SquareTodayFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tx = new TextView(getActivity());
        tx.setText("Today Square Fragement!!!");
        return tx;//super.onCreateView(inflater, container, savedInstanceState);
    }
}
