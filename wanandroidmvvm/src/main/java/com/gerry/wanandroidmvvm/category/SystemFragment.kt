package com.gerry.wanandroidmvvm.category


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.gerry.wanandroidmvvm.R
import com.gerry.wanandroidmvvm.category.tree.TreeFragment
import com.gerry.wanandroidmvvm.category.netnav.NetNavFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_system.*

/**
 * 知识体系
 */
class SystemFragment : Fragment() {
    private var systemFragments = mutableListOf<Fragment>()
    private lateinit var systemArticleFragmentAdapter: SystemArticleFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_system, container, false)
    }

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

    inner class SystemArticleFragmentAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {
            return systemFragments[position]
        }

        override fun getCount(): Int {
            return systemFragments.size
        }
    }
}