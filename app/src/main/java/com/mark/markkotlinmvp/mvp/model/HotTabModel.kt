package com.mark.markkotlinmvp.mvp.model

import com.mark.markkotlinmvp.mvp.model.bean.TabInfoBean
import com.mark.markkotlinmvp.net.RetrofitManager
import com.mark.markkotlinmvp.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by xuhao on 2017/11/30.
 * desc: 热门 Model
 */
class HotTabModel {

    /**
     * 获取 TabInfo
     */
    fun getTabInfo(): Observable<TabInfoBean> {

        return RetrofitManager.service.getRankList()
                .compose(SchedulerUtils.ioToMain())
    }

}
