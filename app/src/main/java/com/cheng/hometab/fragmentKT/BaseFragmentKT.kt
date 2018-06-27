package com.cheng.hometab.fragmentKT

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.hometab.MyApp

abstract class BaseFragmentKT : Fragment(), View.OnClickListener {

    protected lateinit var root : View
    private lateinit var mContext : Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun getContext() : Context{
        if(mContext==null) mContext = MyApp.getInstance()
        return mContext
    }

    abstract fun newInstance(args: Bundle): BaseFragmentKT

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater!!.inflate(getLayoutId(), container, false);
        if(root.parent!=null)(root.parent as ViewGroup).removeView(root)
        return root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    abstract fun getLayoutId():Int

    abstract fun initView()

    override fun onClick(view: View) {

    }

}
