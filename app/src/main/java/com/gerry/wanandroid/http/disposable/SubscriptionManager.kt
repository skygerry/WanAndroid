package com.gerry.wanandroid.http.disposable

import io.reactivex.disposables.Disposable
import io.reactivex.disposables.CompositeDisposable


class SubscriptionManager : SubscriptionHelper<Any> {

    private var mDisposables: CompositeDisposable? = null

    companion object {
        var subscriptionManager: SubscriptionManager? = null

        fun getInstance(): SubscriptionManager? {
            if (subscriptionManager == null) {
                synchronized(SubscriptionManager::class.java) {
                    if (subscriptionManager == null) {
                        subscriptionManager = SubscriptionManager()
                    }
                }
            }
            return subscriptionManager
        }

    }

    init {
        if (mDisposables == null) {
            mDisposables = CompositeDisposable()
        }
    }


    override fun add(subscription: Disposable) {
        if (subscription != null) {
            mDisposables?.add(subscription)
        }

    }

    override fun cancel(t: Disposable) {
        if (t != null) {
            mDisposables?.delete(t)
        }
    }

    override fun cancelall() {
        mDisposables?.clear()
    }
}