package com.mark.markkotlinmvp.mvp.model

import com.mark.markkotlinmvp.mvp.model.bean.HomeBean
import com.mark.markkotlinmvp.net.RetrofitManager
import com.mark.markkotlinmvp.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * @Desc  关注Model
 *
 * @作者 Mark
 * @时间 2019/1/2
 * @EMail 2285581945@qq.com
 */
class FollowModel {
    /**
     * 获取关注信息
     */
    fun requestFollowList(): Observable<HomeBean.Issue> {

        return RetrofitManager.service.getFollowInfo()
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多
     */
    fun loadMoreData(url:String): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getIssueData(url)
                .compose(SchedulerUtils.ioToMain())
    }
}