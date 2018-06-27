package com.cheng.hometab.fragmentKT

import android.os.Bundle
import com.cheng.hometab.R

class FragmentFirstKT : BaseFragmentKT(){

    override fun newInstance(args: Bundle): BaseFragmentKT {
        var first = FragmentFirstKT()
        first.arguments = args
        return first
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_layout_1
    }

    override fun initView() {

    }

}