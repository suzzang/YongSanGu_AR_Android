package com.smtown.yongsangu_ar_project.sookmyung.ending.camera;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.smtown.yongsangu_ar_project.sookmyung.ending.camera.dialog.CameraImageDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyCameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private final String TAG = "MyTag";

    private SurfaceHolder mHolder;

    private int frameId;
    private int mCameraID;

    public Activity act;
    private Camera mCamera;
    private Camera.CameraInfo mCameraInfo;


    private int mDisplayOrientation;

    public MyCameraPreview(Context context, int cameraId) {
        super(context);
        Log.d(TAG, "MyCameraPreview cameraId : " + cameraId);

        // init frameId zero
        frameId = 0;

        // 0    ->     CAMERA_FACING_BACK
        // 1    ->     CAMERA_FACING_FRONT
        mCameraID = cameraId;

        try {
            // attempt to get a Camera instance
            mCamera = Camera.open(mCameraID);

        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
            Log.e(TAG, "Camera is not available");


        }


        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);

        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        // get display orientation
        mDisplayOrientation = ((Activity)context).getWindowManager().getDefaultDisplay().getRotation();
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated");

        // retrieve camera's info.
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(mCameraID, cameraInfo);

        mCameraInfo = cameraInfo;

        // The Surface has been created, now tell the camera where to draw the preview.
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();

        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
           
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed");
        // empty. Take care of releasing the Camera preview in your activity.
        //전,후면 카메라 전환시 꼭 필요한 부분
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;

            holder.removeCallback(this);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        Log.d(TAG, "surfaceChanged");
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null){
            // preview surface does not exist
            Log.e(TAG, "preview surface does not exist");
            return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
            Log.d(TAG, "Preview stopped.");
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }


        // set preview size and make any resize, rotate or
        // reformatting changes here
        // start preview with new settings
        int orientation = calculatePreviewOrientation(mCameraInfo, mDisplayOrientation);
        mCamera.setDisplayOrientation(orientation);

        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
            Log.d(TAG, "Camera preview started.");
        } catch (Exception e) {
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }

    }

    /**
     * 안드로이드 디바이스 방향에 맞는 카메라 프리뷰를 화면에 보여주기 위해 계산합니다.
     */
    public int calculatePreviewOrientation(Camera.CameraInfo info, int rotation) {
        int degrees = 0;

        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;

        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }

        return result;
    }

    /**
     *  이미지 캡처
     */
    public void takePicture(Activity activity){
        this.act = activity;

        mCamera.takePicture(shutterCallback, rawCallback, jpegCallback);
    }


    /**
     *  이미지 캡처 시 배경 선택
     */
    public void setFrameId(int frameId) {
        this.frameId = frameId;
    }

    /**
     * 이미지 저장을 위한 콜백 함수
     */
    private Camera.ShutterCallback shutterCallback = new Camera.ShutterCallback() {
        public void onShutter() {

        }
    };

    private Camera.PictureCallback rawCallback = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {

        }
    };


    private Camera.PictureCallback jpegCallback = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {


                //이미지의 너비와 높이 결정
                int w = camera.getParameters().getPictureSize().width;
                int h = camera.getParameters().getPictureSize().height;
                int orientation = calculatePreviewOrientation(mCameraInfo, mDisplayOrientation);

                //Log.d("MyTag","이미지 캡처 시 -> orientation : " + orientation);

                //byte array 를 bitmap 으로 변환
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                Bitmap bitmap = BitmapFactory.decodeByteArray( data, 0, data.length, options);

                //이미지를 디바이스 방향으로 회전
                Matrix matrix = new Matrix();

                /**
                 * 셀카 모드에는 저장 시 좌우 반전을 해줘야 한다.
                 */
                if(mCameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                    //Log.d("MyTag","180도 추가 회전 후 좌우반전을 해줍니다.");
                    //orientation += 180;
                    matrix.setScale(-1,1);
                }

                matrix.postRotate(orientation);
                bitmap =  Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);


                /**
                 * frameId 가 있는 경우 해당 이미지를 합성해 준다.
                 */
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();

                if(0 != frameId) {

                    Bitmap bp = BitmapFactory.decodeResource(getContext().getResources(),frameId);
                    Bitmap resizeBp = Bitmap.createScaledBitmap(bp, width, height, true);

                    Canvas canvas = new Canvas(bitmap);
                    canvas.drawBitmap(resizeBp, 0f, 0f,null);

                }

//            bgr.setImageBitmap(bitmap);//딱 캡쳐만 하는 부분

                DisplayMetrics displayMetrics = act.getApplicationContext().getResources().getDisplayMetrics();

                int width_d = displayMetrics.widthPixels;
                int  height_d = displayMetrics.heightPixels;

                CameraImageDialog camera_image_dialog = new CameraImageDialog(act,bitmap);


                WindowManager.LayoutParams wm = camera_image_dialog.getWindow().getAttributes();
                wm.copyFrom(camera_image_dialog.getWindow().getAttributes());

                camera_image_dialog.show();



        }
    };



}