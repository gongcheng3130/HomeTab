package com.cheng.hometab.fragmentKT

import android.os.Bundle
import com.cheng.hometab.R

class FragmentThirdKT : BaseFragmentKT(){

    override fun newInstance(args: Bundle): BaseFragmentKT {
        var first = FragmentThirdKT()
        first.arguments = args
        return first
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_layout_3
    }

    override fun initView() {

    }

}