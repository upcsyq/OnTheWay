package people.ontheway.custom;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.wxlib.util.SysUtil;
//import com.taobao.openimui.sample.InitHelper;

/**
 * Created by Administrator on 2016/3/6.
 */
public class OTWApplication extends MultiDexApplication{
    final String APP_KEY = "23015524";//"23317752";
    final String LOG_TAG = "OTWApplication";
    private static YWIMKit mIMKit;
    @Override
    public void onCreate() {
        super.onCreate();

        //todo Application.onCreate中，首先执行这部分代码，以下代码固定在此处，不要改动，这里return是为了退出Application.onCreate！！！
        if(mustRunFirstInsideApplicationOnCreate()){
            //todo 如果在":TCMSSevice"进程中，无需进行openIM和app业务的初始化，以节省内存
            return;
        }

        //初始化云旺SDK
        //InitHelper.initYWSDK(this);
        //第一个参数是Application Context
        //这里的APP_KEY即应用创建时申请的APP_KEY，同时初始化必须是在主进程中
        if(SysUtil.isMainProcess(this)){
            YWAPI.init(this, APP_KEY);
            initOther();
        }
    }

    private void initOther(){
        //此实现不一定要放在Application onCreate中
        final String userid = "testpro1";
        //此对象获取到后，保存为全局对象，供APP使用
        //此对象跟用户相关，如果切换了用户，需要重新获取
        mIMKit = YWAPI.getIMKitInstance(userid, APP_KEY);

        //开始登录
        //String userid = "testpro1";
        String password = "taobao1234";
        IYWLoginService loginService = mIMKit.getLoginService();
        YWLoginParam loginParam = YWLoginParam.createLoginParam(userid, password);
        loginService.login(loginParam, new IWxCallback() {

            @Override
            public void onSuccess(Object... arg0) {
                Log.d(LOG_TAG,"onSuccess");
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

    //云旺OpenIM的DEMO用到的Application上下文实例
    private static Context sContext;
    public static Context getContext(){
        return sContext;
    }

    private boolean mustRunFirstInsideApplicationOnCreate() {
        //必须的初始化
        SysUtil.setApplication(this);
        sContext = getApplicationContext();
        return SysUtil.isTCMSServiceProcess(sContext);
    }
}
