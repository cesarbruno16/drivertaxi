package com.ride.taxiDriver;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.apporiologs.ApporioLog;
import com.ride.taxiDriver.others.ChatModel;
import com.ride.taxiDriver.others.FirebaseChatEvent;
import com.ride.taxiDriver.others.FirebaseChatUtillistener;
import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import morxander.zaman.ZamanTextView;

/**
 * Created by samirgoel3@gmail.com on 10/10/2017.
 */

public class ChatActivity extends Activity {

    private static final String TAG = "ChatActivity";
    @Bind(R.id.list)
    ListView list;
    @Bind(R.id.message_edt)
    EditText messageEdt;
    @Bind(R.id.send_btn)
    LinearLayout sendBtn;

    FirebaseChatUtillistener firebaseChatUtillistener;
    List<ChatModel> localMesagees = new ArrayList<>();
    ChatAdapter chatAdapter;
    @Bind(R.id.user_image)
    CircleImageView userImage;
    @Bind(R.id.user_name_txt)
    TextView userNameTxt;
    @Bind(R.id.riding_status)
    TextView ridingStatus;

    public static Activity activity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        activity = this ;
        try {
            firebaseChatUtillistener = new FirebaseChatUtillistener(getIntent().getExtras().getString("ride_id"));
        } catch (Exception e) {
            Toast.makeText(this, "Unable to fetch via Intent", Toast.LENGTH_SHORT).show();
        }

        ButterKnife.bind(this);
        chatAdapter = new ChatAdapter();
        list.setAdapter(chatAdapter);
        list.setSelection(chatAdapter.getCount() - 1);


        try{ ridingStatus.setText(""+getIntent().getExtras().getString("ride_status"));
            userNameTxt.setText(""+getIntent().getExtras().getString("user_name"));
            Glide.with(this).load("" + getIntent().getExtras().getString("user_image")).placeholder(R.drawable.ic_profile_small).into(userImage);}catch (Exception e){}


        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!messageEdt.getText().toString().trim().equals("")) {
                    List<ChatModel> tempMessage = new ArrayList<ChatModel>();
                    tempMessage = localMesagees;
                    FirebaseDatabase.getInstance().getReference("" + Config.ChatReferencetable).child("/" + getIntent().getExtras().getString("ride_id")).push().setValue(new ChatModel("Driver", "" + messageEdt.getText().toString(), "" + System.currentTimeMillis() / 1000));
                    messageEdt.setText("");
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        firebaseChatUtillistener.startChatListening();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        firebaseChatUtillistener.stopChatListener();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(FirebaseChatEvent event) {
        if(!checkExsistedmessage(event)){
            localMesagees.add(new ChatModel(event.send_via, event.message, event.timestamp));
        }
        chatAdapter.notifyDataSetChanged();
        list.setSelection(chatAdapter.getCount() - 1);
    }

    private class ChatAdapter extends BaseAdapter {

        public ChatAdapter() {
        }

        @Override
        public int getCount() {
            return localMesagees.size();
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder holder = null;
            LayoutInflater mInflater = (LayoutInflater) ChatActivity.this.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.layout_left_message, null);
                holder = new ViewHolder();
                holder.left_side_msg_layout = (CardView) convertView.findViewById(R.id.left_side_msg_layout);
                holder.right_side_msg_layout = (CardView) convertView.findViewById(R.id.right_side_msg_layout);
                holder.left_txt = (TextView) convertView.findViewById(R.id.left_txt);
                holder.left_date = (ZamanTextView) convertView.findViewById(R.id.left_date);
                holder.right_text = (TextView) convertView.findViewById(R.id.right_text);
                holder.right_date = (ZamanTextView) convertView.findViewById(R.id.right_date);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            try{holder.left_txt.setText("" + localMesagees.get(i).message);
                holder.right_text.setText("" + localMesagees.get(i).message);
                holder.left_date.setTimeStamp(Long.parseLong("" + localMesagees.get(i).timestamp));
                holder.right_date.setTimeStamp(Long.parseLong("" + localMesagees.get(i).timestamp));}catch (Exception e){
                ApporioLog.logD(""+TAG , ""+e.getMessage());
            }


            setViewVisibility(i, holder);
            return convertView;
        }

        private void setViewVisibility(int i, ViewHolder holder) {
            if (localMesagees.get(i).send_via.equals("User")) {
                holder.left_side_msg_layout.setVisibility(View.GONE);
                holder.right_side_msg_layout.setVisibility(View.VISIBLE);
            } else if (localMesagees.get(i).send_via.equals("Driver")) {
                holder.left_side_msg_layout.setVisibility(View.VISIBLE);
                holder.right_side_msg_layout.setVisibility(View.GONE);
            } else {
                holder.left_side_msg_layout.setVisibility(View.GONE);
                holder.right_side_msg_layout.setVisibility(View.GONE);
            }
        }

        private class ViewHolder {
            CardView left_side_msg_layout, right_side_msg_layout;
            TextView left_txt, right_text;
            ZamanTextView left_date, right_date;
        }
    }

    private boolean checkExsistedmessage(FirebaseChatEvent event){
        boolean returning_val = false ;
        for(int i = 0  ; i < localMesagees.size() ; i++){
            if(localMesagees.get(i).timestamp.equals(""+event.timestamp)){
                returning_val = true ;
            }
        }
        return returning_val ;
    }

}
