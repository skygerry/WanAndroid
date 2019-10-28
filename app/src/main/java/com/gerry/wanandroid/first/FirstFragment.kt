package com.gerry.wanandroid.first


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.gerry.wanandroid.R

import com.gerry.wanandroid.base.fragement.BaseFragment
import com.gerry.wanandroid.first.mvp.FirstPresenter
import com.gerry.wanandroid.first.mvp.IFirstView
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.FirstBannerBean
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : BaseFragment<IFirstView, FirstPresenter>(), IFirstView {
    private var firstFragments = mutableListOf<Fragment>()
    private lateinit var firstArticleFragmentAdapter: FirstArticleFragmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstFragments.add(NewArticleFragment())
        firstFragments.add(NewProjectFragment())

        first_tab.addTab(first_tab.newTab().setText("最新博文"))
        first_tab.addTab(first_tab.newTab().setText("最新项目"))
        first_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                first_vp.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        first_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                first_tab.setScrollPosition(position, 0f, true)

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        firstArticleFragmentAdapter = FirstArticleFragmentAdapter(activity?.supportFragmentManager)
        first_vp.adapter = firstArticleFragmentAdapter
//        getPresenter()?.getFirstArticleByTimeList(0)
    }

    override fun createView(): IFirstView {
        return this
    }

    override fun createPresenter(): FirstPresenter {
        return FirstPresenter()
    }

    override fun getLayoutId(): Int = R.layout.fragment_first


    override fun init() {

    }

    inner class FirstArticleFragmentAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {
            return firstFragments[position]
        }

        override fun getCount(): Int {
            return firstFragments.size
        }
    }


    override fun getFirstArticleListSuccess(articleList: ArticleList) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFirstArticleByTimeListSuccess(data: ArticleList) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFirstBannerSuccess(data: List<FirstBannerBean>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFirstArticleTopSuccess(data: List<ArticleBean>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoadingDialog(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissLoadingDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResponseError(msg: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
