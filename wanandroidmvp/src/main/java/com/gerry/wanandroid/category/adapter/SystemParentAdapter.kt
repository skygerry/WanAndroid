package com.gerry.wanandroid.category.adapter

import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gerry.wanandroid.R
import com.gerry.wanandroid.http.bean.TreeBean

class SystemParentAdapter(layoutResId: Int) :
    BaseQuickAdapter<TreeBean, BaseViewHolder>(layoutResId) {

    var clickPosition = 0
    override fun convert(helper: BaseViewHolder, item: TreeBean?) {
        var position = helper.layoutPosition
        if (clickPosition == position) {
            helper.setVisible(R.id.system_parent_select_view, true)
            helper.setTextColor(
                R.id.system_parent_name_tv,
                ContextCompat.getColor(mContext, R.color.colorPrimary)
            )
        } else {
            helper.setVisible(R.id.system_parent_select_view, false)
            helper.setTextColor(
                R.id.system_parent_name_tv,
                ContextCompat.getColor(mContext, R.color.colorBlack)
            )
        }

        helper.setText(R.id.system_parent_name_tv, item?.name)
    }
}