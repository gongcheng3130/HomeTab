package com.cheng.hometab.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cheng.hometab.MyApp;

abstract class BaseFragment extends Fragment implements View.OnClickListener{

    protected View root;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public Context getContext() {
        if(mContext==null) mContext = MyApp.getInstance();
        return mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(getLayoutId(), container, false);
        if(root.getParent()!=null)((ViewGroup)root.getParent()).removeView(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    abstract int getLayoutId();

    abstract void initView();

    @Override
    public void onClick(View view) {

    }

}
