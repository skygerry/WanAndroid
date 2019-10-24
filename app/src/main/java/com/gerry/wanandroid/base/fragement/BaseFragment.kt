package com.gerry.wanandroid.base.fragement

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gerry.wanandroid.base.BasePresenter
import com.gerry.wanandroid.base.view.BaseView

abstract class BaseFragment<V : BaseView, P : BasePresenter<V>> : Fragment() {
    private var presenter: P? = null
    private var view: V? = null
    public lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(getLayoutId(), container, false)
        mContext = activity!!
        if (presenter == null) {
            presenter = createPresenter()
        }
        if (this.view == null) {
            this.view = createView()
        }
        if (presenter != null && view != null) {
            presenter?.attachView(this.view!!)
        }
        init()
        return view
    }

    abstract fun init()

    abstract fun createView(): V

    abstract fun createPresenter(): P

    abstract fun getLayoutId(): Int

    override fun onDestroy() {
        super.onDestroy()
        if (presenter != null) {
            presenter?.detachView()
        }
    }
}