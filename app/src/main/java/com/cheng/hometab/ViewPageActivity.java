package com.cheng.hometab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cheng.hometab.fragment.FragmentFirst;
import com.cheng.hometab.fragment.FragmentForth;
import com.cheng.hometab.fragment.FragmentSecond;
import com.cheng.hometab.fragment.FragmentThird;
import java.util.List;

public class ViewPageActivity extends AppCompatActivity implements View.OnClickListener{

    private int[] IMAGE_PRESS = new int[]{R.mipmap.main_icon_server_blue, R.mipmap.main_icon_order_blue
            , R.mipmap.main_icon_approval_blue, R.mipmap.main_icon_mine_blue};
    private int[] IMAGE_NORMAL = new int[]{R.mipmap.main_icon_server_gray, R.mipmap.main_icon_order_gray
            , R.mipmap.main_icon_approval_gray, R.mipmap.main_icon_mine_gray};
    private TextView[] tabText = new TextView[4];
    private ImageView[] tabImage = new ImageView[4];
    private View[] tabLayout = new View[4];
    private Fragment[] fragment = new Fragment[4];
    private ViewPager viewPage;
    private int curIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        initView();
    }

    private void initView() {
        for (int i = 0; i < IMAGE_PRESS.length; i++) {
            tabLayout[i] = findViewById(getResources().getIdentifier("rl_tab_" + i, "id", getPackageName()));
            tabImage[i] = findViewById(getResources().getIdentifier("iv_tab_" + i, "id", getPackageName()));
            tabText[i] = findViewById(getResources().getIdentifier("tv_tab_" + i, "id", getPackageName()));
            tabLayout[i].setOnClickListener(this);
        }
        viewPage = findViewById(R.id.viewPage);
        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                changeTab(position, false);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (fragment[position] == null) {
                    switch (position){
                        case 0:
                            fragment[position] = new FragmentFirst();
                            break;
                        case 1:
                            fragment[position] = new FragmentSecond();
                            break;
                        case 2:
                            fragment[position] = new FragmentThird();
                            break;
                        case 3:
                            fragment[position] = new FragmentForth();
                            break;
                    }
                }
                return fragment[position];
            }
            @Override
            public int getCount() {
                return fragment.length;
            }
        });
        viewPage.setOffscreenPageLimit(0);
        viewPage.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_tab_0:
                changeTab(0, true);
                break;
            case R.id.rl_tab_1:
                changeTab(1, true);
                break;
            case R.id.rl_tab_2:
                changeTab(2, true);
                break;
            case R.id.rl_tab_3:
                changeTab(3, true);
                break;
        }
    }

    private void changeTab(int index, boolean flag){
        if (curIndex == index) return;
        for (int i = 0; i < IMAGE_PRESS.length; i++) {
            tabText[i].setTextColor(ContextCompat.getColor(this, R.color.text_main_dark_gray));
            tabImage[i].setImageResource(IMAGE_NORMAL[i]);
        }
        tabText[index].setTextColor(ContextCompat.getColor(this, R.color.text_blue));
        tabImage[index].setImageResource(IMAGE_PRESS[index]);
        curIndex = index;
        if(flag) viewPage.setCurrentItem(curIndex);
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
