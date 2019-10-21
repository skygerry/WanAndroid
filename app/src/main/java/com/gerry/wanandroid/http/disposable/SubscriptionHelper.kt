package com.gerry.wanandroid.http.disposable

import io.reactivex.disposables.Disposable

/**
 * 处理订阅关系的接口
 */
interface SubscriptionHelper<T> {
    fun add(subscription: Disposable)

    fun cancel(t: Disposable)

    fun cancelall()
}