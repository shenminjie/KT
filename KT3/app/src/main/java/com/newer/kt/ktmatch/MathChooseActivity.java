package com.newer.kt.ktmatch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.newer.kt.R;
import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.utils.MD5;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.UUID;

import shengchengerweima.CamScanActivity;

import static com.newer.kt.Refactor.ui.Avtivity.LoginActivity.PRE_CURRENT_CLUB_ID;


public class MathChooseActivity extends CamScanActivity {

    int index = R.id.leftid;


    int num = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_choose);
        num = getIntent().getIntExtra("num",1);
        if(num<3){
            findViewById(R.id.leftid2).setVisibility(View.INVISIBLE);
            findViewById(R.id.leftportrait2).setVisibility(View.INVISIBLE);

            findViewById(R.id.rightid2).setVisibility(View.INVISIBLE);
            findViewById(R.id.rightportrait2).setVisibility(View.INVISIBLE);
        }
        if(num<2){
            findViewById(R.id.leftid1).setVisibility(View.INVISIBLE);
            findViewById(R.id.leftportrait1).setVisibility(View.INVISIBLE);

            findViewById(R.id.rightid1).setVisibility(View.INVISIBLE);
            findViewById(R.id.rightportrait1).setVisibility(View.INVISIBLE);
        }
        preparedCount = num*2;

        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inSampleSize = 2;
        Bitmap bp = BitmapFactory.decodeResource(getResources(),R.drawable.team_battle_0,op);
        findViewById(R.id.action_match_choose).setBackground(new BitmapDrawable(bp));
        findViewById(R.id.quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.menu).setVisibility(View.INVISIBLE);
            }
        });
        findViewById(R.id.saomiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invokeCap();
            }
        });
        findViewById(R.id.xuanze).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.menu).setVisibility(View.INVISIBLE);

                startActivity(new Intent(getBaseContext(), ChooseMatcherActivity.class));

            }
        });
        findViewById(R.id.leftportrait).setOnTouchListener(getPuller(R.id.leftid));
        findViewById(R.id.rightportrait).setOnTouchListener(getPuller(R.id.rightid));
        findViewById(R.id.leftportrait1).setOnTouchListener(getPuller(R.id.leftid1));
        findViewById(R.id.rightportrait1).setOnTouchListener(getPuller(R.id.rightid1));
        findViewById(R.id.leftportrait2).setOnTouchListener(getPuller(R.id.leftid2));
        findViewById(R.id.rightportrait2).setOnTouchListener(getPuller(R.id.rightid2));



        findViewById(R.id.ready).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MatchActivity.invoke(getBaseContext());
                Params.getInstanceParam().setSide_a_users_1(((TextView)findViewById(R.id.rightid)).getText().toString());
                Params.getInstanceParam().setSide_a_users_2(((TextView)findViewById(R.id.rightid1)).getText().toString());
                Params.getInstanceParam().setSide_a_users_3(((TextView)findViewById(R.id.rightid2)).getText().toString());

                Params.getInstanceParam().setSide_b_users_1(((TextView)findViewById(R.id.leftid)).getText().toString());
                Params.getInstanceParam().setSide_b_users_2(((TextView)findViewById(R.id.leftid1)).getText().toString());
                Params.getInstanceParam().setSide_b_users_3(((TextView)findViewById(R.id.leftid2)).getText().toString());

                Params.getInstanceParam().setBattle_id(UUID.randomUUID().toString());

                finish();
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    int preparedCount = 2;

    public View.OnTouchListener getPuller(final int idx){

        return  new View.OnTouchListener() {
            int id = idx;
            int lastX = 0;
            int lastY = 0;
            int w = 0;
            int h = 0;
            int topm = 0;
            int dylast;
            int initX;
            int initY;
            boolean scroll = true;
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {

                int dy = 0;
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:

                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        if(w==0){
                            initX = (int) event.getRawX();
                            initY = (int) event.getRawY();
                            w = lParams.width;
                            h = lParams.height;
                            topm = ((RelativeLayout.MarginLayoutParams)lParams).topMargin;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(dylast<30){
                            findViewById(R.id.menu).setVisibility(View.VISIBLE);
                            index = id;
                            dylast = 0;
                        }

                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = (int) event.getRawX() - lastX;
                        dy = (int) event.getRawY() - lastY;
                        if(event.getRawY() - initY>0){
                            dylast = (int) (event.getRawY() - initY);
                        }
                        final int left = view.getLeft() + 0;
                        final int top = view.getTop() + dy;
                        if(dylast>270){
                            dylast = 270;
                        }
                        final RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        if(dy>0){
                            if(!scroll){
                                return false;
                            }
                            layoutParams.height = view.getLayoutParams().height;
                            layoutParams.width = view.getLayoutParams().width;
                            layoutParams.leftMargin =left;
                            layoutParams.topMargin =top;
                            view.setLayoutParams(layoutParams);
                            lastX = (int) event.getRawX();
                            lastY = (int) event.getRawY();
                            final int dyy = dy;
                            final TranslateAnimation animation = new TranslateAnimation(0, 0,0, -dy);
                            animation.setDuration(200);//设置动画持续时间

                            animation.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    layoutParams.height = h;
                                    layoutParams.width = w;
                                    layoutParams.leftMargin =left;
                                    layoutParams.topMargin =topm;
                                    view.setLayoutParams(layoutParams);
                                    lastX = (int) event.getRawX();
                                    lastY = (int) event.getRawY();
                                    if(dylast>=80) {
                                        ((ImageView) view).setImageResource(R.drawable.default_head);
                                        genMatch_Id(index = id);
                                        scroll = false;

                                    }

                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });
                            view.startAnimation(animation);

                        }
                        break;
                }
//                getWindow().getDecorView().invalidate();
                return true;
            }
        };

    }
    @Override
    public void recvCode(String result) {
        super.recvCode(result);
        ((TextView)findViewById(index)).setText(result);
        checkPrepared(index);
    }

    public void onResume(){
        super.onResume();
        if(ChooseMatcherActivity.teamName!=null){
            ((TextView)findViewById(index)).setText(ChooseMatcherActivity.teamName);
            checkPrepared(index);
            ChooseMatcherActivity.teamName = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        findViewById(R.id.menu).setVisibility(View.INVISIBLE);
    }

    public void genMatch_Id(final int index){

                    String url = Constants.KTHOST + "users/f_register";
                    RequestParams p = new RequestParams(url);

                    String clubid = ""+PreferenceManager.getDefaultSharedPreferences(MathChooseActivity.this)
                            .getLong(PRE_CURRENT_CLUB_ID,1);
                    p.addQueryStringParameter("club_id", ""+clubid);
                    p.addQueryStringParameter("authenticity_token", MD5.getToken(url));
                    x.http().post(p, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
//                        { response: "success", user_id: 用户ID, match_id: 用户快速参赛的号码 }
//                showDialogToast(result);

                            Gson gson = new Gson();
                            //1. 获得 解析者
                            JsonParser parser = new JsonParser();

                            //2. 获得 根节点元素
                            JsonElement element = parser.parse(result);

                            //3. 根据 文档判断根节点属于 什么类型的 Gson节点对象
                            JsonObject root = element.getAsJsonObject();

                            //4. 取得 节点 下 的某个节点的 value
                            JsonPrimitive flagjson = root.getAsJsonPrimitive("response");
                            String flag = flagjson.getAsString();

                            if("success".equals(flag)){

                                JsonPrimitive matchidj = root.getAsJsonPrimitive("match_id");
                                String matchid = matchidj.getAsString();
                                matchid = matchid.substring(matchid.lastIndexOf("_")+1);
                                ((TextView)findViewById(index)).setText(matchid);
                                checkPrepared(index);
                            }
                        }

                        @Override
                        public void onError(Throwable ex, boolean isOnCallback) {

                        }

                        @Override
                        public void onCancelled(CancelledException cex) {

                        }

                        @Override
                        public void onFinished() {


                        }
                    });
    }

    public void checkPrepared(int index){
        switch(index){
            case R.id.leftid:
                findViewById(R.id._1v1_left).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.leftportrait)).setImageResource(R.drawable.default_head);

                break;
            case R.id.rightid:

                findViewById(R.id._1v1_right).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.rightportrait)).setImageResource(R.drawable.default_head);

                break;
            case R.id.leftid1:
                findViewById(R.id._1v1_left1).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.leftportrait1)).setImageResource(R.drawable.default_head);

                break;
            case R.id.rightid1:

                findViewById(R.id._1v1_right1).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.rightportrait1)).setImageResource(R.drawable.default_head);

                break;

            case R.id.leftid2:
                findViewById(R.id._1v1_left2).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.leftportrait2)).setImageResource(R.drawable.default_head);

                break;
            case R.id.rightid2:

                findViewById(R.id._1v1_right2).setVisibility(View.VISIBLE);
                ((ImageView)findViewById(R.id.rightportrait2)).setImageResource(R.drawable.default_head);

                break;
        }
        preparedCount--;
        if(preparedCount<=0){
            findViewById(R.id.ready).setVisibility(View.VISIBLE);
        }
    }


    public void onDestroy(){
        super.onDestroy();

    }

}
