package com.ride.taxiDriver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ride.taxiDriver.logger.Logger;
import com.ride.taxiDriver.others.ImageCompressMode;
import com.sampermissionutils.AfterPermissionGranted;
import com.sampermissionutils.EasyPermissions;

import java.io.ByteArrayOutputStream;
import java.util.List;
import android.Manifest;

import org.json.JSONObject;


public class MeterImageActivity extends Activity implements EasyPermissions.PermissionCallbacks{


    private static final int RC_CAMERA_PERM = 123;
    private static final String TAG = "MeterImageActivity";
    public static int CAMERS_PICKER = 102;
    private static String imagePathCompressed = "";

    ImageView image ;
    EditText meter_val_edt ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter_image);
        image = (ImageView) findViewById(R.id.image);
        meter_val_edt = (EditText) findViewById(R.id.meter_val_edt);

        imagePathCompressed = "";

        findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraTask();
            }
        });

        findViewById(R.id.ok_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imagePathCompressed.equals("") || meter_val_edt.getText().toString().equals("")){
                    Toast.makeText(MeterImageActivity.this, R.string.METER_IMAGE_ACTIVITY__current_meter_reading_txt, Toast.LENGTH_SHORT).show();
                }else {
                    finalizeActivity();
                }
            }
        });

    }




    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            // Have permission, do the thing!
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent, CAMERS_PICKER);
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_camera), RC_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            try {
                if (requestCode == CAMERS_PICKER) {
                    if (resultCode == RESULT_OK) {
                        Bitmap photo = (Bitmap) data.getExtras().get("data");
                        image.setImageBitmap(photo);
                        Uri tempUri = getImageUri(getApplicationContext(), photo);
                        imagePathCompressed = new ImageCompressMode(this).compressImage(getPath(tempUri));
                    }
                }
            } catch (Exception e) {
                Logger.e("res         " + e.toString());
            }
        }
    }



    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent=new Intent();
        intent.putExtra("image","");
        intent.putExtra("meter" , "");
        setResult(Activity.RESULT_CANCELED,intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent();
        intent.putExtra("image","");
        intent.putExtra("meter" , "");
        setResult(Activity.RESULT_CANCELED,intent);
        finish();
    }

    private void finalizeActivity() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        JSONObject no_data = new JSONObject();
        if(imagePathCompressed.equals("") || meter_val_edt.getText().toString().equals("")){
            Intent intent=new Intent();
            intent.putExtra("image","");
            intent.putExtra("meter" , "");
            setResult(Activity.RESULT_CANCELED,intent);
        }else{
            Intent intent=new Intent();
            intent.putExtra("image",""+imagePathCompressed);
            intent.putExtra("meter" , ""+meter_val_edt.getText().toString());
            setResult(Activity.RESULT_OK,intent);
        }
        finish();
    }


}