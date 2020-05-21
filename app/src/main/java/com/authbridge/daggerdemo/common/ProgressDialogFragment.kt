package com.authbridge.daggerdemo.common

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.authbridge.daggerdemo.R

class ProgressDialogFragment : DialogFragment, View.OnClickListener {

    private constructor()

    private var message: String = "Please wait..."
    private var textViewProgressMessage: TextView?=null
    private var buttonCancelProgress: ImageView?=null
    private var listener: ProgressActionListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.progress_bar, null)
        textViewProgressMessage = view.findViewById<TextView>(R.id.textViewProgressMessage)
        textViewProgressMessage!!.text = message
        buttonCancelProgress = view.findViewById<ImageView>(R.id.buttonCancelProgress)
        buttonCancelProgress!!.setOnClickListener(this)
        buttonCancelProgress!!.visibility=View.GONE
        isCancelable = false
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return view
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.buttonCancelProgress -> {
                if (listener != null) {
                    listener!!.onProgressClosed()
                }
            }
        }
    }

    companion object {
        var INSTANCE: ProgressDialogFragment? = null

        fun getProgressDialog(): ProgressDialogFragment {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = ProgressDialogFragment()
                    }
                }
            }
            return INSTANCE!!
        }
    }


    fun showDialog(
        manager: FragmentManager?,
        tag: String,
        message: String,
        listener: ProgressActionListener?
    ) {
        if (textViewProgressMessage != null) {
            textViewProgressMessage!!.text = message
        }
        this.listener = listener
        if (this.listener != null) {
            buttonCancelProgress!!.visibility = View.VISIBLE
        } else {
            buttonCancelProgress!!.visibility = View.GONE
        }
        super.show(manager!!, tag)
    }

    interface ProgressActionListener {
        fun onProgressClosed()
    }
}