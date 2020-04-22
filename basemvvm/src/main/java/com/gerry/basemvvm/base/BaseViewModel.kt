package com.gerry.basemvvm.base

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.Utils
import com.gerry.basemvvm.event.Message
import com.gerry.basemvvm.network.ApiException
import com.gerry.basemvvm.event.SingleLiveEvent
import com.gerry.basemvvm.network.ExceptionUtil
import kotlinx.coroutines.*

open class BaseViewModel : AndroidViewModel(Utils.getApp()), LifecycleObserver {
    val defUI: UIChange by lazy { UIChange() }

    /**
     * 所有的网络请求都是在viewModelScope域中启动，
     * 当页面销毁时会自动调用viewModel的 onCleared方法取消所有协程
     */
    fun launchUI(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch {
            block()
        }

    /**
     * 不过滤请求结果的异常统一处理处理
     */
    private suspend fun handleException(
        block: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(ApiException) -> Unit,
        complete: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            try {
                block()
            } catch (e: Throwable) {
                error(ExceptionUtil.catchException(e))
            } finally {
                complete()
            }
        }
    }

    /**
     * 不过滤请求结果
     * @param block 数据请求
     * @param error 失败回调
     * @param complete 完成回调(无论失败成功都会回到)
     * @param isShowDialog 是否显示加载框
     *
     */
    fun launchGo(
        block: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(ApiException) -> Unit = {
            defUI.toastEvent.postValue("${it.code}:${it.errorMsg}")
        },
        complete: suspend CoroutineScope.() -> Unit = {},
        isShowDialog: Boolean = true
    ) {
        if (isShowDialog) {
            defUI.showDialog.call()
        }
        launchUI {
            handleException(
                { withContext(Dispatchers.IO) { block } },
                { error(it) },
                {
                    defUI.dismissDialog.call()
                    complete()
                }
            )
        }
    }

    /**
     * 过滤请求结果的统一异常处理
     */
    suspend fun <T> handleException(
        block: suspend CoroutineScope.() -> IBaseResponse<T>,
        success: suspend CoroutineScope.(IBaseResponse<T>) -> Unit,
        error: suspend CoroutineScope.(ApiException) -> Unit,
        complete: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            try {
                success(block())
            } catch (e: Throwable) {
                error(ExceptionUtil.catchException(e))
            } finally {
                complete()
            }
        }
    }

    /**
     * 过滤请求结果
     */
    suspend fun <T> executeResponse(
        response: IBaseResponse<T>,
        success: suspend CoroutineScope.(T) -> Unit
    ) {
        coroutineScope {
            if (response.isSuccess()) success(response.data())
            else throw ApiException(response.code(), response.msg())
        }
    }

    /**
     * 过滤请求结果
     */
    fun <T> launchOnlyData(
        block: suspend CoroutineScope.() -> IBaseResponse<T>,
        success: (T) -> Unit,
        error: (ApiException) -> Unit = {
            defUI.toastEvent.postValue("${it.code}:${it.errorMsg}")
        },
        complete: () -> Unit = {},
        isShowDialog: Boolean = true
    ) {
        if (isShowDialog) defUI.showDialog.call()
        launchUI {
            handleException(
                { withContext(Dispatchers.IO) { block() } },
                { res -> executeResponse(res) { success(it) } },
                { error(it) },
                {
                    defUI.dismissDialog.call()
                    complete()
                }


            )
        }
    }


    /**
     * UI事件
     */
    inner class UIChange {
        val showDialog by lazy { SingleLiveEvent<String>() }
        val dismissDialog by lazy { SingleLiveEvent<Void>() }
        val toastEvent by lazy { SingleLiveEvent<String>() }
        val message by lazy { SingleLiveEvent<Message>() }
    }
}