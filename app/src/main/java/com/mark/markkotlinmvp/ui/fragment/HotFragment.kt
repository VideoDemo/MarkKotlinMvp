package com.mark.markkotlinmvp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.mark.markkotlinmvp.R
import com.mark.markkotlinmvp.base.BaseFragment
import com.mark.markkotlinmvp.base.BaseFragmentAdapter
import com.mark.markkotlinmvp.mvp.contract.HotTabContract
import com.mark.markkotlinmvp.mvp.model.bean.TabInfoBean
import com.mark.markkotlinmvp.mvp.presenter.HotTabPresenter
import com.mark.markkotlinmvp.net.exception.ErrorStatus
import com.mark.markkotlinmvp.showToast
import com.mark.markkotlinmvp.utils.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * @Desc  热门
 *
 * @作者 Mark
 * @时间 2018/12/28
 * @EMail 2285581945@qq.com
 */
class HotFragment : BaseFragment(), HotTabContract.View {

    private val mPresenter by lazy { HotTabPresenter() }

    private var mTitle: String? = null

    /**
     * 存放 tab 标题
     */
    private val mTabTitleList = ArrayList<String>()

    private val mFragmentList = ArrayList<Fragment>()


    override fun lazyLoad() {
        mPresenter.getTabInfo()
    }

    companion object {
        fun getInstance(title: String): HotFragment {
            val fragment = HotFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    init {
        mPresenter.attachView(this)
    }

    override fun getLayoutId(): Int = R.layout.fragment_hot

    override fun initView() {
        mLayoutStatusView = multipleStatusView
        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, toolbar) }
    }

    override fun showLoading() {
        multipleStatusView.showLoading()
    }

    override fun dismissLoading() {

    }

    /**
     * 设置 TabInfo
     */
    override fun setTabInfo(tabInfoBean: TabInfoBean) {
        multipleStatusView.showContent()

        tabInfoBean.tabInfo.tabList.mapTo(mTabTitleList) { it.name }
        tabInfoBean.tabInfo.tabList.mapTo(mFragmentList) { RankFragment.getInstance(it.apiUrl) }

        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager, mFragmentList, mTabTitleList)
        mTabLayout.setupWithViewPager(mViewPager)

    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView.showNoNetwork()
        } else {
            multipleStatusView.showError()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}