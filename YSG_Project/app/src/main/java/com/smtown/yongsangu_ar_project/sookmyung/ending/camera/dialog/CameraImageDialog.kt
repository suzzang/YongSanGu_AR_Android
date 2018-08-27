package com.smtown.yongsangu_ar_project.sookmyung.ending.camera.dialog

import android.app.Activity
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import android.view.Window
import com.smtown.yongsangu_ar_project.R
import com.smtown.yongsangu_ar_project.sookmyung.ending.camera.MyCameraPreview
import kotlinx.android.synthetic.main.dialog_camera_image.*
import java.io.File
import java.io.FileDescriptor.out
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class CameraImageDialog(activity: Activity, bitmap: Bitmap?) : Dialog(activity) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_camera_image)
        this.setCancelable(false) //뒤로가기,터치 무력화
        captureimg.setImageBitmap(bitmap)

        btn_save.setOnClickListener {
            saveBitmaptoJpeg(bitmap)
            activity.finish()
            //저장되었습니다 토스트 메시지 띄우기

        }



    }

    /** * Image SDCard Save (input Bitmap -> saved file JPEG) * Writer intruder(Kwangseob Kim) * @param bitmap : input bitmap file * @param folder : input folder name * @param name : output file name  */
    fun saveBitmaptoJpeg(bitmap: Bitmap?) {
        val ex_storage = Environment.getExternalStorageDirectory().absolutePath // Get Absolute Path in External Sdcard
        val folder_name = "/" + "camtest2" + "/"
        val file_name = String.format("%d.jpg", System.currentTimeMillis())
        val string_path = ex_storage + folder_name
        val file_path: File
        try {
            file_path = File(string_path)
            if (!file_path.isDirectory) {
                file_path.mkdirs()
            }
            val out = FileOutputStream(string_path + file_name)
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, out)
            context.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file_path)))
            out.close()
        } catch (exception: FileNotFoundException) {
            Log.e("FileNotFoundException", exception.message)
        } catch (exception: IOException) {
            Log.e("IOException", exception.message)
        }

//        갤러리로 새로 "사진찍었어요" 하로 broadcasting 을하던지.
//        sendBroadcast(new Intent("com.android.camera.NEW_PICTURE", mLastContentUri));

       // "SD Card 다시 스캔 하세요", 라고 하면 되겠죠.

        //sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,Uri.parse ("file://" + Environment.getExternalStorageDirectory())))


    }


}