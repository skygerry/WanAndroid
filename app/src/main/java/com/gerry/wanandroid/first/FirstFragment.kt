package com.gerry.wanandroid.first


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.gerry.wanandroid.R
import com.gerry.wanandroid.base.BasePresenter

import com.gerry.wanandroid.base.fragement.BaseFragment
import com.gerry.wanandroid.base.view.BaseView
import com.gerry.wanandroid.first.mvp.FirstPresenter
import com.gerry.wanandroid.first.mvp.IFirstView
import com.gerry.wanandroid.http.bean.ArticleBean
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.FirstBannerBean
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_first.*


/**
 * 首页
 */
class FirstFragment : BaseFragment<BaseView, BasePresenter<BaseView>>(), BaseView {
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
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                first_tab.setScrollPosition(position, 0f, true)

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        firstArticleFragmentAdapter = FirstArticleFragmentAdapter(activity?.supportFragmentManager)
        first_vp.adapter = firstArticleFragmentAdapter
    }

    override fun getLayoutId(): Int = R.layout.fragment_first

    override fun init() {}
    override fun createView(): BaseView = this
    override fun createPresenter(): BasePresenter<BaseView> = BasePresenter()

    override fun showLoadingDialog(msg: String) {}
    override fun dismissLoadingDialog() {}
    override fun onResponseError(msg: String?) {}


    inner class FirstArticleFragmentAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {
            return firstFragments[position]
        }

        override fun getCount(): Int {
            return firstFragments.size
        }
    }
}
