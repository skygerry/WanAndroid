package com.gerry.wanandroid.navigation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

import com.gerry.wanandroid.R
import com.gerry.wanandroid.base.BasePresenter
import com.gerry.wanandroid.base.fragement.BaseFragment
import com.gerry.wanandroid.base.view.BaseView
import com.gerry.wanandroid.first.NewArticleFragment
import com.gerry.wanandroid.first.NewProjectFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_nav.*

class NavFragment : BaseFragment<BaseView, BasePresenter<BaseView>>(), BaseView {
    private var firstFragments = mutableListOf<Fragment>()
    private lateinit var firstArticleFragmentAdapter: FirstArticleFragmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstFragments.add(NewArticleFragment())
        firstFragments.add(NewProjectFragment())

        nav_tab.addTab(nav_tab.newTab().setText("网站导航"))
        nav_tab.addTab(nav_tab.newTab().setText("公众号"))
        nav_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                first_vp.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        nav_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                nav_tab.setScrollPosition(position, 0f, true)

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        firstArticleFragmentAdapter = FirstArticleFragmentAdapter(activity?.supportFragmentManager)
        nav_vp.adapter = firstArticleFragmentAdapter
    }

    override fun getLayoutId(): Int = R.layout.fragment_nav

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
