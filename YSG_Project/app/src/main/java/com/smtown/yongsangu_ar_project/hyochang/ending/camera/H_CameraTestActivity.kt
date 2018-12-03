package com.smtown.yongsangu_ar_project.hyochang.ending.camera

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.hardware.Camera
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import com.smtown.yongsangu_ar_project.R
import com.smtown.yongsangu_ar_project.sookmyung.ending.camera.dialog.CameraImageDialog
import kotlinx.android.synthetic.main.activity_ar.*
import kotlinx.android.synthetic.main.activity_camera_test.*
import kotlinx.android.synthetic.main.activity_h_camera_test.*

class H_CameraTestActivity: AppCompatActivity() {
    private val TAG = "MyTag"
    var btn_flag = 0

    private val PERMISSIONS_REQUEST_CODE = 100

    private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private var CAMERA_FACING = Camera.CameraInfo.CAMERA_FACING_FRONT

    private var myCameraPreview: H_MyCameraPreview? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 상태바를 안보이도록 합니다.
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // 화면 켜진 상태를 유지합니다.
        window.setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        setContentView(R.layout.activity_h_camera_test)

        h_btnCapture.setOnClickListener {
            myCameraPreview?.setFrameId(R.drawable.people)
            myCameraPreview?.takePicture(this)


        }
        h_btnTransform.setOnClickListener {
            transformCamera()
        }

//        ivFrame1.setOnClickListener {
//
//            ivFrameSet.setImageResource(R.drawable.frame1)
//            myCameraPreview?.setFrameId(R.drawable.frame1)
//
//        }
//
//        ivFrame2.setOnClickListener {
//
//            ivFrameSet.setImageResource(R.drawable.frame2)
//            myCameraPreview?.setFrameId(R.drawable.frame2)c
//        }

        //액자 깔리는 부분
        h_ivFrameSet.setImageResource(R.drawable.people)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // OS가 Marshmallow 이상일 경우 권한체크를 해야 합니다.

            val permissionCheckCamera
                    = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            val permissionCheckStorage
                    = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

            if (permissionCheckCamera == PackageManager.PERMISSION_GRANTED

                    && permissionCheckStorage == PackageManager.PERMISSION_GRANTED) {

                // 권한 있음
                Log.d(TAG, "권한 이미 있음")
                startCamera()


            } else {

                // 권한 없음
                Log.d(TAG, "권한 없음")
                ActivityCompat.requestPermissions(this,
                        REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE)


            }


        } else {
            // OS가 Marshmallow 이전일 경우 권한체크를 하지 않는다.
            Log.d("MyTag", "마시멜로 버전 이하로 권한 이미 있음")
            startCamera()

        }

    }
    private fun transformCamera() {

        if(CAMERA_FACING == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            CAMERA_FACING = Camera.CameraInfo.CAMERA_FACING_BACK

            h_cameraPreview.removeAllViews()

            startCamera()


        } else {
            CAMERA_FACING = Camera.CameraInfo.CAMERA_FACING_FRONT

            h_cameraPreview.removeAllViews()
            startCamera()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // grantResults[0] 거부 -> -1
        // grantResults[0] 허용 -> 0 (PackageManager.PERMISSION_GRANTED)

        Log.d(TAG, "requestCode : $requestCode, grantResults size : ${grantResults.size}")

        if(requestCode == PERMISSIONS_REQUEST_CODE) {

            var check_result = true

            for(result in grantResults) {
                if(result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false
                    break
                }
            }

            if(check_result) {

                startCamera()

            } else {

                Log.e(TAG, "권한 거부")
            }

        }

    }
    private fun startCamera() {

        Log.e(TAG, "startCamera")

        // Create our Preview view and set it as the content of our activity.
        myCameraPreview = H_MyCameraPreview(this, CAMERA_FACING)

        h_cameraPreview.addView(myCameraPreview)

    }
}