package com.newer.kt.Refactor.ui.Fragment.Main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.frame.app.base.fragment.BaseFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.newer.kt.R;
import com.newer.kt.Refactor.Constants;
import com.newer.kt.Refactor.ui.Avtivity.LoginActivity;
import com.newer.kt.Refactor.ui.Avtivity.Xjss.ActivityNewContest;
import com.newer.kt.Refactor.utils.MD5;
import com.newer.kt.entity.AddClassData;
import com.newer.kt.entity.Clubs_game_Bean;
import com.newer.kt.entity.Clubs_groups_Bean;
import com.newer.kt.entity.GradeList;
import com.newer.kt.ktmatch.ActivityKi;
import com.newer.kt.ktmatch.CountActivity;
import com.newer.kt.ktmatch.MathChooseActivity;
import com.newer.kt.ktmatch.Params;
import com.newer.kt.ktmatch.QueryBuilder;
import com.newer.kt.ktmatch.json.JsonUtil;
import com.newer.kt.utils.AuthenticityToken;

import org.json.JSONArray;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.newer.kt.Refactor.ui.Avtivity.LoginActivity.PRE_CURRENT_CLUB_ID;
import static com.newer.kt.Refactor.ui.Avtivity.LoginActivity.PRE_CURRENT_CLUB_NAME;

/**
 * Created by jy on 16/9/14.
 */
public class ManagerFragment extends BaseFragment {

    public static final String GAME_ID = "game_id";
    public static final String PRE_CURRENT_GAME_ID = "current_game_id";//提交比分时数据
    public static final String EXTRA_USER_CODE = "user_code";
    public static final String NO_UPDATA_USER = "未更新学生数： ";
    public static final int TO_USERINFOAVTIVITY = 1231;
    private ExpandableListView listView;
    private TextView add_class;
    private TextView add_student;
    private AddClassData classesData;
    private ArrayList<GradeList> item_list;

    private ListView lv_saishi;
    private LayoutInflater layoutInflater;
    public String Clubs_game_url=null;
    public String Clubs_group=null;
    //  public BaseAdapter saishiAdapter=null;
    private  View view=null;
    final ArrayList<Clubs_game_Bean.Clubs_game> clubs_games_list=new ArrayList<Clubs_game_Bean.Clubs_game>();


    private BaseAdapter saishiAdapter=null;
    private ViewHolder viewHolder =null;

    private Clubs_groups_Bean clubs_groups_bean=null;
    public TextView tv_all_game;
    public String a=null;
    public Type typec=null;
    private TextView tv_all_people;
    private TextView tv_all_games;
    private TextView tv_sex;

