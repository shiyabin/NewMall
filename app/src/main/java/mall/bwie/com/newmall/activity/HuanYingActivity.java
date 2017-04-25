package mall.bwie.com.newmall.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mall.bwie.com.newmall.MainActivity;
import mall.bwie.com.newmall.R;

public class HuanYingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huan_ying);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                jumpMainActivity();

            }
        },3000);
    }

    private void jumpMainActivity() {
        //启动主页面
        startActivity(new Intent(HuanYingActivity.this, MainActivity.class));
        //结束Activity
        finish();
    }
}
