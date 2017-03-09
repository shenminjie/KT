package com.newer.kt.Refactor.ui.Avtivity;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.KeyEvent;
        import android.view.View;
        import android.webkit.WebResourceRequest;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.newer.kt.R;
        import com.newer.kt.ktmatch.QueryBuilder;

        import org.xutils.http.RequestParams;

public class Main2Activity extends AppCompatActivity {

    private WebView webView;
    private ImageView image_vs_item_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ((TextView)findViewById(R.id.tv_title_stuInfo)).setText(getIntent().getStringExtra("title"));
        webView = ((WebView) findViewById(R.id.webview));
        webView.getSettings().setJavaScriptEnabled(true);
        image_vs_item_back = ((ImageView) findViewById(R.id.image_vs_item_back));
        image_vs_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        QueryBuilder.build("school_gym_courses/detail").add("school_gym_course_combination_id",getIntent().getStringExtra("school_gym_course_combination_id")).get(new QueryBuilder.EnhancedCallback() {
//            @Override
//            public void onSuccessWithObject(String namelink, Object object) {
//
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//
//            }
//
//            @Override
//            public void onDebug(RequestParams rp) {
//
//            }
//        });

        webView.loadUrl(getIntent().getStringExtra("url"));
                webView.setWebViewClient(new HellowebView());

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {

            webView.goBack();

            return true;

        }

        return super.onKeyDown(keyCode, event);

    }


    private class HellowebView extends WebViewClient {

        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }


}
//  http://api.ktfootball.com?authenticity_token=/wikis/listktfootballapistrftoken
// http://api.ktfootball.com?category='鍩虹鐭ヨ瘑'&authenticity_token=b838fd62531fc3c4c3e1f78217eba5fb