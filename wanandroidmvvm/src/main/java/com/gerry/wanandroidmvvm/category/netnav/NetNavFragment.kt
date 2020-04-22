package com.gerry.wanandroidmvvm.category.netnav

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gerry.basemvvm.base.BaseFragment
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.NaviBean
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroidmvvm.R
import com.gerry.wanandroidmvvm.web.CommentWebActivity
import com.gerry.wanandroidmvvm.category.adapter.NetNavChildrenAdapter
import com.gerry.wanandroidmvvm.category.adapter.NetNavParentAdapter
import kotlinx.android.synthetic.main.fragment_net_nav.*

class NetNavFragment : BaseFragment<NetNavViewModel>() {

    private val netNavParentAdapter by lazy { NetNavParentAdapter() }
    private val netNavChildrenAdapter by lazy { NetNavChildrenAdapter() }


    override fun initView(savedInstanceState: Bundle?) {
        with(net_nev_parent_rv) {
            layoutManager = LinearLayoutManager(context)
            adapter = netNavParentAdapter
        }
        netNavParentAdapter.apply {
            setOnItemClickListener { adapter, _, position ->
                val naviBean = adapter.data[position] as NaviBean
                clickPosition = position
                notifyDataSetChanged()

                netNavChildrenAdapter.setNewData(naviBean.articles)

            }
        }

        with(net_nev_children_rv) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = netNavChildrenAdapter

        }
        netNavChildrenAdapter.apply {
            setOnItemClickListener { adapter, _, position ->
                val articleBean = adapter.data[position] as ArticleBean
                var intent = Intent(context, CommentWebActivity::class.java)
                intent.putExtra("url", articleBean.link)
                startActivity(intent)
            }
        }
    }

    override fun lazyLoadData() {
        viewModel.run {
            getNaviData().observe(this@NetNavFragment, Observer {
                getNaviDataSuccess(it)
            })
        }
    }


    private fun getNaviDataSuccess(data: List<NaviBean>) {
        if (data != null) {
            netNavParentAdapter.setNewData(data)
            netNavChildrenAdapter.setNewData(data[0].articles)
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_net_nav

}
