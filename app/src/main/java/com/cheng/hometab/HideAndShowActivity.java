package com.cheng.hometab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cheng.hometab.fragment.FragmentFirst;
import com.cheng.hometab.fragment.FragmentForth;
import com.cheng.hometab.fragment.FragmentSecond;
import com.cheng.hometab.fragment.FragmentThird;

import java.util.List;

public class HideAndShowActivity extends AppCompatActivity implements View.OnClickListener{

    private int[] IMAGE_PRESS = new int[]{R.mipmap.main_icon_server_blue, R.mipmap.main_icon_order_blue
            , R.mipmap.main_icon_approval_blue, R.mipmap.main_icon_mine_blue};
    private int[] IMAGE_NORMAL = new int[]{R.mipmap.main_icon_server_gray, R.mipmap.main_icon_order_gray
            , R.mipmap.main_icon_approval_gray, R.mipmap.main_icon_mine_gray};
    private TextView[] tabText = new TextView[4];
    private ImageView[] tabImage = new ImageView[4];
    private View[] tabLayout = new View[4];
    private Fragment[] fragment = new Fragment[4];
    private int curIndex = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_and_show);
        initView();
        changeTab(0);
    }

    private void initView() {
        for (int i = 0; i < IMAGE_PRESS.length; i++) {
            tabLayout[i] = findViewById(getResources().getIdentifier("rl_tab_" + i, "id", getPackageName()));
            tabImage[i] = findViewById(getResources().getIdentifier("iv_tab_" + i, "id", getPackageName()));
            tabText[i] = findViewById(getResources().getIdentifier("tv_tab_" + i, "id", getPackageName()));
            tabLayout[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_tab_0:
                changeTab(0);
                break;
            case R.id.rl_tab_1:
                changeTab(1);
                break;
            case R.id.rl_tab_2:
                changeTab(2);
                break;
            case R.id.rl_tab_3:
                changeTab(3);
                break;
        }
    }

    private void changeTab(int index){
        if (curIndex == index) return;
        for (int i = 0; i < IMAGE_PRESS.length; i++) {
            tabText[i].setTextColor(ContextCompat.getColor(this, R.color.text_main_dark_gray));
            tabImage[i].setImageResource(IMAGE_NORMAL[i]);
        }
        tabText[index].setTextColor(ContextCompat.getColor(this, R.color.text_blue));
        tabImage[index].setImageResource(IMAGE_PRESS[index]);
        if (fragment[index] == null) {
            switch (index){
                case 0:
                    fragment[index] = new FragmentFirst();
                    break;
                case 1:
                    fragment[index] = new FragmentSecond();
                    break;
                case 2:
                    fragment[index] = new FragmentThird();
                    break;
                case 3:
                    fragment[index] = new FragmentForth();
                    break;
            }
        }
        if (curIndex == -1) {
            getSupportFragmentManager().beginTransaction().add(R.id.fl_container, fragment[index]).commitAllowingStateLoss();
        } else {
            if (fragment[index].isAdded()) {
                getSupportFragmentManager().beginTransaction().hide(fragment[curIndex]).show(fragment[index]).commitAllowingStateLoss();
            } else {
                getSupportFragmentManager().beginTransaction().hide(fragment[curIndex]).add(R.id.fl_container, fragment[index]).commitAllowingStateLoss();
            }
        }
        curIndex = index;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if(fragments!=null && fragments.size()>0){
            for (int i = 0; i < fragments.size(); i++) {
                if(fragments.get(i)!=null) fragments.get(i).onActivityResult(requestCode, resultCode, data);
            }
        }
    }

}
