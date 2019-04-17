package com.ride.taxiDriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.apporiologs.ApporioLog;
import com.ride.taxiDriver.logger.Logger;
import com.ride.taxiDriver.models.ResultCheck;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.urls.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sampermissionutils.AfterPermissionGranted;
import com.sampermissionutils.EasyPermissions;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import customviews.typefacesviews.TypefaceDosisRegular;
import id.zelory.compressor.Compressor;

public class PhotoUploaderActivity extends Activity implements EasyPermissions.PermissionCallbacks , DatePickerDialog.OnDateSetListener , ApiManager.APIFETCHER {


    private static final int RC_CAMERA_PERM = 123;
    private static final int PICK_IMAGE = 124;
    private static final int CAMERS_PICKER = 122;
    private static final String TAG = "";
    Uri selectedImage;
    String imagePath = "" ;
    Bitmap bitmap1;
    @Bind(R.id.image) ImageView image;
    @Bind(R.id.camera) TypefaceDosisRegular camera;
    @Bind(R.id.gallery) TypefaceDosisRegular gallery;
    @Bind(R.id.date) TextView date;
    @Bind(R.id.submit) CardView submit;
    private Uri imageUri;
    ApiManager apiManager ;
    ProgressDialog progressDialog;
    private File compressedImage;
    private ContentValues values;
    private Bitmap thumbnail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_uploader);
        ButterKnife.bind(this);
        apiManager = new ApiManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try{cameraTask();}catch (Exception e){}
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{selectFromgalery();}catch (Exception e){}
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{openDateFDialog();}catch (Exception e){
                    ApporioLog.logE("" +TAG, "Exception caught while date click --> "+e.getMessage());
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(date.getText().toString().equals("DD MM YYYY")){
                        Toast.makeText(PhotoUploaderActivity.this, R.string.attach_expirey_date_of_your_document, Toast.LENGTH_SHORT).show();
                    }else{

                        compressedImage = new Compressor(PhotoUploaderActivity.this)
                                .setQuality(75)
                                .compressToFile(new File(imagePath));

                        HashMap<String , File>  images  = new HashMap<>();
                        images.put("document_image" , compressedImage );
                        HashMap<String  , String > data  = new HashMap<>();
                        data.put("document_expiry_date" , ""+date.getText().toString());
                        data.put("driver_id" , ""+getIntent().getExtras().getString("driver_id"));
                        data.put("document_id" , ""+getIntent().getExtras().getString("document_id"));
                        apiManager.execution_method_image_post(""+Config.ApiKeys.KEY_Documents_Submit , ""+ Apis.Document_Upload,images ,  data);
                    }

                }catch (Exception e){}
            }
        });



    }




    @AfterPermissionGranted(RC_CAMERA_PERM)
    public void cameraTask()throws Exception {
        if (EasyPermissions.hasPermissions(this, android.Manifest.permission.CAMERA)) {
            try{ // Have permission, do the thing!
                values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                imageUri = getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, CAMERS_PICKER);
            }catch (Exception e){}
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_camera), RC_CAMERA_PERM, android.Manifest.permission.CAMERA);
        }
    }



    public void selectFromgalery() throws Exception{
        Intent i1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i1.setType("image/*");
        startActivityForResult(i1, PICK_IMAGE);
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        ApporioLog.logI(""+TAG , "Permission Granted");
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        ApporioLog.logI(""+TAG , "Permission Denied");

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case CAMERS_PICKER:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        if (resultCode == RESULT_OK) {
//
//                            Bitmap photo = (Bitmap) data.getExtras().get("data");
//                            image.setImageBitmap(photo);
//                            Uri tempUri = getImageUri(getApplicationContext(), photo);
//                            imagePath = getPath(tempUri);
                            thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                            image.setImageBitmap(thumbnail);
                            imagePath = getRealPathFromURI(imageUri);
                            Toast.makeText(PhotoUploaderActivity.this, R.string.attach_expirey_date_of_your_document, Toast.LENGTH_SHORT).show();
                            openDateFDialog();
                        }
                    } catch (Exception e) {
                        Logger.e("Exception -->" + e.toString());
                    }
                }
                break;



            case PICK_IMAGE:
                try {
                    selectedImage = data.getData();
                    imagePath = getPath(selectedImage);
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    // Set the Image in ImageView after decoding the String
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(filePath, options);
                    final int REQUIRED_SIZE = 300;
                    int scale = 1;
                    while (options.outWidth / scale / 2 >= REQUIRED_SIZE && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                        scale *= 2;
                    options.inSampleSize = scale;
                    options.inJustDecodeBounds = false;
                    bitmap1 = BitmapFactory.decodeFile(filePath, options);
                    image.setImageBitmap(bitmap1);
                    Toast.makeText(PhotoUploaderActivity.this, R.string.attach_expirey_date_of_your_document, Toast.LENGTH_SHORT).show();
                    openDateFDialog();
                } catch (Exception e) {
//                    Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
                }
                break;
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

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }



    private void openDateFDialog() throws Exception{

        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(PhotoUploaderActivity.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dpd.setMinDate(calendar);
        dpd.setAccentColor(PhotoUploaderActivity.this.getResources().getColor(R.color.colorPrimary));
        dpd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            }
        });
        dpd.show(getFragmentManager(), "Date Picker Dialog");
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String data = ""+year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
        date.setText(""+data);
    }


    @Override
    public void onAPIRunningState(int a, String APINAME) {
       try{if(a== ApiManager.APIFETCHER.KEY_API_IS_STARTED){
           progressDialog.show();
       }else if (progressDialog.isShowing()){
           progressDialog.dismiss();
       }}catch (Exception e){}
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {

        try{
            Gson gson = new GsonBuilder().create();
            ResultCheck rs = gson.fromJson(""+script , ResultCheck.class);
            if(rs.result.equals("1")){
                DocumentSubmitModel document_response = gson.fromJson(""+script , DocumentSubmitModel.class);
                Toast.makeText(this, ""+document_response.getMsg(), Toast.LENGTH_SHORT).show();
                finish();
            }
        }catch (Exception e){}
    }

    @Override
    public void onFetchResultZero(String script) {

    }




    private class DocumentSubmitModel{

        /**
         * result : 1
         * msg : Document Upload Successfully
         * details : [{"driver_document_id":"2","driver_id":"460","document_id":"4","document_path":"uploads/demotaxiappdriver/1505120882document_image_4604.jpg","document_expiry_date":"20-9-2017","documnet_varification_status":"1"}]
         */

        private int result;
        private String msg;
        private List<DetailsBean> details;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public  class DetailsBean {
            /**
             * driver_document_id : 2
             * driver_id : 460
             * document_id : 4
             * document_path : uploads/demotaxiappdriver/1505120882document_image_4604.jpg
             * document_expiry_date : 20-9-2017
             * documnet_varification_status : 1
             */

            private String driver_document_id;
            private String driver_id;
            private String document_id;
            private String document_path;
            private String document_expiry_date;
            private String documnet_varification_status;

            public String getDriver_document_id() {
                return driver_document_id;
            }

            public void setDriver_document_id(String driver_document_id) {
                this.driver_document_id = driver_document_id;
            }

            public String getDriver_id() {
                return driver_id;
            }

            public void setDriver_id(String driver_id) {
                this.driver_id = driver_id;
            }

            public String getDocument_id() {
                return document_id;
            }

            public void setDocument_id(String document_id) {
                this.document_id = document_id;
            }

            public String getDocument_path() {
                return document_path;
            }

            public void setDocument_path(String document_path) {
                this.document_path = document_path;
            }

            public String getDocument_expiry_date() {
                return document_expiry_date;
            }

            public void setDocument_expiry_date(String document_expiry_date) {
                this.document_expiry_date = document_expiry_date;
            }

            public String getDocumnet_varification_status() {
                return documnet_varification_status;
            }

            public void setDocumnet_varification_status(String documnet_varification_status) {
                this.documnet_varification_status = documnet_varification_status;
            }
        }
    }
}
