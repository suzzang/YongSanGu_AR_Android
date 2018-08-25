package com.smtown.yongsangu_ar_project.sookmyung.ending.camera.dialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.smtown.yongsangu_ar_project.R
import kotlinx.android.synthetic.main.dialog_camera_image.*

class CameraImageDialog(activity: Activity) : Dialog(activity) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_camera_image)
        this.setCancelable(false) //뒤로가기,터치 무력화

        btn_close.setOnClickListener {
            dismiss()
            activity.finish()

        }



    }
}