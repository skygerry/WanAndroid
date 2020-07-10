package com.gerry.wanandroidmvvm.project


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gerry.basemvvm.base.BaseFragment

import com.gerry.wanandroidmvvm.http.bean.TreeBean
import com.gerry.wanandroidmvvm.project.content.ProjectContentFragment
import com.gerry.wanandroidmvvm.R
import kotlinx.android.synthetic.main.fragment_project.*

/**
 * 项目
 */
class ProjectFragment : BaseFragment<ProjectViewModel>() {
    var fragmentList = mutableListOf<ProjectContentFragment>()
    var projectTree = mutableListOf<TreeBean>()

    private lateinit var projectFragmentAdapter: ProjectFragmentAdapter

    override fun initView(savedInstanceState: Bundle?) {
        projectFragmentAdapter = ProjectFragmentAdapter(childFragmentManager)
        project_vp.adapter = projectFragmentAdapter
        project_tabs?.setupWithViewPager(project_vp)
    }

    override fun lazyLoadData() {
        viewModel.run {
            getProjectTree().observe(this@ProjectFragment, Observer {
                getProjectTreeSuccess(it)
            })
        }
    }

    private fun getProjectTreeSuccess(data: List<TreeBean>) {
        if (!data.isNullOrEmpty()) {
            for (i in data) {
                fragmentList.add(ProjectContentFragment.newInstance(i.id))
            }
            projectTree.clear()
            projectTree.addAll(data)
            projectFragmentAdapter.notifyDataSetChanged()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_project


    inner class ProjectFragmentAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return projectTree[position].name
        }
    }

    override fun createViewModel(): ProjectViewModel {
        return ViewModelProvider(this).get(ProjectViewModel::class.java)
    }
}