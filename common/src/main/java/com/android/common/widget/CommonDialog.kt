package com.android.common.widget

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.android.common.R
import com.android.common.databinding.CommonDialogLayoutBinding

/**
 * author:aclidae on 2023/3/31 15 35
 * des: 普通的dialog
 */
class CommonDialog(context: Context):Dialog(context) {

    var binding:CommonDialogLayoutBinding

    init {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.common_dialog_layout,null,false)

        val params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        setContentView(binding.root,params)
        val attributes = window!!.attributes
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT
        attributes.gravity = Gravity.BOTTOM
        window!!.apply {
            setBackgroundDrawableResource(android.R.color.transparent)
            decorView.setPadding(0, 0, 0, 0)
            this.attributes = attributes
        }
    }

    fun setTitle(title:String):CommonDialog{
        binding.dialogTitle.text = title
        return this;
    }

    interface OnClickDialog{
        fun onOk()
        fun onCancel()
    }

    fun setOnClickDialog(onClickDialog: OnClickDialog):CommonDialog{
        binding.onClickDialog = onClickDialog
        return this
    }
}