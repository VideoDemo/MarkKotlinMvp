package com.mark.markkotlinmvp.mvp.presenter

import com.mark.markkotlinmvp.base.BasePresenter
import com.mark.markkotlinmvp.mvp.contract.FollowContract
import com.mark.markkotlinmvp.mvp.model.FollowModel
import com.mark.markkotlinmvp.net.exception.ExceptionHandle

/**
 * @Desc
 *
 * @作者 Mark
 * @时间 2019/1/2
 * @EMail 2285581945@qq.com
 */
class FollowPresenter:BasePresenter<FollowContract.View>(),FollowContract.Prensenter {

    private val followModel by lazy { FollowModel() }

    private var nextPageUrl:String?=null

    /**
     *  请求关注数据
     */
    override fun requestFollowList() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = followModel.requestFollowList()
                .subscribe({ issue ->
                    mRootView?.apply {
                        dismissLoading()
                        nextPageUrl = issue.nextPageUrl
                        setFollowInfo(issue)
                    }
                }, { throwable ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(throwable),ExceptionHandle.errorCode)
                    }
                })
        addSubscription(disposable)
    }

    /**
     * 加载更多
     */
    override fun loadMoreData(){
        val disposable = nextPageUrl?.let {
            followModel.loadMoreData(it)
                    .subscribe({ issue->
                        mRootView?.apply {
                            nextPageUrl = issue.nextPageUrl
                            setFollowInfo(issue)
                        }

                    },{ t ->
                        mRootView?.apply {
                            showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                        }
                    })


        }
        if (disposable != null) {
            addSubscription(disposable)
        }
    }
}