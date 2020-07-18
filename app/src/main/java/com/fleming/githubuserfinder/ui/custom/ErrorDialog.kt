package com.fleming.githubuserfinder.ui.custom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.fleming.githubuserfinder.R
import kotlinx.android.synthetic.main.dialog_error.*

class ErrorDialog : DialogFragment() {

    private var mActionListener: (() -> Unit)? = null
    private var mMessage: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_error, container, false)
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return view
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        if (mMessage.isNotBlank()) {
            tvErrorMessage?.text = mMessage
        }
        btnErrorOk?.setOnClickListener {
            mActionListener?.invoke()
            dismiss()
        }
    }

    fun setMessage(mDesc: String) {
        this.mMessage = mDesc
    }

}
