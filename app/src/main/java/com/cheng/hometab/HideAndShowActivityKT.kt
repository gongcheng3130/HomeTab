package com.cheng.hometab

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cheng.hometab.fragmentKT.FragmentFirstKT
import com.cheng.hometab.fragmentKT.FragmentForthKT
import com.cheng.hometab.fragmentKT.FragmentSecondKT
import com.cheng.hometab.fragmentKT.FragmentThirdKT

class HideAndShowActivityKT : AppCompatActivity(), View.OnClickListener {

    private val IMAGE_PRESS = intArrayOf(R.mipmap.main_icon_server_blue, R.mipmap.main_icon_order_blue, R.mipmap.main_icon_approval_blue, R.mipmap.main_icon_mine_blue)
    private val IMAGE_NORMAL = intArrayOf(R.mipmap.main_icon_server_gray, R.mipmap.main_icon_order_gray, R.mipmap.main_icon_approval_gray, R.mipmap.main_icon_mine_gray)
    private val tabText = arrayOfNulls<TextView>(4)
    private val tabImage = arrayOfNulls<ImageView>(4)
    private val tabLayout = arrayOfNulls<View>(4)
    private var fragment = arrayOfNulls<Fragment>(4)
    var curIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hide_and_show)
        curIndex = -1//不要删
        initView()
        changeTab(0)
    }

    private fun initView() {
        for (i in 0..3) {
            tabLayout[i] = findViewById(resources.getIdentifier("rl_tab_$i", "id", packageName))
            tabImage[i] = findViewById(resources.getIdentifier("iv_tab_$i", "id", packageName))
            tabText[i] = findViewById(resources.getIdentifier("tv_tab_$i", "id", packageName))
            tabLayout[i]!!.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.rl_tab_0 -> changeTab(0)
            R.id.rl_tab_1 -> changeTab(1)
            R.id.rl_tab_2 -> changeTab(2)
            R.id.rl_tab_3 -> changeTab(3)
        }
    }

    private fun changeTab(index: Int) {
        if (curIndex == index) return
        for (i in 0..3) {
            tabText[i]!!.setTextColor(ContextCompat.getColor(this, R.color.text_main_dark_gray))
            tabImage[i]!!.setImageResource(IMAGE_NORMAL[i])
        }
        tabText[index]!!.setTextColor(ContextCompat.getColor(this, R.color.text_blue))
        tabImage[index]!!.setImageResource(IMAGE_PRESS[index])
        if (fragment[index] == null) {
            var args = Bundle()
            when (index) {
                0 -> {
                    args.putString("tag", "FragmentFirstKT")
                    args.putInt("index", 0)
                    fragment[index] = FragmentFirstKT().newInstance(args)
                }
                1 -> {
                    args.putString("tag", "FragmentSecondKT")
                    args.putInt("index", 1)
                    fragment[index] = FragmentSecondKT().newInstance(args)
                }
                2 -> {
                    args.putString("tag", "FragmentThirdKT")
                    args.putInt("index", 2)
                    fragment[index] = FragmentThirdKT().newInstance(args)
                }
                3 -> {
                    args.putString("tag", "FragmentForthKT")
                    args.putInt("index", 3)
                    fragment[index] = FragmentForthKT().newInstance(args)
                }
            }
        }
        if (curIndex == -1) {
            supportFragmentManager.beginTransaction().add(R.id.fl_container, fragment[index]).commitAllowingStateLoss()
        } else {
            if (fragment[index]!!.isAdded) {
                supportFragmentManager.beginTransaction().hide(fragment[curIndex]).show(fragment[index]).commitAllowingStateLoss()
            } else {
                supportFragmentManager.beginTransaction().hide(fragment[curIndex]).add(R.id.fl_container, fragment[index]).commitAllowingStateLoss()
            }
        }
        curIndex = index
    }

    //传递给子fragment处理的事件
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        supportFragmentManager.fragments?.forEach { it?.onActivityResult(requestCode, resultCode, data) }
    }

}