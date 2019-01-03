package com.mark.markkotlinmvp.mvp.model

import com.mark.markkotlinmvp.mvp.model.bean.HomeBean
import com.mark.markkotlinmvp.net.RetrofitManager
import com.mark.markkotlinmvp.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by xuhao on 2017/11/30.
 * desc: 排行榜 Model
 */
class RankModel {

    /**
     * 获取排行榜
     */
    fun requestRankList(apiUrl:String): Observable<HomeBean.Issue> {

        return RetrofitManager.service.getIssueData(apiUrl)
                .compose(SchedulerUtils.ioToMain())
    }

}
