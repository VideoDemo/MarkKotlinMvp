package com.mark.markkotlinmvp.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.mark.markkotlinmvp.R
import com.mark.markkotlinmvp.base.BaseFragment
import com.mark.markkotlinmvp.base.BaseFragmentAdapter
import com.mark.markkotlinmvp.utils.StatusBarUtil
import com.mark.markkotlinmvp.view.TabLayoutHelper
import kotlinx.android.synthetic.main.fragment_hot.*

/**
 * @Desc
 *
 * @作者 Mark
 * @时间 2018/12/28
 * @EMail 2285581945@qq.com
 */
class DiscoveryFragment : BaseFragment() {
    private val tabList = ArrayList<String>()

    private val fragments = ArrayList<Fragment>()

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): DiscoveryFragment {
            val fragment = DiscoveryFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_hot

    override fun initView() {
        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, toolbar) }

        tv_header_title.text = mTitle

        tabList.add("关注")
        tabList.add("分类")
        fragments.add(FollowFragment.getInstance("关注"))
        fragments.add(CategoryFragment.getInstance("分类"))

        /**
         * getSupportFragmentManager() 替换为getChildFragmentManager()
         */
        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager, fragments, tabList)
        mTabLayout.setupWithViewPager(mViewPager)
        TabLayoutHelper.setUpIndicatorWidth(mTabLayout)
    }

    override fun lazyLoad() {

    }
}