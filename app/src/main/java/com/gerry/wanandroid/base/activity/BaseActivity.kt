package com.gerry.wanandroid.base.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.gerry.wanandroid.base.BasePresenter
import com.gerry.wanandroid.base.view.BaseView

abstract class BaseActivity<V : BaseView, P : BasePresenter<V>> : AppCompatActivity() {
    var presenter: P? = null
    private var view: V? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        if (presenter == null) {
            presenter = createPresenter()
        }

        if (view == null) {
            view = createView()
        }

        if (presenter != null && view != null) {
            presenter?.attachView(view!!)
        }
    }

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