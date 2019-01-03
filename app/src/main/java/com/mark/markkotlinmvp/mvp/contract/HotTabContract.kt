package com.mark.markkotlinmvp.mvp.contract

import com.mark.markkotlinmvp.base.IBaseView
import com.mark.markkotlinmvp.base.IPresenter
import com.mark.markkotlinmvp.mvp.model.bean.TabInfoBean

/**
 * @Desc 契约类
 *
 * @作者 Mark
 * @时间 2019/1/2
 * @EMail 2285581945@qq.com
 */
interface HotTabContract {

    interface View:IBaseView{
        /**
         * 设置 TabInfo
         */
        fun setTabInfo(tabInfoBean: TabInfoBean)

        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter: IPresenter<View> {
        /**
         * 获取 TabInfo
         */
        fun getTabInfo()
    }

}