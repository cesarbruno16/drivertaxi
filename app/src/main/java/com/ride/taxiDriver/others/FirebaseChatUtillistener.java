package com.ride.taxiDriver.others;

import android.util.Log;

import com.ride.taxiDriver.Config;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo-pc on 10/10/2017.
 */

public class FirebaseChatUtillistener {


    private static final String TAG = "FirebaseChatUtillistener";
    DatabaseReference mDatabaseReference;
    List<ChatModel> mData = new ArrayList<>();


    ChildEventListener childEventListener = new ChildEventListener() {
        public void onChildAdded(DataSnapshot dataSnapshot, String previousKey) {
            EventBus.getDefault().post(new FirebaseChatEvent(""+dataSnapshot.child("message").getValue() , ""+dataSnapshot.child("send_via").getValue() , ""+dataSnapshot.child("timestamp").getValue()));
//            try{
//                GenericTypeIndicator<List<ChatModel>> t = new GenericTypeIndicator<List<ChatModel>>() {};
//                List<ChatModel> grabbedData = dataSnapshot.getValue(t);
//                Log.d(""+TAG , "onChildAdded "+grabbedData.g);
//                if(mData == null ){
//                    mData = grabbedData ;
//                    Log.d(""+TAG , "on Child Adding "+mData);
//                    EventBus.getDefault().post(new FirebaseChatEvent(mData) );
//                }
//
//            }catch (Exception e){}

        }

        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            Log.d(""+TAG , "onChildChanged");
//            try{
//                GenericTypeIndicator<List<ChatModel>> t = new GenericTypeIndicator<List<ChatModel>>() {};
//                List<ChatModel> grabbedData = dataSnapshot.child("Chat").getValue(t);
//                if(mData == null ){
//                    mData = grabbedData ;
//                    Log.d(""+TAG , "Listing data when mData is null "+mData);
//                    EventBus.getDefault().post(new FirebaseChatEvent(mData) );
//                }else if(mData.size()>0 && mData.size() <grabbedData.size()){
//                    mData = grabbedData ;
//                    EventBus.getDefault().post(new FirebaseChatEvent(mData) );
//                }else{
//                    Log.d(""+TAG , "NO CHANGE INSIDE EXSISTING ARRAY");
//                }
//
//            }catch (Exception e){Log.d(""+TAG , "Error Found while parsing change");}
        }

        public void onChildRemoved(DataSnapshot dataSnapshot) {
            System.out.println("ON CHILD REMOVED " + dataSnapshot.getKey());
        }

        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            System.out.println("ON CHILD MOVED  " + dataSnapshot.getKey() + " to UI after " + s);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            System.out.println("Firebase ERROR  ");
        }
    };



    public FirebaseChatUtillistener(String RideId){
       mDatabaseReference =  FirebaseDatabase.getInstance().getReference(Config.ChatReferencetable).child(RideId);
    }


    public void startChatListening(){
        Log.i("" +TAG, "Starting Chat Event");
        mDatabaseReference.addChildEventListener(childEventListener);
    }


    public void stopChatListener(){
        Log.i("" +TAG, "Stop Chat Event");
        mDatabaseReference.removeEventListener(childEventListener);
    }



}
