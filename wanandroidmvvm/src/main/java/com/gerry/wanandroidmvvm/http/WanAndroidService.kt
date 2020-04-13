package com.gerry.wanandroidmvvm.http

import com.gerry.wanandroid.http.bean.ResultData
import com.gerry.wanandroid.http.bean.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WanAndroidService {
    companion object {
        var BASE_URL = "https://wanandroid.com/"
    }

    /**
     * 获取公众号列表
     */
    @GET("wxarticle/chapters/json")
    suspend fun getWxArticleChapters(): ResultData<List<TreeBean>>

    /**
     * 查看某个公众号的历史数据
     * @param id 公众号id
     * @param page 页码
     */
    @GET("wxarticle/list/{id}/{page}/json")
    suspend fun getWxArticleList(
        @Path("id") id: Int,
        @Path("page") page: Int
    ): ResultData<ArticleList>

    /**
     * 在某个公众号中搜索历史文章
     * @param id 公众号id
     * @param page 页码
     * @param key 搜索词
     */
    @GET("wxarticle/list/{id}/{page}/json")
    suspend fun getWxArticleListByKey(
        @Path("id") id: Int,
        @Path("page") page: Int,
        @Query("k") key: String
    ): ResultData<ArticleList>
//--------------------------首页------------------------------------------------------
    /**
     * 首页文章列表
     * @param page 页码 从0开始
     */
    @GET("article/list/{page}/json")
    suspend fun getFistArticleList(@Path("page") page: Int): ResultData<ArticleList>

    /**
     * 首页最新项目tab (首页的第二个tab)
     * 按时间分页展示所有项目。
     * @param page 页码 从0开始
     */
    @GET("article/listproject/{page}/json")
    suspend fun getFirstArticleListByTime(@Path("page") page: Int): ResultData<ArticleList>

    /**
     * 首页banner
     */
    @GET("banner/json")
    suspend  fun getFirstBanner(): ResultData<List<FirstBannerBean>>

    /**
     * 置顶文章
     */
    @GET("article/top/json")
    suspend fun getFirstArticleTop(): ResultData<List<ArticleBean>>

    /**
     * 常用网站
     */
    @GET("friend/json")
    suspend fun getFriendWeb(): ResultData<List<FriendWebBean>>

    /**
     * 搜索热词
     */
    @GET("hotkey/json")
    suspend fun getHotKey(): ResultData<List<HotWordBean>>

//---------------------------体系---------------------------------------------------
    /**
     * 体系结构数据
     */
    @GET("tree/json")
    suspend fun getTree(): ResultData<List<TreeBean>>

    /**
     * 知识体系下的文章
     * @param page 页码 从0开始
     * @param cid 二级目录id
     */
    @GET("article/list/{page}/json")
    suspend fun getTreeArticle(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): ResultData<ArticleList>

    /**
     * 按照作者搜索文章
     */
    @GET("article/list/{page}/json")
    suspend  fun getArticleByAuthor(
        @Path("page") page: Int,
        @Query("author") author: String
    ): ResultData<ArticleList>

//---------------------------导航---------------------------------------------------
    /**
     * 导航数据
     */
    @GET("navi/json")
    suspend fun getNaviData(): ResultData<List<NaviBean>>

//---------------------------项目---------------------------------------------------
    /**
     * 项目分类
     */
    @GET("project/tree/json")
    suspend fun getProjectTree(): ResultData<List<TreeBean>>

    /**
     * 项目列表数据
     */
    @GET("project/list/{page}/json")
    suspend fun getProjectListByCid(
        @Path("page") page: Int,
        @Query("cid") cid: Int
    ): ResultData<ArticleList>


}