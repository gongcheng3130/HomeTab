package com.cheng.hometab.fragmentKT

import android.os.Bundle
import com.cheng.hometab.R

class FragmentSecondKT : BaseFragmentKT(){

    override fun newInstance(args: Bundle): BaseFragmentKT {
        var first = FragmentSecondKT()
        first.arguments = args
        return first
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_layout_2
    }

    override fun initView() {

    }

}