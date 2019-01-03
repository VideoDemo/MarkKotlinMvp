package com.mark.markkotlinmvp.mvp.model

import com.mark.markkotlinmvp.mvp.model.bean.HomeBean
import com.mark.markkotlinmvp.net.RetrofitManager
import com.mark.markkotlinmvp.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by xuhao on 2017/11/25.
 * desc:
 */
class VideoDetailModel {

    fun requestRelatedData(id:Long):Observable<HomeBean.Issue>{

        return RetrofitManager.service.getRelatedData(id)
                .compose(SchedulerUtils.ioToMain())
    }

}