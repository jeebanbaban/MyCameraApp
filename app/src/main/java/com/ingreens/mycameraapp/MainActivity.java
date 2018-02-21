package com.ingreens.mycameraapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 1;
    ImageView imgpreview;
    VideoView videopreview;
    Button captureimage;
    Button capturevideo;
    private Uri fileUri;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isDeviceSupportCamera();

        imgpreview = findViewById(R.id.imgPreview);
        videopreview = findViewById(R.id.videoPreview);
        captureimage = findViewById(R.id.btnCapturePicture);
        capturevideo = findViewById(R.id.btnRecordVideo);

        captureimage.setOnClickListener(this);
        capturevideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCapturePicture:
                captureImage();
                break;
            case R.id.btnRecordVideo:


                break;

        }

    }


    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /*
 * Capturing Camera Image will lauch camera app requrest image capture
 */
    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }

    /**
     * Receiving activity result method will be called after closing the camera
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        ////////direct show image after capture(without sending image to memory)begin......

        bitmap=(Bitmap)data.getExtras().get("data");
        String obj=new Gson().toJson(bitmap).toString();
        Bitmap b=new Gson().fromJson(obj,Bitmap.class);
        imgpreview.setImageBitmap(b);
        imgpreview.setVisibility(View.VISIBLE);
        Log.e("Image Data: ", "onActivityResult: "+new Gson().toJson(bitmap).toString());

        ////////direct show image after capture(without sending image to memory)end.......
    }
}
