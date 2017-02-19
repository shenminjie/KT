package com.newer.kt.wxapi;


import android.widget.Toast;
import com.newer.kt.NeActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.umeng.weixin.callback.WXCallbackActivity;

//import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class WXEntryActivity extends WXCallbackActivity implements IWXAPIEventHandler {

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                Toast.makeText(this, "发送成功", Toast.LENGTH_LONG).show();
                NeActivity.getThisInstance().callback(true);
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Toast.makeText(this, "分享取消", Toast.LENGTH_LONG).show();
                NeActivity.getThisInstance().callback(false);
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Toast.makeText(this, "分享被拒绝", Toast.LENGTH_LONG).show();
                NeActivity.getThisInstance().callback(false);
                finish();
                break;
            default:
                Toast.makeText(this, "分享返回", Toast.LENGTH_LONG).show();
                NeActivity.getThisInstance().callback(false);
                break;
        }

    }

}
