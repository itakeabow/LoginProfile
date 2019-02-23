package com.example.loginprofile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ChatRoomActivity extends Activity {

    private ListView listView;
    private Button send,receive;
    private EditText editText;
    private boolean isSend = false;
    private MessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        listView = findViewById(R.id.listView1);
        send = findViewById(R.id.button1);
        receive = findViewById(R.id.button2);
        editText = findViewById(R.id.editText1);
        adapter = new MessageAdapter(getApplication(),R.layout.m);
        listView.setAdapter(adapter);

        final ArrayList<Message> messages= new ArrayList<>();

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                messageSend();
            }
        });
        receive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                messageReceive();
            }
        });
    }

    private boolean messageSend(){
        adapter.add(new Message(editText.getText().toString(), isSend));
        editText.setText("");
        return true;
    }

    private boolean messageReceive(){
        adapter.add(new Message(editText.getText().toString(), !isSend));
        editText.setText("");
        return true;
    }


}
