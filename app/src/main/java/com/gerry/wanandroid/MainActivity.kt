package com.gerry.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gerry.wanandroid.base.activity.BaseActivity
import com.gerry.wanandroid.first.mvp.FirstPresenter
import com.gerry.wanandroid.first.mvp.IFirstView
import com.gerry.wanandroid.http.bean.ArticleList

class MainActivity : BaseActivity<IFirstView, FirstPresenter>(), IFirstView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        presenter?.getFirstArticleList(0)
    }

    override fun createView(): IFirstView {
        return this
    }

    override fun createPresenter(): FirstPresenter {
        return FirstPresenter()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onResponseError(msg: String?) {

    }

    override fun getFirstArticleListSuccess(articleList: ArticleList) {
        if (articleList != null) {
            for (i in articleList.datas) {
                Log.e("xyh", "onResponse: " + i.title)
            }
        }
        Log.e("----->", articleList.datas.size.toString())
    }

    override fun showLoadingDialog(msg: String) {
    }

    override fun dismissLoadingDialog() {
    }
}
