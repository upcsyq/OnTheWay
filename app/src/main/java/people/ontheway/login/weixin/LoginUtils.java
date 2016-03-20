package people.ontheway.login.weixin;

/**
 * Created by jackyshu on 2015/12/18.
 */
public class LoginUtils {

    private static LoginUtils mInstance = null;
    public static LoginUtils getInstance(){
        if(mInstance == null){
            mInstance = new LoginUtils();
        }

        return mInstance;
    }

    private boolean wxLoginStatus = false;
    public void setWXLoginStatus(boolean status){
        wxLoginStatus = status;
    }

    public boolean isWxLoginStatus(){
        return wxLoginStatus;
    }
}
