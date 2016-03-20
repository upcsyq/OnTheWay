package people.ontheway.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import people.ontheway.R;
import people.ontheway.login.weixin.Constants;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.wx_login_button)
    Button wxLoginButton;
    @Bind(R.id.qq_login_button)
    Button qqLoginButton;

    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        wxLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 通过WXAPIFactory工厂，获取IWXAPI的实例
//                api = WXAPIFactory.createWXAPI(LoginActivity.this, Constants.APP_ID, false);
//                // 将该app注册到微信
//                api.registerApp(Constants.APP_ID);
//                //api.handleIntent(getIntent(), LoginActivity.this);
//                final SendAuth.Req req = new SendAuth.Req();
//                req.scope = "snsapi_userinfo";
//                req.state = "none";
//                boolean ret = api.sendReq(req);
//                Log.d("OTW","WXLogin ret="+ret);
//                finish();
            }
        });

        qqLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
