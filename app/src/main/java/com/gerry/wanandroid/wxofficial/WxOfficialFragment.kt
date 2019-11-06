package com.gerry.wanandroid.wxofficial

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.gerry.wanandroid.R
import com.gerry.wanandroid.base.fragement.BaseFragment
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.NaviBean
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroid.project.ProjectContentFragment
import com.gerry.wanandroid.project.ProjectFragment
import com.gerry.wanandroid.project.mvp.IProjectView
import com.gerry.wanandroid.project.mvp.ProjectPresenter
import com.gerry.wanandroid.wxofficial.mvp.IWxOfficialView
import com.gerry.wanandroid.wxofficial.mvp.WxOfficialPresenter
import kotlinx.android.synthetic.main.fragment_project.*
import kotlinx.android.synthetic.main.fragment_wx_official.*

/**
 * 公众号
 */
class WxOfficialFragment : BaseFragment<IWxOfficialView, WxOfficialPresenter>(), IWxOfficialView {


    var fragmentList = mutableListOf<WxOfficialContentFragment>()
    var wxTree = mutableListOf<TreeBean>()

    lateinit var wxOfficialFragmentAdapter: WxOfficialFragmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wxOfficialFragmentAdapter = WxOfficialFragmentAdapter(childFragmentManager)
        wx_vp.adapter = wxOfficialFragmentAdapter
        wx_tabs?.setupWithViewPager(wx_vp)

        getPresenter()?.getWxArticleChapters()
    }

    override fun getWxArticleChapterSuccess(data: List<TreeBean>) {
        if (!data.isNullOrEmpty()) {
            for (i in data) {
                fragmentList.add(WxOfficialContentFragment.newInstance(i.id))
            }
            wxTree.clear()
            wxTree.addAll(data)
            wxOfficialFragmentAdapter.notifyDataSetChanged()
        }
    }

    override fun getWxArticleListSuccess(data: ArticleList) {}

    override fun showLoadingDialog(msg: String) {}
    override fun dismissLoadingDialog() {}
    override fun onResponseError(msg: String?) {}

    override fun createView(): IWxOfficialView = this
    override fun createPresenter(): WxOfficialPresenter = WxOfficialPresenter()
    override fun getLayoutId(): Int = R.layout.fragment_wx_official
    override fun init() {}

    inner class WxOfficialFragmentAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return wxTree[position].name
        }
    }
}