package com.mark.markkotlinmvp.mvp.contract

import com.mark.markkotlinmvp.base.IBaseView
import com.mark.markkotlinmvp.base.IPresenter
import com.mark.markkotlinmvp.mvp.model.bean.HomeBean

/**
 * @Desc 契约类
 *
 * @作者 Mark
 * @时间 2019/1/2
 * @EMail 2285581945@qq.com
 */
interface FollowContract {

    interface View:IBaseView{
        /**
         * 设置关注信息数据
         */
        fun setFollowInfo(issue: HomeBean.Issue)

        fun showError(errorMsg: String, errorCode: Int)
    }


    interface Prensenter:IPresenter<View>{
        /**
         * 获取List
         */
        fun requestFollowList()

        /**
         * 加载更多
         */
        fun loadMoreData()
    }

}