package people.ontheway.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import people.ontheway.R;
import people.ontheway.adapter.SquareFragmentPageAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConsultantFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConsultantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultantFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RadioButton hot_party;
    RadioButton today_party;
    RadioButton good_party;
    RadioGroup radioGroup;
    private ArrayList<Fragment> fragmentList;
    ViewPager square_viewpager;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultantFragment newInstance(String param1, String param2) {
        ConsultantFragment fragment = new ConsultantFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ConsultantFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private class CheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.hot_party:
                    square_viewpager.setCurrentItem(0);
                    break;
                case R.id.today_party:
                    square_viewpager.setCurrentItem(1);
                    break;
                case R.id.good_party:
                    square_viewpager.setCurrentItem(2);
                    break;
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consultant, container, false);
        hot_party = (RadioButton)view.findViewById(R.id.hot_party);
        today_party = (RadioButton)view.findViewById(R.id.today_party);
        good_party = (RadioButton)view.findViewById(R.id.good_party);
        radioGroup = (RadioGroup)view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new CheckedChangeListener());
        square_viewpager = (ViewPager)view.findViewById(R.id.square_viewpager);
        fragmentList = new ArrayList<Fragment>();
        Fragment hotFragment = new SquareHotFragment();
        Fragment todayFragment = new SquareTodayFragment();
        Fragment goodFragment = new SquareGoodFragment();
        fragmentList.add(hotFragment);
        fragmentList.add(todayFragment);
        fragmentList.add(goodFragment);

        square_viewpager.setAdapter(new SquareFragmentPageAdapter(getActivity().getSupportFragmentManager(),fragmentList));
        //hot_party.requestFocus();
        square_viewpager.setCurrentItem(0);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
