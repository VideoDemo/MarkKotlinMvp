package com.mark.markkotlinmvp.mvp.model

import com.mark.markkotlinmvp.mvp.model.bean.HomeBean
import com.mark.markkotlinmvp.net.RetrofitManager
import com.mark.markkotlinmvp.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by xuhao on 2017/11/21.
 * desc: 首页精选 model
 */

class HomeModel{

    /**
     * 获取首页 Banner 数据
     */
    fun requestHomeData(num:Int):Observable<HomeBean>{
        return RetrofitManager.service.getFirstHomeData(num)
                .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多
     */
    fun loadMoreData(url:String):Observable<HomeBean>{

        return RetrofitManager.service.getMoreHomeData(url)
                .compose(SchedulerUtils.ioToMain())
    }



}
