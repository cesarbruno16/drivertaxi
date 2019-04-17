package com.ride.taxiDriver.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Fleet.db";
    public static final String LOCATION_TABLE_NAME = "Location";


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_BEARING = "bearing";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_SPEED = "speed";
    public static final String COLUMN_TIME = "time";
    public static final String COLOUMN_RIDE_ID = "ride_id";
    private static final String TAG = "DBHelper";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table "+LOCATION_TABLE_NAME  +" "+
                        "("+COLUMN_ID+" integer primary key," +
                        " "+COLUMN_BEARING+" text,"+
                        " "+COLUMN_LATITUDE+" text,"+
                        " "+COLUMN_LONGITUDE+" text,"+
                        " "+COLUMN_SPEED+" text,"+
                        " "+COLUMN_TIME+" text,"+
                        " "+COLOUMN_RIDE_ID+" text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertLocation (float bearing,
                                   double latitude,
                                   double longitude,
                                   float speed,
                                   long time,
                                  String Ride_id ) {

        Log.d("****"+TAG , "insertLocation "+latitude+" "+longitude+"   speed="+speed);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BEARING, bearing);
        contentValues.put(COLUMN_LATITUDE, ""+latitude);
        contentValues.put(COLUMN_LONGITUDE, ""+longitude);
        contentValues.put(COLUMN_SPEED, ""+speed);
        contentValues.put(COLUMN_TIME, ""+time);
        contentValues.put(COLOUMN_RIDE_ID, ""+Ride_id);
        db.insert(LOCATION_TABLE_NAME, null, contentValues);
        return true;
    }


    public Cursor getData(String ride_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from Location where "+COLOUMN_RIDE_ID+"="+ride_id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, LOCATION_TABLE_NAME);
        return numRows;
    }

    public Cursor getFulltableCursor (){
        String searchQuery = "SELECT  * FROM " + LOCATION_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(searchQuery, null );
        return cursor;
    }



    public Integer deleteLocation (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Location",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public void clearTable (){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LOCATION_TABLE_NAME , null , null);
    }



    public String getRideLocationData(String Rideid){

        List<String> latitude = new ArrayList<>();
        List<String> longitude = new ArrayList<>();
        List<String> speed = new ArrayList<>();
        List<String> timestamp = new ArrayList<>();
        List<String> bearing = new ArrayList<>();
        String returning_value  = "";
        Cursor c = getData(Rideid);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                latitude.add(c.getString(c.getColumnIndex(""+COLUMN_LATITUDE)));
                longitude.add(c.getString(c.getColumnIndex(""+COLUMN_LONGITUDE)));
                speed.add(c.getString(c.getColumnIndex(""+COLUMN_SPEED)));
                timestamp.add(c.getString(c.getColumnIndex(""+COLUMN_TIME)));
                bearing.add(c.getString(c.getColumnIndex(""+COLUMN_BEARING)));
                if(c.isLast()){
                    returning_value = returning_value + c.getString(c.getColumnIndex(""+COLUMN_LATITUDE))+","+c.getString(c.getColumnIndex(""+COLUMN_LONGITUDE));
                }else{
                    returning_value = returning_value + c.getString(c.getColumnIndex(""+COLUMN_LATITUDE))+","+c.getString(c.getColumnIndex(""+COLUMN_LONGITUDE))+"|";
                }

                c.moveToNext();
            }
        }

        HashMap<String , List<String>> data = new HashMap<>();
        data.put("latitude" , latitude);
        data.put("longitude" , longitude);
        data.put("speed" , speed);
        data.put("timestamp" , timestamp);
        data.put("bearing" , bearing);






        return  returning_value;
    }

//    public ArrayList<String> getAllLocations() {
//        ArrayList<String> array_list = new ArrayList<String>();
//
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from contacts", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false){
//            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
//            res.moveToNext();
//        }
//        return array_list;
//    }
}