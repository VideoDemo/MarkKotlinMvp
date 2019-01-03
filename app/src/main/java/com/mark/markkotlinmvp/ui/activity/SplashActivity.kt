package com.mark.markkotlinmvp.ui.activity

import android.content.Intent
import android.graphics.Typeface
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.mark.markkotlinmvp.MarkApp
import com.mark.markkotlinmvp.R
import com.mark.markkotlinmvp.base.BaseActivity
import com.mark.markkotlinmvp.utils.AppUtils
import kotlinx.android.synthetic.main.activity_splash.*
import pub.devrel.easypermissions.EasyPermissions


/**
 * @Desc
 *
 * @作者 Mark
 * @时间 2018/12/28
 * @EMail 2285581945@qq.com
 */
class SplashActivity : BaseActivity() {

    private var textTypeface: Typeface?=null

    private var descTypeFace: Typeface?=null

    private var alphaAnimation: AlphaAnimation? = null

    init {
        textTypeface = Typeface.createFromAsset(MarkApp.context.assets,"fonts/Lobster-1.4.otf")
        descTypeFace = Typeface.createFromAsset(MarkApp.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }

    override fun layoutId(): Int = R.layout.activity_splash

    override fun initData() {

    }

    override fun initView() {
        tv_app_name.typeface = textTypeface
        tv_splash_desc.typeface = descTypeFace
        tv_version_name.text = AppUtils.getVerName(this)


        //渐变展示启动屏
        alphaAnimation = AlphaAnimation(0.3f, 1.0f)
        alphaAnimation?.duration = 2000
        alphaAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {
                redirectTo()
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })

        //动态权限
        checkPermission()
    }

    private fun redirectTo() {
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
    private fun checkPermission() {
        val perms = arrayOf(android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
        EasyPermissions.requestPermissions(this, "KotlinMvp应用需要以下权限，请允许", 0, *perms)
    }

    override fun start() {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        super.onPermissionsDenied(requestCode, perms)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        super.onPermissionsGranted(requestCode, perms)
        if (requestCode == 0) {
            if (perms.isNotEmpty()) {
                if (perms.contains(android.Manifest.permission.READ_PHONE_STATE)
                        && perms.contains(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        && perms.contains(android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    if (alphaAnimation != null) {
                        iv_web_icon.startAnimation(alphaAnimation)
                    }
                }
            }
        }
    }

}