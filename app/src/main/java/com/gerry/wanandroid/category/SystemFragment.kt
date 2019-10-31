package com.gerry.wanandroid.category


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter

import com.gerry.wanandroid.base.fragement.BaseFragment
import com.gerry.wanandroid.category.adapter.SystemChildrenAdapter
import com.gerry.wanandroid.category.adapter.SystemParentAdapter
import com.gerry.wanandroid.category.mvp.ISystemView
import com.gerry.wanandroid.category.mvp.SystemPresenter
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroid.web.CommentWebActivity
import kotlinx.android.synthetic.main.fragment_system.*


class SystemFragment : BaseFragment<ISystemView, SystemPresenter>(), ISystemView {
    lateinit var systemParentAdapter: SystemParentAdapter
    lateinit var systemChildrenAdapter: SystemChildrenAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        systemParentAdapter = SystemParentAdapter(com.gerry.wanandroid.R.layout.item_system_parent)
        systemParentAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                systemParentAdapter.clickPosition = position
                systemParentAdapter.notifyDataSetChanged()

                systemChildrenAdapter.setNewData(systemParentAdapter.data[position].children)
            }
        system_parent_rv.layoutManager = LinearLayoutManager(mContext)
        system_parent_rv.adapter = systemParentAdapter


        systemChildrenAdapter =
            SystemChildrenAdapter(com.gerry.wanandroid.R.layout.item_system_children)
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

    override fun getArticleListSuccess(data: ArticleList) {
    }


    override fun showLoadingDialog(msg: String) {
    }

    override fun dismissLoadingDialog() {
    }

    override fun onResponseError(msg: String?) {
    }

    override fun createView(): ISystemView = this

    override fun createPresenter(): SystemPresenter = SystemPresenter()

    override fun getLayoutId(): Int = com.gerry.wanandroid.R.layout.fragment_system

    override fun init() {
    }
}