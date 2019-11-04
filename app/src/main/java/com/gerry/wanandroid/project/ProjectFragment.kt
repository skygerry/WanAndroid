package com.gerry.wanandroid.project


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.gerry.wanandroid.R
import com.gerry.wanandroid.base.fragement.BaseFragment
import com.gerry.wanandroid.http.bean.ArticleList
import com.gerry.wanandroid.http.bean.TreeBean
import com.gerry.wanandroid.project.mvp.IProjectView
import com.gerry.wanandroid.project.mvp.ProjectPresenter
import kotlinx.android.synthetic.main.fragment_project.*

class ProjectFragment : BaseFragment<IProjectView, ProjectPresenter>(), IProjectView {
    var fragmentList = mutableListOf<ProjectContentFragment>()
    var projectTree = mutableListOf<TreeBean>()

    lateinit var projectFragmentAdapter: ProjectFragmentAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projectFragmentAdapter = ProjectFragmentAdapter(childFragmentManager)
        project_vp.adapter = projectFragmentAdapter
        project_tabs?.setupWithViewPager(project_vp)

        getPresenter()?.getProjectTree()
    }

    override fun getProjectTreeSuccess(data: List<TreeBean>) {
        if (!data.isNullOrEmpty()) {
            for (i in data) {
                fragmentList.add(ProjectContentFragment.newInstance(i.id))
            }
            projectTree.clear()
            projectTree.addAll(data)
            projectFragmentAdapter.notifyDataSetChanged()
        }
    }

    override fun getArticleListSuccess(data: ArticleList) {
    }

    override fun showLoadingDialog(msg: String) {}

    override fun dismissLoadingDialog() {}

    override fun onResponseError(msg: String?) {}

    override fun createView(): IProjectView = this

    override fun createPresenter(): ProjectPresenter = ProjectPresenter()

    override fun getLayoutId(): Int = R.layout.fragment_project

    override fun init() {}

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
}