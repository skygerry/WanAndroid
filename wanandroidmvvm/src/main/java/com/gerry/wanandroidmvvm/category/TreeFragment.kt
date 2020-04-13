package com.gerry.wanandroid.category


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter

import com.gerry.wanandroid.R
import com.gerry.wanandroidmvvm.base.fragement.BaseFragment
import com.gerry.wanandroidmvvm.category.adapter.SystemChildrenAdapter
import com.gerry.wanandroidmvvm.category.adapter.SystemParentAdapter
import com.gerry.wanandroid.category.mvp.ISystemView
import com.gerry.wanandroid.category.mvp.SystemPresenter
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.NaviBean
import com.gerry.wanandroid.http.bean.TreeBean
import kotlinx.android.synthetic.main.fragment_tree.*

class TreeFragment : BaseFragment<ISystemView, SystemPresenter>(), ISystemView {

    lateinit var systemParentAdapter: SystemParentAdapter
    lateinit var systemChildrenAdapter: SystemChildrenAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        systemParentAdapter = SystemParentAdapter(R.layout.item_system_parent)
        systemParentAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                systemParentAdapter.clickPosition = position
                systemParentAdapter.notifyDataSetChanged()

                systemChildrenAdapter.setNewData(systemParentAdapter.data[position].children)
            }
        system_parent_rv.layoutManager = LinearLayoutManager(mContext)
        system_parent_rv.adapter = systemParentAdapter


        systemChildrenAdapter =
            SystemChildrenAdapter(R.layout.item_system_children)
        systemChildrenAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                var intent = Intent(mContext, SystemArticleActivity::class.java)
                intent.putExtra("cid", systemChildrenAdapter.data[position].id)
                intent.putExtra("title",systemChildrenAdapter.data[position].name)
                startActivity(intent)
            }

        system_children_rv.layoutManager = GridLayoutManager(mContext, 2)
        system_children_rv.adapter = systemChildrenAdapter


        getPresenter()?.getSystemTree()
    }


    override fun getSystemTreeSuccess(data: List<TreeBean>) {
        if (data != null) {
            systemParentAdapter.setNewData(data)
            systemChildrenAdapter.setNewData(data[0].children)
        }
    }

    override fun getArticleListSuccess(data: ArticleList) {}
    override fun getNaviDataSuccess(data: List<NaviBean>) {}

    override fun showLoadingDialog(msg: String) {}
    override fun dismissLoadingDialog() {}
    override fun onResponseError(msg: String?) {}

    override fun createView(): ISystemView = this
    override fun createPresenter(): SystemPresenter = SystemPresenter()
    override fun getLayoutId(): Int = R.layout.fragment_tree

    override fun init() {
    }
}