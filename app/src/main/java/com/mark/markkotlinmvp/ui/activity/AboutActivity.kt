package com.mark.markkotlinmvp.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import com.mark.markkotlinmvp.MarkApp
import com.mark.markkotlinmvp.R
import com.mark.markkotlinmvp.base.BaseActivity
import com.mark.markkotlinmvp.utils.AppUtils
import com.mark.markkotlinmvp.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_about.*

/**
 * Created by xuhao on 2017/12/6.
 * desc: 关于
 */
class AboutActivity : BaseActivity() {
    override fun layoutId(): Int = R.layout.activity_about

    override fun initData() {
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, toolbar)

        tv_version_name.text ="v${AppUtils.getVerName(MarkApp.context)}"
        //返回
        toolbar.setNavigationOnClickListener { finish() }
        //访问 GitHub
        relayout_gitHub.setOnClickListener {
            val uri = Uri.parse("https://markchyl.github.io/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }

    override fun start() {

    }
}