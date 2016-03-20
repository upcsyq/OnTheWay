package people.ontheway.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.conversation.EServiceContact;
import com.alibaba.wxlib.util.SysUtil;

import people.ontheway.OTWUtil;
import people.ontheway.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    final String LOG_TAG = "HomeFragment";
    private static YWIMKit mIMKit;
    private static boolean mLogined = false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //init();
        initOther();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button button1 = (Button)view.findViewById(R.id.open_session_list);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLogined) {
                    Intent intent = mIMKit.getConversationActivityIntent();
                    startActivity(intent);
                    Toast.makeText(HomeFragment.this.getActivity(), "你正在进入会话列表", Toast.LENGTH_LONG);
                }else {
                    Toast.makeText(HomeFragment.this.getActivity(),"你还没有登录",Toast.LENGTH_LONG);
                }
            }
        });

        Button button2 = (Button)view.findViewById(R.id.open_chat_window);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mLogined) {
                    final String target = "testpro2";// 消息接收者ID
                    Intent intent = mIMKit.getChattingActivityIntent(target);
                    startActivity(intent);
                    Toast.makeText(HomeFragment.this.getActivity(), "你正在发起单聊", Toast.LENGTH_LONG);
                }else {
                    Toast.makeText(HomeFragment.this.getActivity(),"你还没有登录",Toast.LENGTH_LONG);
                }
            }
        });

        Button button3 = (Button)view.findViewById(R.id.open_chat_window);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLogined) {

                    //userid是客服帐号，第一个参数是客服帐号，第二个是组ID，如果没有，传0
                    EServiceContact contact = new EServiceContact("userid", 0);
                    //如果需要发给指定的客服帐号，不需要Server进行分流(默认Server会分流)，请调用EServiceContact对象
                    //的setNeedByPass方法，参数为false。
                    //contact.setNeedByPass(false);
                    Intent intent = mIMKit.getChattingActivityIntent(contact);
                    startActivity(intent);
                    Toast.makeText(HomeFragment.this.getActivity(), "你正在跳往客服聊天界面", Toast.LENGTH_LONG);
                } else {
                    Toast.makeText(HomeFragment.this.getActivity(), "你还没有登录", Toast.LENGTH_LONG);
                }
            }
        });

        return view;
    }

    private void initOther(){
        //此实现不一定要放在Application onCreate中
        final String userid = "testpro1";
        //此对象获取到后，保存为全局对象，供APP使用
        //此对象跟用户相关，如果切换了用户，需要重新获取
        mIMKit = YWAPI.getIMKitInstance(userid, OTWUtil.APP_KEY);

        //开始登录
        //String userid = "testpro1";
        String password = "taobao1234";
        IYWLoginService loginService = mIMKit.getLoginService();
        YWLoginParam loginParam = YWLoginParam.createLoginParam(userid, password);
        loginService.login(loginParam, new IWxCallback() {

            @Override
            public void onSuccess(Object... arg0) {
                Log.d(LOG_TAG, "onSuccess");
                mLogined = true;
                Toast.makeText(HomeFragment.this.getActivity(), "登录成功", Toast.LENGTH_LONG);
            }

            @Override
            public void onProgress(int arg0) {
                Log.d(LOG_TAG,"onProgress arg0="+arg0);
                // TODO Auto-generated method stub
            }

            @Override
            public void onError(int errCode, String description) {
                Log.d(LOG_TAG,"onError errCode="+errCode);
                Log.d(LOG_TAG,"onError description="+description);
                //如果登录失败，errCode为错误码,description是错误的具体描述信息
            }
        });
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
