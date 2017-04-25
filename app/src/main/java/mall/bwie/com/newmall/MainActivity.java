package mall.bwie.com.newmall;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mall.bwie.com.newmall.base.BaseFragment;
import mall.bwie.com.newmall.community.fragment.CommunityFragment;
import mall.bwie.com.newmall.home.fragment.HomeFragment;
import mall.bwie.com.newmall.shoppingcart.fragment.ShoppingCartFragment;
import mall.bwie.com.newmall.type.fragment.TypeFragment;
import mall.bwie.com.newmall.user.fragment.UserFragment;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.rg_main)
    RadioGroup mRgMain;
    //装初始化好Fragment的容器
    private ArrayList<BaseFragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        //默认主界面一运行,就选中的第一个按钮
        mRgMain.check(R.id.rb_home);
        //默认加载home的fragment
        ReplaceFG(mContent,fragments.get(0));
    }

    private void initFragment() {
        //创建装Fragment的容器
        fragments = new ArrayList<>();
        //添加到容器集合中,按照顺序进行添加
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new UserFragment());
    }
    //选择的Fragment对应的编号
    private int position;

    //点击主界面下的按钮,跳转到不同的模块fragment里去
    @OnClick({R.id.rb_home, R.id.rb_type, R.id.rb_community, R.id.rb_cart, R.id.rb_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                position =0;
                mRgMain.check(R.id.rb_home);
                break;
            case R.id.rb_type:
                position =1;
                mRgMain.check(R.id.rb_type);
                break;
            case R.id.rb_community:
                position =2;
                mRgMain.check(R.id.rb_community);
                break;
            case R.id.rb_cart:
                position =3;
                mRgMain.check(R.id.rb_cart);
                break;
            case R.id.rb_user:
                position =4;
                mRgMain.check(R.id.rb_user);
                break;
            default:
                position =0;
                mRgMain.check(R.id.rb_home);
                break;
        }
        //根据对应的位置得到对应的fragment
        BaseFragment baseFragment = fragments.get(position);
        //替换fragment
        ReplaceFG(mContent,baseFragment);
    }
    private Fragment mContent;

    /**
     * 替换Fragment,进行缓存优化
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to 马上要切换到的Fragment，一会要显示
     */
    private void ReplaceFG(Fragment from, Fragment to) {
        //刚刚显示的Fragment和马上切换的Fragment是否是同一个对象,是就不做切换,不是就进行切换
        if(from != to){
            //把切换到的新Fragment替换缓存的Fragment
            mContent = to;
            //开启Fragment的事务对象
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断内存中有没有添加这个Fragment
            if(!to.isAdded()){
                //该Fragment没有被添加

                //对上一个fragment进行非空判断
                if(from != null){
                    //隐藏上一个Fragment
                    ft.hide(from);
                }
                //对要切换的Fragment进行非空判断
                if(to != null){
                    //添加切换的Fragment,并提交
                    ft.add(R.id.frameLayout,to).commit();
                }
            }
            //
            else{
                //该Fragment已经被添加

                //对上一个fragment进行非空判断
                if(from != null){
                    //隐藏上一个Fragment
                    ft.hide(from);
                }
                //对要切换的Fragment进行非空判断
                if(to != null){
                    //显示隐藏的要切换的Fragment,并提交,show就不用替换fragment的ID值了
                    ft.show(to).commit();
                }
            }
        }
    }


}
