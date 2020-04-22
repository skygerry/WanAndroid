package com.gerry.wanandroidmvvm.http

import com.gerry.wanandroid.http.bean.*
import com.gerry.wanandroidmvvm.http.bean.ResultData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object WanAndroidRepository {
    //----------------------首页-------------------
    /**
     * 首页文章列表
     * @param page 页码 从0开始
     */
    suspend fun getFirstArticleList(page: Int): ResultData<ArticleList> {
        return WanAndroidRetrofit.service.getFistArticleList(page)
    }

    /**
     * 首页最新项目tab (首页的第二个tab)
     * 按时间分页展示所有项目。
     * @param page 页码 从0开始
     */
    suspend fun getFirstArticleListByTime(page: Int): ResultData<ArticleList> {
        return WanAndroidRetrofit.service.getFirstArticleListByTime(page)
    }

    /**
     * 首页banner
     */
    suspend fun getFirstBanner(): ResultData<List<FirstBannerBean>> {
        return WanAndroidRetrofit.service.getFirstBanner()
    }

    /**
     * 置顶文章
     */
    suspend fun getFirstArticleTop(): ResultData<List<ArticleBean>> {
        return WanAndroidRetrofit.service.getFirstArticleTop()
    }

    /**
     * 常用网站
     */
    suspend fun getFriendWeb(): ResultData<List<FriendWebBean>> {
        return WanAndroidRetrofit.service.getFriendWeb()
    }

    /**
     * 搜索热词
     */
    suspend fun getHotKey(): ResultData<List<HotWordBean>> {
        return WanAndroidRetrofit.service.getHotKey()
    }

    //---------------------------体系---------------------------------------------------
    /**
     * 体系结构数据
     */
    suspend fun getTree(): ResultData<List<TreeBean>> {
        return WanAndroidRetrofit.service.getTree()
    }

    /**
     * 知识体系下的文章
     * @param page 页码 从0开始
     * @param cid 二级目录id
     */
    suspend fun getTreeArticle(page: Int, cid: Int): ResultData<ArticleList> {
        return WanAndroidRetrofit.service.getTreeArticle(page, cid)
    }

    /**
     * 按照作者搜索文章
     */
    suspend fun getArticleByAuthor(page: Int, author: String): ResultData<ArticleList> {
        return WanAndroidRetrofit.service.getArticleByAuthor(page, author)
    }
    //---------------------------导航---------------------------------------------------
    /**
     * 导航数据
     */
    suspend fun getNaviData(): ResultData<List<NaviBean>> {
        return WanAndroidRetrofit.service.getNaviData()
    }

    //---------------------------项目---------------------------------------------------
    /**
     * 项目分类
     */
    suspend fun getProjectTree(): ResultData<List<TreeBean>> {
        return WanAndroidRetrofit.service.getProjectTree()
    }

    /**
     * 项目列表数据
     */
    suspend fun getProjectListByCid(page: Int, cid: Int): ResultData<ArticleList> {
        return WanAndroidRetrofit.service.getProjectListByCid(page, cid)
    }

    //--------------------------公众号--------------------------------------------------
    /**
     * 获取公众号列表
     */
    suspend fun getWxArticleChapters(): ResultData<List<TreeBean>> {
        return WanAndroidRetrofit.service.getWxArticleChapters()
    }

    /**
     * 查看某个公众号的历史数据
     * @param id 公众号id
     * @param page 页码
     */
    suspend fun getWxArticleList(id: Int, page: Int): ResultData<ArticleList> {
        return WanAndroidRetrofit.service.getWxArticleList(id, page)
    }

    /**
     * 在某个公众号中搜索历史文章
     * @param id 公众号id
     * @param page 页码
     * @param key 搜索词
     */
    suspend fun getWxArticleListByKey(id: Int, page: Int, key: String): ResultData<ArticleList> {
        return WanAndroidRetrofit.service.getWxArticleListByKey(id, page, key)
    }
}