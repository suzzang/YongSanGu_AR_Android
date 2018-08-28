package com.smtown.yongsangu_ar_project.sookmyung.ending.reward.dialog

import android.app.Activity
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.Window
import android.widget.Toast
import com.smtown.yongsangu_ar_project.MainActivity
import com.smtown.yongsangu_ar_project.R
import com.smtown.yongsangu_ar_project.sookmyung.ar.ARActivity
import com.smtown.yongsangu_ar_project.sookmyung.ending.reward.RewardInputActivity
import kotlinx.android.synthetic.main.dialog_camera_image.*
import kotlinx.android.synthetic.main.dialog_camera_reward.*
import java.io.*

class RewardCameraDialog(activity: Activity, bitmap: Bitmap?) : Dialog(activity) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_camera_reward)
        this.setCancelable(false) //뒤로가기,터치 무력화
       rewardimg.setImageBitmap(bitmap)

        btn_reward_save.setOnClickListener {
            saveBitmaptoJpeg(bitmap)
            Toast.makeText(context,"저장되었습니다", Toast.LENGTH_SHORT).show()

            dismiss()
            val intent = Intent(context,MainActivity::class.java) //저장을 하고나면 표창장 받는 부분으로 전환
            activity.startActivity(intent)

        }



    }

    /** * Image SDCard Save (input Bitmap -> saved file JPEG) * Writer intruder(Kwangseob Kim) * @param bitmap : input bitmap file * @param folder : input folder name * @param name : output file name  */
    fun saveBitmaptoJpeg(bitmap: Bitmap?) {

        //비트맵을 바이트array로 바꾸는 부분
        val stream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val currentData = stream.toByteArray()

        try {
            val path = File(Environment.getExternalStorageDirectory().absolutePath + "/camtest")
            if (!path.exists()) {
                path.mkdirs()
            }

            val fileName = String.format("%d.jpg", System.currentTimeMillis())
            val outputFile = File(path, fileName)

            val outStream = FileOutputStream(outputFile)
            outStream.write(currentData)
            outStream.flush()
            outStream.close()

            Log.d(ContentValues.TAG, "onPictureTaken - wrote bytes: " + currentData.size + " to "
                    + outputFile.absolutePath)


            // 갤러리에 반영
            val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            mediaScanIntent.data = Uri.fromFile(outputFile)
            context.sendBroadcast(mediaScanIntent)

        } catch (exception: FileNotFoundException) {
            Log.e("FileNotFoundException", exception.message)
        } catch (exception: IOException) {
            Log.e("IOException", exception.message)
        }


    }


}