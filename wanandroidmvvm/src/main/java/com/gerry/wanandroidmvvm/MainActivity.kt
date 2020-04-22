package com.gerry.wanandroidmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.gerry.wanandroidmvvm.first.FirstFragment
import kotlinx.android.synthetic.main.activity_main.*
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.Fragment
import com.gerry.wanandroidmvvm.category.SystemFragment
import com.gerry.wanandroidmvvm.mine.MineFragment
import com.gerry.wanandroidmvvm.project.ProjectFragment
import com.gerry.wanandroidmvvm.wxofficial.WxOfficialFragment


class MainActivity : AppCompatActivity(), BottomNavigationBar.OnTabSelectedListener {

    private var titles = mutableListOf("首页", "知识体系", "项目", "公众号", "我的")

    var firstFragment: FirstFragment? = null
    var categoryFragment: SystemFragment? = null
    var projectFragment: ProjectFragment? = null
    var mineFragment: MineFragment? = null
    var navFragment: WxOfficialFragment? = null

    private var mFragment: Fragment? = null//当前显示的Fragment

    private var transaction: FragmentTransaction? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        initFragment()
    }

    private fun initView() {
        main_bottom_navigation_bar.setTabSelectedListener(this)
        main_bottom_navigation_bar.setMode(BottomNavigationBar.MODE_FIXED)
        main_bottom_navigation_bar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
        main_bottom_navigation_bar.setActiveColor(R.color.colorPrimary)
            .setInActiveColor(R.color.colorUnSelect)//设置Item未选中颜色方法
            .setBarBackgroundColor("#FFFFFF")//背景颜色

        //底部选项按钮
        main_bottom_navigation_bar
            .addItem(BottomNavigationItem(R.mipmap.home_select, titles[0]))
            .addItem(BottomNavigationItem(R.mipmap.category_select, titles[1]))
            .addItem(BottomNavigationItem(R.mipmap.project_select, titles[2]))
            .addItem(BottomNavigationItem(R.mipmap.nav_select, titles[3]))
            .addItem(BottomNavigationItem(R.mipmap.mine_select, titles[4]))
            .setFirstSelectedPosition(0)//默认选中页面
            .initialise()
    }

    private fun initFragment() {
        firstFragment = FirstFragment()
        categoryFragment = SystemFragment()
        projectFragment = ProjectFragment()
        mineFragment = MineFragment()
        navFragment = WxOfficialFragment()

        transaction = supportFragmentManager.beginTransaction()
        transaction?.add(R.id.main_layout_fragment, firstFragment!!)?.commit()
        mFragment = firstFragment
    }

    override fun onTabSelected(position: Int) {
        when (position) {
            0 -> switchFragment(firstFragment!!)
            1 -> switchFragment(categoryFragment!!)
            2 -> switchFragment(projectFragment!!)
            3 -> switchFragment(navFragment!!)
            4 -> switchFragment(mineFragment!!)
        }
    }

    override fun onTabReselected(position: Int) {

    }

    override fun onTabUnselected(position: Int) {
    }

    private fun switchFragment(fragment: Fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if (mFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                supportFragmentManager.beginTransaction().hide(mFragment!!)
                    .add(R.id.main_layout_fragment, fragment).commit()
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                supportFragmentManager.beginTransaction().hide(mFragment!!).show(fragment).commit()
            }
            mFragment = fragment
        }
    }
}
