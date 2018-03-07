package com.zyjd.mips.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyjd.mips.R;
import com.zyjd.mips.entity.Element;
import com.zyjd.mips.entity.Scene;

public class AdvFragment extends Fragment {
    protected View mRoot;
    protected Scene scene;

    public AdvFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initArgs(getArguments());
    }

    // 获取场景的布局json
    protected void initArgs(Bundle bundle) {
        scene = (Scene) bundle.getSerializable("scene");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRoot == null) {
            View root = inflater.inflate(R.layout.fragment_adv, container, false);
            initWidget(root);
            mRoot = root;
        } else {
            if (mRoot.getParent() != null) {
                // 把当前root从其父控件中移除
                ((ViewGroup) mRoot.getParent()).removeView(mRoot);
            }
        }
        return mRoot;
    }

    // 初始化控件
    public void initWidget(View root) {


        ((TextView) root.findViewById(R.id.txt)).setText(scene.getName());

        TextView tv = new TextView(getContext());
        tv.setBackgroundColor(255);
        tv.setText("dynamic text");
        LinearLayout linearLayout = new LinearLayout(getContext());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        linearLayout.setLayoutParams(layoutParams);
        ((ViewGroup) root).addView(linearLayout);
        linearLayout.addView(tv);
    }

    public int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

}
