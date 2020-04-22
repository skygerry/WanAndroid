package com.gerry.wanandroidmvvm.category.tree


import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.gerry.basemvvm.base.BaseFragment

import com.gerry.wanandroidmvvm.category.adapter.SystemChildrenAdapter
import com.gerry.wanandroidmvvm.category.adapter.SystemParentAdapter
import com.gerry.wanandroid.http.bean.Children
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroidmvvm.R
import com.gerry.wanandroidmvvm.category.systemarticle.SystemArticleActivity
import kotlinx.android.synthetic.main.fragment_tree.*

class TreeFragment : BaseFragment<TreeViewModel>() {

    private val systemParentAdapter by lazy { SystemParentAdapter() }
    private val systemChildrenAdapter by lazy { SystemChildrenAdapter() }


    override fun initView(savedInstanceState: Bundle?) {
        with(system_parent_rv) {
            layoutManager = LinearLayoutManager(context)
            adapter = systemParentAdapter
        }
        systemParentAdapter.apply {
            setOnItemClickListener { adapter, _, position ->
                val treeBean = adapter.data[position] as TreeBean
                clickPosition = position
                notifyDataSetChanged()
                systemChildrenAdapter.setNewData(treeBean.children)

            }
        }

        with(system_children_rv) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = systemChildrenAdapter

        }

        systemChildrenAdapter.apply {
            setOnItemClickListener { adapter, _, position ->
                val children = adapter.data[position] as Children
                var intent = Intent(context, SystemArticleActivity::class.java)
                intent.putExtra("cid", children.id)
                intent.putExtra("title", children.name)
                startActivity(intent)
            }
        }
    }

    override fun lazyLoadData() {
        viewModel.run {
            getSystemTree().observe(this@TreeFragment, Observer {
                getSystemTreeSuccess(it)
            })
        }
    }

    private fun getSystemTreeSuccess(data: List<TreeBean>) {
        if (data != null) {
            systemParentAdapter.setNewData(data)
            systemChildrenAdapter.setNewData(data[0].children)
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_tree
}