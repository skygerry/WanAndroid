package com.gerry.wanandroid.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gerry.wanandroid.R
import com.gerry.wanandroidmvvm.base.fragement.BaseFragment
import com.gerry.wanandroid.category.mvp.ISystemView
import com.gerry.wanandroid.category.mvp.SystemPresenter
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.NaviBean
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroid.web.CommentWebActivity
import com.gerry.wanandroidmvvm.category.adapter.NetNavChildrenAdapter
import com.gerry.wanandroidmvvm.category.adapter.NetNavParentAdapter
import kotlinx.android.synthetic.main.fragment_net_nav.*

class NetNavFragment : BaseFragment<ISystemView, SystemPresenter>(), ISystemView {

    lateinit var netNavParentAdapter: NetNavParentAdapter
    lateinit var netNavChildrenAdapter: NetNavChildrenAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        netNavParentAdapter = NetNavParentAdapter(R.layout.item_system_parent)
        netNavParentAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                netNavParentAdapter.clickPosition = position
                netNavParentAdapter.notifyDataSetChanged()

                netNavChildrenAdapter.setNewData(netNavParentAdapter.data[position].articles)
            }
        net_nev_parent_rv.layoutManager = LinearLayoutManager(mContext)
        net_nev_parent_rv.adapter = netNavParentAdapter


        netNavChildrenAdapter = NetNavChildrenAdapter(R.layout.item_system_children)
        netNavChildrenAdapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
                var intent = Intent(mContext, CommentWebActivity::class.java)
                intent.putExtra("url", netNavChildrenAdapter.data[position].link)
                startActivity(intent)
            }

        net_nev_children_rv.layoutManager = GridLayoutManager(mContext, 2)
        net_nev_children_rv.adapter = netNavChildrenAdapter


        getPresenter()?.getNaviData()
    }


    override fun getNaviDataSuccess(data: List<NaviBean>) {
        if (data != null) {
            netNavParentAdapter.setNewData(data)
            netNavChildrenAdapter.setNewData(data[0].articles)
        }
    }

    override fun getSystemTreeSuccess(data: List<TreeBean>) {}

    override fun getArticleListSuccess(data: ArticleList) {}


    override fun showLoadingDialog(msg: String) {}
    override fun dismissLoadingDialog() {}
    override fun onResponseError(msg: String?) {}

    override fun init() {}

    override fun createView(): ISystemView = this
    override fun createPresenter(): SystemPresenter = SystemPresenter()
    override fun getLayoutId(): Int = R.layout.fragment_net_nav

}