    @Override
    protected void initView(Bundle savedInstanceState) {
        //setContentView(R.layout.layout_managerfragment);

        rootView=getActivity().getLayoutInflater().inflate(R.layout.layout_managerfragment,null);
        lv_saishi = ((ListView) rootView.findViewById(R.id.lv_saishi));
        tv_all_game = ((TextView) rootView.findViewById(R.id.tv_all_game));
        tv_all_people = ((TextView) rootView.findViewById(R.id.tv_all_people));
        tv_all_games = ((TextView) rootView.findViewById(R.id.tv_all_games));
        tv_sex = ((TextView) rootView.findViewById(R.id.tv_sex));
        rootView.findViewById(R.id.image_new).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ActivityNewContest.class));
            }
        });
        getClubs_group();


        String clubid = ""+ PreferenceManager.getDefaultSharedPreferences(getContext())
                .getString(PRE_CURRENT_CLUB_NAME,"");
        ((TextView)rootView.findViewById(R.id.tv_title_game)).setText(clubid);

        lv_saishi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Clubs_game_Bean.Clubs_game  clubs_game = (Clubs_game_Bean.Clubs_game) parent.getItemAtPosition(position);
                Params.getInstanceParam().setGame_id(clubs_game.game_id);
                ActivityKi.title = clubs_game.name;

                Intent intent=new Intent(getActivity(),SaiShiEJ.class).putExtra("game_id",clubs_game.game_id);
                startActivity(intent);


            }
        });
        saishiAdapter=new BaseAdapter() {

            @Override
            public int getCount() {
                return clubs_games_list.size();
            }

            @Override
            public Object getItem(int position) {
                return clubs_games_list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                view=View.inflate(getActivity(),R.layout.item_game_name,null);

                viewHolder = new ViewHolder();
                viewHolder.tv_vs_saishi_title = ((TextView) view.findViewById(R.id.tv_vs_saishi_title));
                viewHolder.tv_people_conts = ((TextView) view.findViewById(R.id.tv_people_conts));
                viewHolder.tv_vs_saishi_state = ((TextView) view.findViewById(R.id.tv_vs_saishi_state));
                viewHolder.tv_vs_startdate = ((TextView) view.findViewById(R.id.tv_vs_startdate));
                viewHolder.tv_vs_enddate = ((TextView) view.findViewById(R.id.tv_vs_enddate));
                Clubs_game_Bean.Clubs_game  clubs=clubs_games_list.get(position);

                viewHolder.tv_vs_saishi_title.setText(clubs.name+"");
                viewHolder.tv_people_conts.setText(clubs.user_count);
                viewHolder.tv_vs_saishi_state.setText(clubs.status);
                if(clubs.status.toString().equals("已结束")){
                    view.setBackgroundColor(Color.parseColor("#f7f7f7"));
                }
                viewHolder.tv_vs_startdate.setText(clubs.start_date);
                viewHolder.tv_vs_enddate.setText(clubs.end_date);
                return view;
            }
        };


        lv_saishi.setAdapter(saishiAdapter);
        getClubs_game();
        saishiAdapter.notifyDataSetChanged();
    }




    /**
     */
    private void getClubs_group()
    {
        String url = Constants.KTHOST + "games/club_tongji";
        RequestParams param = new RequestParams(url);
        param.addQueryStringParameter("authenticity_token", MD5.getToken(url));

        final String clubid = "" + PreferenceManager.getDefaultSharedPreferences(getContext()).getLong(LoginActivity.PRE_CURRENT_CLUB_ID,1);

        param.addQueryStringParameter("club_id", clubid);
        x.http().get(param, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();

                typec=new TypeToken<Clubs_groups_Bean>(){}.getType();

//                male_users_count: 人数(男),
//                        female_users_count: 人数(女),
                clubs_groups_bean=gson.fromJson(result,typec);
                tv_all_game.setText(clubs_groups_bean.getGames_count());
                tv_all_people.setText(clubs_groups_bean.getUsers_count());
                tv_all_games.setText(clubs_groups_bean.getBattles_count());
                tv_sex.setText(clubs_groups_bean.getMale_users_count()+"/"+clubs_groups_bean.getFemale_users_count());

                //  Typeface typeFace =Typeface.createFromAsset(AssetManager mgr,"fonts/BEBAS.TTF");
                //  tv_all_game.setTypeface(typeFace);

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
    private void getClubs_game() {
        Clubs_game_url=AuthenticityToken.getAuthenticityToken("/games/club_all_games");

        String clubid = ""+ PreferenceManager.getDefaultSharedPreferences(getContext())
                .getLong(PRE_CURRENT_CLUB_ID,0);

        // System.out.println(Clubs_game_url+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        RequestParams params=new RequestParams("http://api.ktfootball.com/games/club_all_games?club_id="+clubid+"&authenticity_token="+Clubs_game_url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                Clubs_game_Bean clubs_games_bean =gson.fromJson(result,Clubs_game_Bean.class);
                clubs_games_list.clear();
                clubs_games_list.addAll(clubs_games_bean.games);

                saishiAdapter.notifyDataSetChanged();



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

    @Override
    protected void setListener() {
//        add_class.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getThis(), CreateClassActivity.class));
//            }
//        });
//        add_student.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getThis(), CaptureActivity.class);
//                intent.putExtra(CaptureActivity.CAPTURE_CODE, 3);
//                startActivityForResult(intent, 3);
//            }
//        })、
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//        classesData = GsonTools.changeGsonToBean(KTApplication.getClassDetailsInfo(), AddClassData.class);
//        if(classesData != null){
//            item_list = classesData.grade_list;
//            Collections.sort(item_list);
//            ClassMyExpandableListViewAdapter myExpandableListViewAdapter = new ClassMyExpandableListViewAdapter(getThis(), item_list);
//            listView.setAdapter(myExpandableListViewAdapter);
//        }
////        for (int i = 0; i < myExpandableListViewAdapter.getGroupCount(); i++) {
////            listView.expandGroup(i);
////        }
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void initHandler(Message msg) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        LogUtils.e("onActivityResult");
//        if (requestCode == 3) {
//            LogUtils.e("requestCode == 3");
//            if (resultCode == 3) {
//                Bundle bundle = data.getBundleExtra("bundle");
//                final String result = bundle.getString(CaptureActivity.BUNDLE_SCAN_RESULT);
//                Intent intent = new Intent(getThis(), UserInfoActivity.class);
//                intent.putExtra(MyClassActivity.EXTRA_USER_ID, result);
//                intent.putExtra(EXTRA_USER_CODE, 1);
//                startActivity(intent);
//            }
//        }
    }








//    public class SaiShiAdapter extends BaseAdapter {
//        View view=null;
//        public int getCount() {
//            return 10;
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return 1;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//
//            view=View.inflate(getActivity(),R.layout.item_game_name,null);
//
//
//
//           //  System.out.println(aa+",,,1111111111111111111111111111111111111");
//           return view;
//        }
//    }

    public  static class ViewHolder{
        private  TextView tv_vs_saishi_title;
        private TextView tv_people_conts;
        private TextView tv_vs_saishi_state;
        private TextView tv_vs_startdate;
        private TextView tv_vs_enddate;
    }

}
