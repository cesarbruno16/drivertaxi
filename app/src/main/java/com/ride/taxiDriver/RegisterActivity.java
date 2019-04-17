package com.ride.taxiDriver;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ride.taxiDriver.adapter.CarAdapter;
import com.ride.taxiDriver.adapter.CarModelAdapter;
import com.ride.taxiDriver.adapter.CityAdapter;
import com.ride.taxiDriver.models.ResultCheck;
import com.ride.taxiDriver.models.carmodels.CarModels;
import com.ride.taxiDriver.models.register.Register;
import com.ride.taxiDriver.models.viewcartype.ViewCarType;
import com.ride.taxiDriver.models.viewcity.ViewCity;
import com.ride.taxiDriver.others.ImageCompressMode;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.urls.Apis;
import com.crowdfire.cfalertdialog.CFAlertDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sampermissionutils.AfterPermissionGranted;
import com.sampermissionutils.EasyPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements ApiManager.APIFETCHER {

    TextView tv_car_type, tv_car_model, tv_city , tv_city_two, tv_ride_category, txt_phone_signup;
    EditText edt_username_signup, edt_email_signup, edt_pass_signup, edt_car_number, edt_bank_name, edt_account_number, edt_account_name;
    LinearLayout ll_register, ll_back_signup;
    ImageView driver_image ;
    Uri selectedImage;
    Bitmap bitmap1;
    String imagePath = "", imagePathCompressed = "";
    public static Activity register;
    ArrayList<String> driver_arr;
    String city_id, city_name, car_id, car_name, car_model_id, car_model_name, ride_cat_id, password;
    ProgressDialog pd;
    String cityCheck = "", carTypeCheck = "", carNameCheck = "";
    ViewCity viewCity;
    ViewCarType viewCarType;
    CarModels carModels;
    ApiManager apimanager ;
    GsonBuilder builder ;
    Gson gson ;

    private static final int KEY_REGISTER = 110;
    String phoneNumber, bank_name, account_number, account_name;

    private static final int RC_CAMERA_PERM = 123;
    private ContentValues values;
    private Bitmap thumbnail;
    private static final int CAMERS_PICKER = 122;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apimanager = new ApiManager(this);
        builder = new GsonBuilder();
        gson = builder.create();
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        register = this;

        driver_arr = new ArrayList<>();
        driver_arr.add("Normal Ride");
        driver_arr.add("Charter Ride");
        driver_arr.add("Both");


        pd = new ProgressDialog(this);
        pd.setMessage(RegisterActivity.this.getResources().getString(R.string.loading));
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        tv_ride_category = (TextView) findViewById(R.id.tv_ride_category);

        driver_image = (ImageView) findViewById(R.id.driver_image);
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_car_type = (TextView) findViewById(R.id.tv_car_type);
        tv_car_model = (TextView) findViewById(R.id.tv_car_model);

        edt_username_signup = (EditText) findViewById(R.id.edt_username_signup);
        edt_email_signup = (EditText) findViewById(R.id.edt_email_signup);
        edt_pass_signup = (EditText) findViewById(R.id.edt_pass_signup);
        tv_city_two = (TextView) findViewById(R.id.tv_city_two);
        txt_phone_signup = (TextView) findViewById(R.id.txt_phone_signup);

        edt_car_number = (EditText) findViewById(R.id.edt_car_number);
        ll_register = (LinearLayout) findViewById(R.id.ll_register);
        ll_back_signup = (LinearLayout) findViewById(R.id.ll_back_signup);

        edt_bank_name = (EditText) findViewById(R.id.bank_name);
        edt_account_number = (EditText) findViewById(R.id.account_number);
        edt_account_name = (EditText) findViewById(R.id.account_holder_name);
        edt_username_signup.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans_Regular.ttf"));
        edt_email_signup.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans_Regular.ttf"));
        txt_phone_signup.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans_Regular.ttf"));
        edt_pass_signup.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans_Regular.ttf"));
        edt_car_number.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans_Regular.ttf"));
        edt_bank_name.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans_Regular.ttf"));
        edt_account_number.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans_Regular.ttf"));
        edt_account_name.setTypeface(Typeface.createFromAsset(getAssets(), "OpenSans_Regular.ttf"));
        apimanager.execution_method_get( Config.ApiKeys.KEY_View_cities , Apis.viewCities);

        tv_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cityCheck.equals("1")) {
                    final Dialog dialog = new Dialog(RegisterActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    dialog.setContentView(R.layout.dialog_for_city);

                    ListView lv_cities = (ListView) dialog.findViewById(R.id.lv_cities);
                    lv_cities.setAdapter(new CityAdapter(RegisterActivity.this, viewCity));


                    lv_cities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            city_id = viewCity.getMsg().get(position).getCityId();
                            tv_city.setText(""+viewCity.getMsg().get(position).getCityName());
                            apimanager.execution_method_get(Config.ApiKeys.KEY_View_car_by_city , Apis.viewCarByCities+"?city_id="+city_id);
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else if (cityCheck.equals("2")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.no_city_found), Toast.LENGTH_SHORT).show();
                }
            }
        });

        driver_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CFAlertDialog.Builder builder = new CFAlertDialog.Builder(RegisterActivity.this);
                builder.setDialogStyle(CFAlertDialog.CFAlertStyle.ALERT);
                builder.setTitle(R.string.upload_profile_pic);
                builder.setItems(new String[]{getString(R.string.camera), getString(R.string.gallery)}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index) {
                        if(index == 0 ){
                            try{cameraTask();}catch (Exception e){}
                        }else if (index == 1 ){
                            Intent i1 = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            i1.setType("image/*");
                            startActivityForResult(i1, 101);
                        }
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        });


        txt_phone_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(RegisterActivity.this, Verify_OTP.class), 110);
            }
        });


        tv_ride_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (tv_ride_category.getText().toString().equals("Type of Ride")) {
                    Toast.makeText(RegisterActivity.this,  RegisterActivity.this.getResources().getString(R.string.select_ride_category), Toast.LENGTH_SHORT).show();
                } else{*/
                    final Dialog dialog = new Dialog(RegisterActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    dialog.setContentView(R.layout.dialog_driver_category);

                    ListView lv_category = (ListView) dialog.findViewById(R.id.lv_category);
                    lv_category.setAdapter(new Driver_Category_Adapter(RegisterActivity.this, driver_arr));

                    lv_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ride_cat_id = String.valueOf(position + 1);
                            Log.e("**ride_cat_id----", ride_cat_id);
                          //  dialog.dismiss();
                            tv_ride_category.setText(driver_arr.get(position));
                      //      tv_ride_category.setVisibility(View.GONE);
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

            }
        });

        tv_city_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (cityCheck.equals("1")) {

                    final Dialog dialog = new Dialog(RegisterActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    dialog.setContentView(R.layout.dialog_for_city);

                    ListView lv_cities = (ListView) dialog.findViewById(R.id.lv_cities);
                    lv_cities.setAdapter(new CityAdapter(RegisterActivity.this, viewCity));


                    lv_cities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            city_id = viewCity.getMsg().get(position).getCityId();
                            tv_city.setText(viewCity.getMsg().get(position).getCityName());
                            tv_city_two.setVisibility(View.GONE);

                            apimanager.execution_method_get(Config.ApiKeys.KEY_View_car_by_city , Apis.viewCarByCities+"?city_id="+city_id);
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else if (cityCheck.equals("2")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.no_city_found), Toast.LENGTH_SHORT).show();
                }
            }
        });




        tv_car_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tv_city.getText().toString().equals("Select City")) {
                    Toast.makeText(RegisterActivity.this,  RegisterActivity.this.getResources().getString(R.string.please_select_city), Toast.LENGTH_SHORT).show();
                } else if (carTypeCheck.equals("1")) {
                    final Dialog dialog = new Dialog(RegisterActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    dialog.setContentView(R.layout.dialog_for_car);

                    ListView lv_cars = (ListView) dialog.findViewById(R.id.lv_cars);
                    lv_cars.setAdapter(new CarAdapter(RegisterActivity.this, viewCarType));

                    lv_cars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            car_id = viewCarType.getMsg().get(position).getCarTypeId();

                            tv_car_type.setText(viewCarType.getMsg().get(position).getCarTypeName());
                            apimanager.execution_method_get(Config.ApiKeys.KEY_View_car_Model , Apis.viewCarModels+"?car_type_id="+car_id);
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                } else if (carTypeCheck.equals("2")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.no_car_found), Toast.LENGTH_SHORT).show();
                }
            }
        });




        tv_car_model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_car_type.getText().toString().equals("Select Car Type")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_select_car_type_first), Toast.LENGTH_SHORT).show();
                } else if (carNameCheck.equals("1")) {
                    final Dialog dialog = new Dialog(RegisterActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    Window window = dialog.getWindow();
                    window.setGravity(Gravity.CENTER);
                    dialog.setContentView(R.layout.dialog_for_car_model);

                    ListView lv_cars = (ListView) dialog.findViewById(R.id.lv_car_model);
                    lv_cars.setAdapter(new CarModelAdapter(RegisterActivity.this, carModels));

                    lv_cars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            car_model_id = carModels.getMsg().get(position).getCarModelId();

                            tv_car_model.setText(""+carModels.getMsg().get(position).getCarModelName());
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                } else {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.no_car_model_found), Toast.LENGTH_SHORT).show();
                }

            }
        });

        ll_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edt_username_signup.getText().toString().trim();
                String email = edt_email_signup.getText().toString().trim();
                password = edt_pass_signup.getText().toString().trim();
                String carTypeName = tv_car_type.getText().toString();
                String carModelName = tv_car_model.getText().toString().trim();
                String carNumber = edt_car_number.getText().toString().trim();
                bank_name = edt_bank_name.getText().toString().trim();
                account_number = edt_account_number.getText().toString().trim();
                account_name = edt_account_name.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+.[a-z]+";

                if (name.equals("")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_enter_name), Toast.LENGTH_SHORT).show();
                } else if (email.equals("")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_enter_email), Toast.LENGTH_SHORT).show();
                } else if (password.equals("")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_enter_password), Toast.LENGTH_SHORT).show();
                } else if (carTypeName.equals("Select Car Type")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_select_car_type), Toast.LENGTH_SHORT).show();
                } else if (carModelName.equals("Select Car Model")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_select_car_model), Toast.LENGTH_SHORT).show();
                } else if (carNumber.equals("")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_enter_car_number), Toast.LENGTH_SHORT).show();
                } else if (!(email.matches(emailPattern))) {
                    edt_email_signup.setText("");
                    Toast.makeText(getApplicationContext(), RegisterActivity.this.getResources().getString(R.string.please_enter_correct_email), Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.password_should_be_minimum_six_digit), Toast.LENGTH_SHORT).show();
                } else if (bank_name.equals("")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_enter_bank_name), Toast.LENGTH_SHORT).show();
                } else if (account_number.equals("")) {
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_enter_account_number), Toast.LENGTH_SHORT).show();
                }else if (account_name.equals("")){
                    Toast.makeText(RegisterActivity.this, RegisterActivity.this.getResources().getString(R.string.please_enter_account_name), Toast.LENGTH_SHORT).show();
                }else if (imagePathCompressed.equals("")){
                    Toast.makeText(RegisterActivity.this, R.string.please_upload_a_good_quality_image_for_your_profile, Toast.LENGTH_SHORT).show();
                }else {
                    cretaDriverAccount(name, email, password, city_id, car_id, car_model_id, carNumber, "3", bank_name, account_number, account_name);
                }
            }
        });

        ll_back_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        try{cameraTask();}catch (Exception e){}
    }


    private void cretaDriverAccount(String name, String email, String password, String city_id, String car_id, String car_model_id, String carNumber, String ride_cat_id, String bank_name, String account_number, String account_name) {
        HashMap<String, String> bodyParameters = new HashMap<String, String>();
        HashMap<String, File> data_image = new HashMap<String, File>();
        bodyParameters.put("driver_name", name);
        bodyParameters.put("driver_email", email);
        bodyParameters.put("driver_phone", phoneNumber);
        bodyParameters.put("driver_password", password);
        bodyParameters.put("city_id", city_id);
        bodyParameters.put("car_type_id", car_id);
        bodyParameters.put("car_number", carNumber);
        bodyParameters.put("car_model_id", car_model_id);
        bodyParameters.put("driver_category", ride_cat_id);
        bodyParameters.put("driver_bank_name", bank_name);
        bodyParameters.put("driver_account_name", account_name);
        bodyParameters.put("driver_account_number", account_number);
        data_image.put("driver_image" , new File(imagePathCompressed));
        apimanager.execution_method_image_post(Config.ApiKeys.KEY_Driver_register , Apis.register,data_image, bodyParameters);

    }


    /////////////// samir work
    @Override
    public void onAPIRunningState(int a, String APINAME) {
        if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
            pd.show();
        }
        if (a == ApiManager.APIFETCHER.KEY_API_IS_STOPPED) {
            pd.dismiss();
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try{ if(APINAME.equals(Config.ApiKeys.KEY_View_cities)){
            ResultCheck resultCheck;
            resultCheck = gson.fromJson(""+script, ResultCheck.class);
            if (resultCheck.result.toString().equals("1")) {
                cityCheck = "1";
                viewCity = gson.fromJson(""+script, ViewCity.class);
            } else {
                cityCheck = "2";
            }
        }
            if (APINAME.equals(Config.ApiKeys.KEY_View_car_by_city)) {
                ResultCheck resultCheck;
                resultCheck = gson.fromJson(""+script, ResultCheck.class);
                if (resultCheck.result.equals("1")) {
                    carTypeCheck = "1";
                    viewCarType = gson.fromJson(""+script, ViewCarType.class);
                } else {
                    carTypeCheck = "2";
                }
            }
            if (APINAME.equals(Config.ApiKeys.KEY_View_car_Model)){
                ResultCheck resultCheck;
                resultCheck = gson.fromJson(""+script, ResultCheck.class);
                if (resultCheck.result.equals("1")) {
                    carNameCheck = "1";
                    carModels = gson.fromJson(""+script, CarModels.class);
                } else {
                    carNameCheck = "2";
                }
            }
            if (APINAME.equals(Config.ApiKeys.KEY_Driver_register)){
                Register register = new Register();
                register = gson.fromJson(""+script + "", Register.class);
                if (register.getResult()== 1) {
                    Toast.makeText(this, "" + register.getMsg(), Toast.LENGTH_SHORT).show();
//                new SessionManager(this).createLoginSession(register.getDetails().getDriverId(),register.getDetails().getDriverName(),register.getDetails().getDriverPhone(),register.getDetails().getDriverEmail(),register.getDetails().getDriverImage(),register.getDetails().getDriverPassword(),register.getDetails().getDriverToken(),register.getDetails().getDeviceId(),Config.Devicetype,register.getDetails().getRating(),register.getDetails().getCarTypeId(),register.getDetails().getCarModelId(),
//                        register.getDetails().getCarNumber(),register.getDetails().getCityId(),register.getDetails().getRegisterDate(),register.getDetails().getLicense(),register.getDetails().getRc(),register.getDetails().getInsurance(),"other_doc","getlast update","last update date ",register.getDetails().getCompletedRides(), register.getDetails().getRejectRides(),register.getDetails().getCancelledRides(),
//                        register.getDetails().getLoginLogout(),register.getDetails().getBusy(),register.getDetails().getOnlineOffline(),register.getDetails().getDetailStatus(),register.getDetails().getStatus(),register.getDetails().getCarTypeName(),register.getDetails().getCarModelName() , "");
                    startActivity(new Intent(this, DocumentActivity.class)
                            .putExtra("driver_id", "" + register.getDetails().getDriver_id())
                            .putExtra("city_id", "" + register.getDetails().getCity_id())
                            .putExtra("email", edt_email_signup.getText().toString())
                            .putExtra("password", password));

                    Log.d("**city_id===", register.getDetails().getCity_id());
                    Log.d("**driver_id===", register.getDetails().getDriver_id());
                    Log.d("**email===", edt_email_signup.getText().toString());
                    Log.d("**password===", password);
//                firebaseUtils.setUpDriver(register);
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                    finish();
                    SplashActivity.splash.finish();
                } else {
                    Toast.makeText(this, "" + register.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }}catch (Exception e){}

    }



    @Override
    public void onFetchResultZero(String script) {

    }






















    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)   {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            switch (requestCode){
                case KEY_REGISTER:
                    phoneNumber = data.getExtras().getString("phone_number");
                    txt_phone_signup.setText(data.getExtras().getString("phone_number"));
                    break;
                case 101 :
                    selectedImage = data.getData();
                    imagePath = getPath(selectedImage);
                    ImageCompressMode imageCompressMode = new ImageCompressMode(this);
                    imagePathCompressed = imageCompressMode.compressImage(imagePath);
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
                    driver_image.setImageBitmap(bitmap1);
                    break ;
                case CAMERS_PICKER:
                    thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    driver_image.setImageBitmap(thumbnail);
                    imagePath = getRealPathFromURI(imageUri);
                    ImageCompressMode imageCompressModee = new ImageCompressMode(this);
                    imagePathCompressed = imageCompressModee.compressImage(imagePath) ;
                    break;
            }
        } catch (Exception e){}

    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }


    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

}