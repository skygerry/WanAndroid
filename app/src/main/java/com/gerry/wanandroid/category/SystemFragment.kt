package com.gerry.wanandroid.category


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
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_system.*


class SystemFragment : BaseFragment<BaseView, BasePresenter<BaseView>>(), BaseView {
    private var systemFragments = mutableListOf<Fragment>()
    private lateinit var systemArticleFragmentAdapter: SystemArticleFragmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        systemFragments.add(TreeFragment())
        systemFragments.add(NetNavFragment())

        system_tab.addTab(system_tab.newTab().setText("体系"))
        system_tab.addTab(system_tab.newTab().setText("导航"))
        system_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                system_vp.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        system_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                system_tab.setScrollPosition(position, 0f, true)

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        systemArticleFragmentAdapter =
            SystemArticleFragmentAdapter(activity?.supportFragmentManager)
        system_vp.adapter = systemArticleFragmentAdapter
    }

    override fun getLayoutId(): Int = R.layout.fragment_system

    override fun init() {}
    override fun createView(): BaseView = this
    override fun createPresenter(): BasePresenter<BaseView> = BasePresenter()

    override fun showLoadingDialog(msg: String) {}
    override fun dismissLoadingDialog() {}
    override fun onResponseError(msg: String?) {}


    inner class SystemArticleFragmentAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {
            return systemFragments[position]
        }

        override fun getCount(): Int {
            return systemFragments.size
        }
    }
}