package com.gerry.wanandroidmvvm.wxofficial

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gerry.basemvvm.base.BaseFragment
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroidmvvm.wxofficial.content.WxOfficialContentFragment
import com.gerry.wanandroidmvvm.R
import com.gerry.wanandroidmvvm.category.netnav.NetNavViewModel
import kotlinx.android.synthetic.main.fragment_wx_official.*

/**
 * 公众号
 */
class WxOfficialFragment : BaseFragment<WxOfficialViewModel>() {


    var fragmentList = mutableListOf<WxOfficialContentFragment>()
    var wxTree = mutableListOf<TreeBean>()

    private lateinit var wxOfficialFragmentAdapter: WxOfficialFragmentAdapter


    override fun initView(savedInstanceState: Bundle?) {
        wxOfficialFragmentAdapter = WxOfficialFragmentAdapter(childFragmentManager)
        wx_vp.adapter = wxOfficialFragmentAdapter
        wx_tabs?.setupWithViewPager(wx_vp)

    }

    override fun lazyLoadData() {
        viewModel.run {
            getWxArticleChapters().observe(this@WxOfficialFragment, Observer {
                getWxArticleChapterSuccess(it)
            })
        }
    }

    private fun getWxArticleChapterSuccess(data: List<TreeBean>) {
        if (!data.isNullOrEmpty()) {
            for (i in data) {
                fragmentList.add(WxOfficialContentFragment.newInstance(i.id))
            }
            wxTree.clear()
            wxTree.addAll(data)
            wxOfficialFragmentAdapter.notifyDataSetChanged()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_wx_official

    override fun createViewModel(): WxOfficialViewModel {
        return ViewModelProvider(this).get(WxOfficialViewModel::class.java)
    }

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