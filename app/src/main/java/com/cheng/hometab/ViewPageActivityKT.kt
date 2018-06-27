package com.cheng.hometab

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cheng.hometab.fragmentKT.FragmentFirstKT
import com.cheng.hometab.fragmentKT.FragmentForthKT
import com.cheng.hometab.fragmentKT.FragmentSecondKT
import com.cheng.hometab.fragmentKT.FragmentThirdKT

class ViewPageActivityKT : AppCompatActivity(), View.OnClickListener{

    private val IMAGE_PRESS = intArrayOf(R.mipmap.main_icon_server_blue, R.mipmap.main_icon_order_blue, R.mipmap.main_icon_approval_blue, R.mipmap.main_icon_mine_blue)
    private val IMAGE_NORMAL = intArrayOf(R.mipmap.main_icon_server_gray, R.mipmap.main_icon_order_gray, R.mipmap.main_icon_approval_gray, R.mipmap.main_icon_mine_gray)
    private val tabText = arrayOfNulls<TextView>(4)
    private val tabImage = arrayOfNulls<ImageView>(4)
    private val tabLayout = arrayOfNulls<View>(4)
    private var fragment = arrayOfNulls<Fragment>(4)
    private lateinit var viewPage: ViewPager
    var curIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_page)
        initView()
    }

    private fun initView() {
        for (i in IMAGE_PRESS.indices) {
            tabLayout[i] = findViewById(resources.getIdentifier("rl_tab_$i", "id", packageName))
            tabImage[i] = findViewById(resources.getIdentifier("iv_tab_$i", "id", packageName))
            tabText[i] = findViewById(resources.getIdentifier("tv_tab_$i", "id", packageName))
            tabLayout[i]?.setOnClickListener(this)
        }
        viewPage = findViewById(R.id.viewPage)
        viewPage.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                changeTab(position, false)
            }
            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        viewPage.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                if (fragment[position] == null) {
                    var args = Bundle()
                    when (position) {
                        0 -> {
                            args.putString("tag", "FragmentFirstKT")
                            args.putInt("index", 0)
                            fragment[position] = FragmentFirstKT().newInstance(args)
                        }
                        1 -> {
                            args.putString("tag", "FragmentSecondKT")
                            args.putInt("index", 1)
                            fragment[position] = FragmentSecondKT().newInstance(args)
                        }
                        2 -> {
                            args.putString("tag", "FragmentThirdKT")
                            args.putInt("index", 2)
                            fragment[position] = FragmentThirdKT().newInstance(args)
                        }
                        3 -> {
                            args.putString("tag", "FragmentForthKT")
                            args.putInt("index", 3)
                            fragment[position] = FragmentForthKT().newInstance(args)
                        }
                    }
                }
                return fragment[position]!!
            }

            override fun getCount(): Int {
                return fragment.size
            }
        }
        viewPage.offscreenPageLimit = 0
        viewPage.currentItem = 0
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.rl_tab_0 -> changeTab(0, true)
            R.id.rl_tab_1 -> changeTab(1, true)
            R.id.rl_tab_2 -> changeTab(2, true)
            R.id.rl_tab_3 -> changeTab(3, true)
        }
    }

    private fun changeTab(index: Int, flag: Boolean) {
        if (curIndex == index) return
        for (i in IMAGE_PRESS.indices) {
            tabText[i]?.setTextColor(ContextCompat.getColor(this, R.color.text_main_dark_gray))
            tabImage[i]?.setImageResource(IMAGE_NORMAL[i])
        }
        tabText[index]?.setTextColor(ContextCompat.getColor(this, R.color.text_blue))
        tabImage[index]?.setImageResource(IMAGE_PRESS[index])
        curIndex = index
        if (flag) viewPage.currentItem = curIndex
    }

}