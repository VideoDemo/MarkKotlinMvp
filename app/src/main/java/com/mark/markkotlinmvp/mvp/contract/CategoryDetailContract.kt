package com.mark.markkotlinmvp.mvp.contract

import com.mark.markkotlinmvp.base.IBaseView
import com.mark.markkotlinmvp.base.IPresenter
import com.mark.markkotlinmvp.mvp.model.bean.HomeBean

/**
 * Created by xuhao on 2017/11/30.
 * desc: 分类详情契约类
 */
interface CategoryDetailContract {

    interface View: IBaseView {
        /**
         *  设置列表数据
         */
        fun setCateDetailList(itemList:ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg:String)




    }

    interface Presenter: IPresenter<View> {

        fun getCategoryDetailList(id:Long)

        fun loadMoreData()
    }
}