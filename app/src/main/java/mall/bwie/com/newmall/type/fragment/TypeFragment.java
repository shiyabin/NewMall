package mall.bwie.com.newmall.type.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mall.bwie.com.newmall.base.BaseFragment;


/**
 * function:分类页面的fragment
 */

public class TypeFragment extends BaseFragment {

    private TextView mTextView;

    @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mTextView = new TextView(mContext);
        return mTextView;
    }

    @Override
    public void initData() {
        mTextView.setText("分类页面的Fragment");
    }
}
