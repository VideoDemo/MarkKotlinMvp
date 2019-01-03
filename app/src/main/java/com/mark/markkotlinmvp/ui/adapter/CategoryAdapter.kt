package com.mark.markkotlinmvp.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mark.markkotlinmvp.Constants
import com.mark.markkotlinmvp.MarkApp
import com.mark.markkotlinmvp.R
import com.mark.markkotlinmvp.glide.GlideApp
import com.mark.markkotlinmvp.mvp.model.bean.CategoryBean
import com.mark.markkotlinmvp.ui.activity.CategoryDetailActivity
import com.mark.markkotlinmvp.view.recyclerview.ViewHolder
import com.mark.markkotlinmvp.view.recyclerview.adapter.CommonAdapter

/**
 * Created by xuhao on 2017/11/29.
 * desc: 分类的 Adapter
 */

class CategoryAdapter(mContext: Context, categoryList: ArrayList<CategoryBean>, layoutId: Int) :
        CommonAdapter<CategoryBean>(mContext, categoryList, layoutId) {



    private var textTypeface:Typeface?=null

    init {
        textTypeface = Typeface.createFromAsset(MarkApp.context.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }
    /**
     * 设置新数据
     */
    fun setData(categoryList: ArrayList<CategoryBean>){
        mData.clear()
        mData = categoryList
        notifyDataSetChanged()
    }
    /**
     * 绑定数据
     */
    override fun bindData(holder: ViewHolder, data: CategoryBean, position: Int) {
        holder.setText(R.id.tv_category_name, "#${data.name}")
        //设置方正兰亭细黑简体
        holder.getView<TextView>(R.id.tv_category_name).typeface = textTypeface

        holder.setImagePath(R.id.iv_category, object : ViewHolder.HolderImageLoader(data.bgPicture) {
            override fun loadImage(iv: ImageView, path: String) {
                GlideApp.with(mContext)
                        .load(path)
                        .placeholder(R.color.color_darker_gray)
                        .transition(DrawableTransitionOptions().crossFade())
                        .thumbnail(0.5f)
                        .into(iv)
            }
        })

        holder.setOnItemClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(mContext as Activity, CategoryDetailActivity::class.java)
                intent.putExtra(Constants.BUNDLE_CATEGORY_DATA,data)
                mContext.startActivity(intent)
            }
        })
    }
}
