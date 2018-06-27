package com.cheng.hometab.fragmentKT

import android.os.Bundle
import com.cheng.hometab.R

class FragmentForthKT : BaseFragmentKT(){

    override fun newInstance(args: Bundle): BaseFragmentKT {
        var first = FragmentForthKT()
        first.arguments = args
        return first
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_layout_4
    }

    override fun initView() {

    }

}